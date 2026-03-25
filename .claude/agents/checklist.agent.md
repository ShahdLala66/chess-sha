---
name: checklist
description: A checklist agent to track the lifecycle of features across the Arabian Chess project.
tools: Read, Grep, Glob, Bash, Todo, Agent, edit, write
---

Here is a comprehensive master TODO list for the entire Arabian Chess project, formatted as a table so you can track the lifecycle of every feature across the backend logic, tests, interfaces, and future integrations. 


### ♟️ Arabian Chess - Master feature tracker

| Feature / Component | Logic (Backend) | Spec (Tests) | TUI (Terminal) | WebView (Angular) | DB / Future |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **Project Infrastructure** |
| Empty SBT / Scala 3 Setup | [x] | [x] | N/A | N/A | N/A |
| Spring Boot Initialization | [ ] | [ ] | N/A | [ ] | [ ] |
| Angular Setup & Assets map | N/A | N/A | N/A | [ ] | N/A |
| MVC Observer Controller | [ ] | [ ] | [ ] | [ ] | N/A |
| **Core Game & Board** |
| Basic Types (Team, Piece) | [x] | [x] | N/A | N/A | [ ] |
| Initial 32-Piece Setup | [ ] | [x] | [ ] | [ ] | [ ] |
| Coordinate System (A-H, 1-8) | [ ] | [ ] | [ ] | [ ] | [ ] |
| **Standard Piece Movements** |
| Pawn (Standard & Double) | [ ] | [ ] | [ ] | [ ] | N/A |
| Knight (L-Shape) | [ ] | [ ] | [ ] | [ ] | N/A |
| Bishop (Diagonals) | [ ] | [ ] | [ ] | [ ] | N/A |
| Rook (Straight lines) | [ ] | [ ] | [ ] | [ ] | N/A |
| Queen (All directions) | [ ] | [ ] | [ ] | [ ] | N/A |
| King (Single steps) | [ ] | [ ] | [ ] | [ ] | N/A |
| **Special Rules & Edge Cases** |
| En Passant | [ ] | [ ] | [ ] | [ ] | N/A |
| Pawn Promotion | [ ] | [ ] | [ ] | [ ] | N/A |
| Castling (King/Queen-side) | [ ] | [ ] | [ ] | [ ] | N/A |
| Check Detection | [ ] | [ ] | [ ] | [ ] | N/A |
| Checkmate Logic | [ ] | [ ] | [ ] | [ ] | [ ] |
| Stalemate (Draw) | [ ] | [ ] | [ ] | [ ] | [ ] |
| 50-Move Rule (Draw) | [ ] | [ ] | [ ] | [ ] | [ ] |
| 3-Fold Repetition (Draw) | [ ] | [ ] | [ ] | [ ] | [ ] |
| Insufficient Material (Draw) | [ ] | [ ] | [ ] | [ ] | [ ] |
| **Game State & Scalability** |
| Turn Management (White->Black) | [ ] | [ ] | [ ] | [ ] | [ ] |
| Move History Log | [ ] | [ ] | [ ] | [ ] | [ ] |
| Multi-Session logic (`GameID`) | [ ] | [ ] | N/A | [ ] | [ ] |
| Play vs. Play (Local) | [ ] | [ ] | [ ] | [ ] | N/A |
| **User Interfaces** |
| Render Board State/Sprites | N/A | N/A | [ ] | [ ] | N/A |
| Parse User Moves (e.g. `e2e4`) | N/A | [ ] | [ ] | [ ] | N/A |
| Broadcast Updates (Observer) | [ ] | [ ] | [ ] | [ ] | N/A |
| Easy Disable switch | N/A | N/A | [ ] | N/A | N/A |
| Apply Custom UI Design System | N/A | N/A | N/A | [ ] | N/A |
| **Future Integrations** |
| PostgreSQL / Mongo Setup | [ ] | [ ] | N/A | N/A | [ ] |
| Save / Load Matches | [ ] | [ ] | [ ] | [ ] | [ ] |
| User Authentication | [ ] | [ ] | n/A | [ ] | [ ] |
| Lichess Bot Microservice | [ ] | [ ] | N/A | N/A | [ ] |
| AI BOT as a second Player | [ ] | [ ] | N/A | N/A | [ ] |

*Legend: `[x]` = Complete, `[ ]` = To Do, `N/A` = Not Applicable to this module.*