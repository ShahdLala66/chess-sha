package chess.model

enum GameEvent:
  case GameStarted(board: Board)
  case MoveMade(board: Board, from: Position, to: Position)
  case InvalidMove(reason: String)
  case Check(team: Team)
  case Checkmate(winner: Team)
  case Draw(reason: String)
