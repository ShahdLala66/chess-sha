package chess.model

case class Move(from: Position, to: Position, piece: Piece, captured: Option[Piece], isEnPassant: Boolean = false, isCastling: Boolean = false, promotion: Option[PieceType] = None)

