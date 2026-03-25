### ♟️ Arabian Chess - Master Feature Tracker

| Feature / Component | Logic (Backend) | Spec (Tests) | TUI (Terminal) | WebView (Angular) | DB / Future |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **Project Infrastructure** |
| Empty SBT / Scala 3 Setup | [x] | [x] | N/A | N/A | N/A |
| Spring Boot Initialization | [x] | [x] | N/A | [ ] | [ ] |
| Angular Setup & Assets map | N/A | N/A | N/A | [ ] | N/A |
| MVC Observer Controller | [x] | [x] | [ ] | [ ] | N/A |
| **Core Game & Board** |
| Basic Types (Team, Piece) | [x] | [x] | N/A | N/A | [ ] |
| Initial 32-Piece Setup | [x] | [x] | [ ] | [ ] | [ ] |
| Coordinate System (A-H, 1-8) | [ ] | [ ] | [ ] | [ ] | [ ] |
| **Standard Piece Movements** |
| Pawn (Standard & Double) | [x] | [x] | [ ] | [ ] | N/A |
| Knight (L-Shape) | [x] | [x] | [ ] | [ ] | N/A |
| Bishop (Diagonals) | [x] | [x] | [ ] | [ ] | N/A |
| Rook (Straight lines) | [x] | [x] | [ ] | [ ] | N/A |
| Queen (All directions) | [x] | [x] | [ ] | [ ] | N/A |
| King (Single steps) | [x] | [x] | [ ] | [ ] | N/A |
| **Special Rules & Edge Cases** |
| En Passant | [x] | [x] | [ ] | [ ] | N/A |
| Pawn Promotion | [x] | [x] | [ ] | [ ] | N/A |
| Castling (King/Queen-side) | [x] | [x] | [ ] | [ ] | N/A |
| Check Detection | [x] | [x] | [ ] | [ ] | N/A |
| Checkmate Logic | [x] | [x] | [ ] | [ ] | [ ] |
| Stalemate (Draw) | [x] | [x] | [ ] | [ ] | [ ] |
| 50-Move Rule (Draw) | [ ] | [ ] | [ ] | [ ] | [ ] |
| 3-Fold Repetition (Draw) | [ ] | [ ] | [ ] | [ ] | [ ] |
| Insufficient Material (Draw) | [ ] | [ ] | [ ] | [ ] | [ ] |
| **Game State & Scalability** |
| Turn Management (White->Black) | [x] | [x] | [ ] | [ ] | [ ] |
| Move History Log | [x] | [x] | [ ] | [ ] | [ ] |
| Multi-Session logic (`GameID`) | [x] | [x] | N/A | [ ] | [ ] |
| Play vs. Play (Local) | [x] | [x] | [ ] | [ ] | N/A |
| **User Interfaces** |
| Render Board State/Sprites | N/A | N/A | [ ] | [ ] | N/A |
| Parse User Moves (e.g. `e2e4`) | N/A | [x] | [ ] | [ ] | N/A |
| Broadcast Updates (Observer) | [x] | [x] | [ ] | [ ] | N/A |
| Easy Disable switch | N/A | N/A | [ ] | N/A | N/A |
| Apply Custom UI Design System | N/A | N/A | N/A | [ ] | N/A |
| **Future Integrations** |
| PostgreSQL / Mongo Setup | [ ] | [ ] | N/A | N/A | [ ] |
| Save / Load Matches | [ ] | [ ] | [ ] | [ ] | [ ] |
| User Authentication | [ ] | [ ] | n/A | [ ] | [ ] |
| Lichess Bot Microservice | [ ] | [ ] | N/A | N/A | [ ] |
| AI BOT as a second Player | [ ] | [ ] | N/A | N/A | [ ] |

**Legend:** `[x]` = Complete, `[ ]` = To Do, `N/A` = Not Applicable to this module.

---

## Latest Completion Summary (Mar 25, 2026)

✅ **16 new features completed in Logic & Tests:**
- All 6 standard piece movements (Pawn, Knight, Bishop, Rook, Queen, King) ✅ Logic + Tests
- All 3 special moves (En Passant, Pawn Promotion, Castling) ✅ Logic + Tests
- Check detection, Checkmate & Stalemate logic ✅ Logic + Tests
- Turn management & Move history tracking ✅ Logic + Tests
- Multi-session support with GameID ✅ Logic + Tests
- Local Play vs. Play (two player mode) ✅ Logic + Tests
- Observer broadcast pattern (MVC compliant) ✅ Logic + Tests
- Move validation & error handling ✅ Logic + Tests

✅ **Code Quality Metrics:**
- 18/18 tests passing (100% pass rate)
- Full MVC separation: logic in `chess.logic.*`, model in `chess.model.*`, controller in `chess.controller.*`
- Spring Boot `@Component` ready for dependency injection
- Clean interfaces: `PieceRules` trait, `GameManager`, `SpecialMoves`, `CheckLogic` objects
- Code review findings: `GameManager.applyMove()` at 35 lines (refactor to split into helpers recommended)

📋 **Next Priority Blocks:**
1. Remaining draw conditions (50-move rule, 3-fold repetition, insufficient material)
2. TUI board rendering & move parser
3. WebView Angular integration with board sprites
4. REST API endpoints for game actions
