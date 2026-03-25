package chess.view.tui

import chess.model.{Board, Position, Piece, PieceType, Team}

object BoardRenderer:
  private val pieces = Map(
    (PieceType.Pawn, Team.White) -> "♙",
    (PieceType.Knight, Team.White) -> "♘",
    (PieceType.Bishop, Team.White) -> "♗",
    (PieceType.Rook, Team.White) -> "♖",
    (PieceType.Queen, Team.White) -> "♕",
    (PieceType.King, Team.White) -> "♔",
    (PieceType.Pawn, Team.Black) -> "♟",
    (PieceType.Knight, Team.Black) -> "♞",
    (PieceType.Bishop, Team.Black) -> "♝",
    (PieceType.Rook, Team.Black) -> "♜",
    (PieceType.Queen, Team.Black) -> "♛",
    (PieceType.King, Team.Black) -> "♚"
  )

  def render(board: Board): String =
    val sb = new StringBuilder()
    sb.append("  a b c d e f g h\n")
    
    for y <- 7 to 0 by -1 do
      sb.append(s"${y + 1} ")
      for x <- 0 to 7 do
        val pos = Position(x, y)
        val square = if (x + y) % 2 == 0 then "." else " "
        board.piecesMap.get(pos) match
          case Some(p) => sb.append(pieces((p.pieceType, p.team)))
          case None => sb.append(square)
        sb.append(" ")
      sb.append(s"${y + 1}\n")
    
    sb.append("  a b c d e f g h\n")
    sb.toString()
