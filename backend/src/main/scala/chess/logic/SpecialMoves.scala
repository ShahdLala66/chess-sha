package chess.logic

import chess.model.{Board, GameState, Position, Move, Team, PieceType, Piece}

object SpecialMoves:
  def getEnPassant(gs: GameState, from: Position): Option[Position] =
    gs.board.piecesMap.get(from).filter(_.pieceType == PieceType.Pawn).flatMap { p =>
      gs.moveHistory.headOption.flatMap { lastMove =>
        val dir = if p.team == Team.White then 1 else -1
        if lastMove.piece.pieceType == PieceType.Pawn &&
           lastMove.to.y == from.y && 
           Math.abs(lastMove.from.y - lastMove.to.y) == 2 &&
           Math.abs(lastMove.to.x - from.x) == 1 then
          Some(Position(lastMove.to.x, from.y + dir))
        else None
      }
    }

  def canCastle(gs: GameState, kingPos: Position, kingSide: Boolean): Boolean =
    val board = gs.board
    val p = board.piecesMap.get(kingPos)
    if p.isEmpty || p.get.pieceType != PieceType.King || CheckLogic.isCheck(board, p.get.team) then return false
    
    val team = p.get.team
    if hasMoved(gs, kingPos, PieceType.King) then return false
    
    val row = kingPos.y
    val rookX = if kingSide then 7 else 0
    val rookPos = Position(rookX, row)
    
    if board.piecesMap.get(rookPos).exists(_.pieceType != PieceType.Rook) || hasMoved(gs, rookPos, PieceType.Rook) then return false
    
    val step = if kingSide then 1 else -1
    val endpoints = if kingSide then Seq(5, 6) else Seq(1, 2, 3)
    
    val blockedOrAttacked = endpoints.exists { x =>
      val px = Position(x, row)
      board.piecesMap.contains(px) || (x != 1 && CheckLogic.isUnderAttack(board, px, team.opposite))
    }
    
    !blockedOrAttacked

  private def hasMoved(gs: GameState, startPos: Position, pt: PieceType): Boolean =
    gs.moveHistory.exists(m => m.piece.pieceType == pt && m.from == startPos)

