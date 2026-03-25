package chess.logic

import chess.model.{GameState, Position, Board, Team, PieceType, Move}

object GameManager:
  def getValidMovesFor(gs: GameState, from: Position): Set[Position] =
    gs.board.piecesMap.get(from) match
      case None => Set.empty
      case Some(p) if p.team != gs.turn => Set.empty
      case Some(p) =>
        val standardMoves = RuleSet.getRules(p.pieceType).possibleMoves(gs.board, from)
        var allMoves = CheckLogic.filterSafeMoves(gs.board, from, standardMoves, p.team)
        
        if p.pieceType == PieceType.Pawn then
          SpecialMoves.getEnPassant(gs, from).foreach { ep =>
            // Double check safety
            val capturedPawnPos = Position(ep.x, from.y)
            val newBoardMap = gs.board.piecesMap - from - capturedPawnPos + (ep -> p)
            if !CheckLogic.isCheck(Board(newBoardMap), p.team) then
              allMoves += ep
          }
        
        if p.pieceType == PieceType.King then
          if SpecialMoves.canCastle(gs, from, kingSide = true) then allMoves += Position(6, from.y)
          if SpecialMoves.canCastle(gs, from, kingSide = false) then allMoves += Position(2, from.y)
          
        allMoves

  def applyMove(gs: GameState, from: Position, to: Position, promoObj: Option[PieceType] = None): Either[String, GameState] =
    if gs.board.piecesMap.get(from).isEmpty then return Left("No piece at position")
    
    val validMoves = getValidMovesFor(gs, from)
    if !validMoves.contains(to) then return Left("Invalid move")
    
    val p = gs.board.piecesMap(from)
    var captured = gs.board.piecesMap.get(to)
    var newMap = gs.board.piecesMap - from + (to -> p)
    
    var isEP = false
    if p.pieceType == PieceType.Pawn && to.x != from.x && captured.isEmpty then
      val capPos = Position(to.x, from.y)
      captured = gs.board.piecesMap.get(capPos)
      newMap = newMap - capPos
      isEP = true
      
    var isCastle = false
    if p.pieceType == PieceType.King && Math.abs(to.x - from.x) == 2 then
      isCastle = true
      val kingSide = to.x == 6
      val oldRookX = if kingSide then 7 else 0
      val newRookX = if kingSide then 5 else 3
      val rook = newMap(Position(oldRookX, from.y))
      newMap = newMap - Position(oldRookX, from.y) + (Position(newRookX, from.y) -> rook)
      
    if p.pieceType == PieceType.Pawn && (to.y == 0 || to.y == 7) then
      val promotedPiece = p.copy(pieceType = promoObj.getOrElse(PieceType.Queen))
      newMap = newMap + (to -> promotedPiece)
      
    val newBoard = Board(newMap)
    val move = Move(from, to, p, captured, isEP, isCastle, promoObj)
    
    gs.board = newBoard
    gs.addMove(move)
    Right(gs)

