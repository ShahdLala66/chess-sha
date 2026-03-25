# Arabian Chess: Architecture & Design Document

This document outlines the high-level architecture, project structure, and design principles for the Arabian Chess application. It serves as a guide for building a scalable, maintainable, and highly decoupled system using Scala 3, Spring Boot, and Angular.

## 1. High-Level Architecture

The project strictly follows the **Model-View-Controller (MVC)** architectural pattern, combined with the **Observer Pattern** to handle multiple concurrent views.

### Model (Core Game Logic)
- **Responsibility:** Manages the game rules, board state, move validation (including edge cases like *en passant*, castling, promotion), and session states.
- **Scalability:** Design to support multiple concurrent games. Avoid global singletons. Use `GameID` and `SessionID` to track instances.
- **Purity:** The model must be entirely unaware of any View or Controller implementations. Tests should cover 100% of this layer.

### Controller (The Brain)
- **Responsibility:** Acts as the bridge between Models and Views. It receives inputs from the views (user moves) and updates the models. 
- **Observer Pattern:** The Controller listens to input streams (Observables). Once the model updates, the Controller broadcasts the new board state to all subscribed Views simultaneously, without either view blocking the other.

### View (Interfaces)
- **Responsibility:** Display the game state and capture user inputs. 
- **Decoupling:** Views must be entirely "dumb" interfaces. 
- **Dual Views:** 
  - **WebView (Angular):** Uses the Arabian Chess PNG sprites, styled with soft edges, warm/pastel colors, and Comic Sans.
  - **TUI (Terminal UI):** A command-line interface running concurrently. Must be instantiable via a single line of code in the `main` method, allowing it to be safely disabled by just removing that one line. When someone uses the TUI it should updates to the controller and then sends singal to the observer to update the Webview as well and vice versa so a player can use both views at the same time if wanted. 

## 2. Technology Stack
- **Backend:** Scala 3, SBT, Spring Boot.
- **Frontend:** Angular, HTML, CSS.
- **Future Extension:** Lichess Bot Microservice (will consume the core Model/Controller logic).

## 3. Directory & Module Structure

A recommend directory structure built for Scala/SBT and Angular:

```text
chess/
├── backend/                  # Scala 3 + Spring Boot Backend
│   ├── build.sbt             # SBT Configuration
│   └── src/
│       ├── main/scala/chess/
│       │   ├── App.scala             # EXACTLY ONE Entry Point (main method)
│       │   ├── model/                # Board, Pieces, Rules, SessionManager
│       │   ├── controller/           # Main Controller, Input Observables
│       │   └── view/                 # View Interfaces, TUI implementation, REST API integrations
│       └── test/scala/chess/         # TDD Test configurations (100% coverage target)
├── frontend/                 # Angular Workspace
│   ├── src/
│   │   ├── app/              # UI Components (Board, Settings)
│   │   └── assets/           # Mapped from `ARABIAN CHESS/` (Sprites, Sheets)
└── docs/                     # Documentation (Architecture, APIs)
```

## 4. Key Design Principles

1. **Test-Driven Development (TDD):** All game logic, particularly special moves (checkmate, draw conditions, en passant), must have tests written *before* implementation.
2. **Micro-Methods:** Code readability is paramount. Functions and methods must **never exceed 30 lines** of code. Extract and modularize complex operations.
3. **Clean Interfaces:** Use Scala `trait`s for all major boundaries. The TUI and the Web API should implement a shared `View` trait.
4. **Future-Proofing:** By strict decoupling and utilizing `GameID` structures, the core Scala backend can eventually be packaged or API-exposed to serve the planned *Lichess bot* microservice without require a rewrite of the game rules. And and AI local bot as a second player.

## 5. UI/UX Design System (Frontend)
- **Color Palette:** Warm earth tones (`#BA6D4B`, `#5A2C28`, `#F3C8A0`) mixed with cool pastels (`#B9DAD1`, `#B9C2DA`, `#C19EF5`, `#E1EAA9`).
- **Typography:** `Comic Sans MS`.
- **Shapes:** Semi-soft edges (`border-radius: 8px-12px`). No harsh, boxy designs.