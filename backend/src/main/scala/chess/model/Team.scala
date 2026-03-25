package chess.model

enum Team:
  case White, Black

  def opposite: Team = this match
    case White => Black
    case Black => White

