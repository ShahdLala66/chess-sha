package chess.model

case class Board(piecesMap: Map[Position, Piece]):
  def pieces: List[Piece] = piecesMap.values.toList

object Board:
  def initial(): Board = 
    val backRow = Map(
      0 -> PieceType.Rook, 1 -> PieceType.Knight, 2 -> PieceType.Bishop, 3 -> PieceType.Queen,
      4 -> PieceType.King, 5 -> PieceType.Bishop, 6 -> PieceType.Knight, 7 -> PieceType.Rook
    )
    val white = generateTeam(Team.White, 0, 1, backRow)
    val black = generateTeam(Team.Black, 7, 6, backRow)
    Board(white ++ black)

  private def generateTeam(team: Team, r: Int, pR: Int, layout: Map[Int, PieceType]): Map[Position, Piece] =
    val back = (0 to 7).map(x => Position(x, r) -> Piece(layout(x), team)).toMap
    val pawns = (0 to 7).map(x => Position(x, pR) -> Piece(PieceType.Pawn, team)).toMap
    back ++ pawns

