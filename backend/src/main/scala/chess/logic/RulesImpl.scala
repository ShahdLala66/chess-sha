package chess.logic

import chess.model.{Board, Position, Team, PieceType}
import MoveHelpers._

object RookRules extends PieceRules:
  def possibleMoves(board: Board, from: Position): Set[Position] =
    board.piecesMap.get(from) match
      case None => Set.empty
      case Some(p) =>
        List((1,0), (-1,0), (0,1), (0,-1))
          .flatMap(dir => raycast(board, from, dir, p.team)).toSet

object BishopRules extends PieceRules:
  def possibleMoves(board: Board, from: Position): Set[Position] =
    board.piecesMap.get(from) match
      case None => Set.empty
      case Some(p) =>
        List((1,1), (1,-1), (-1,1), (-1,-1))
          .flatMap(dir => raycast(board, from, dir, p.team)).toSet

object QueenRules extends PieceRules:
  def possibleMoves(board: Board, from: Position): Set[Position] =
    board.piecesMap.get(from) match
      case None => Set.empty
      case Some(p) =>
        getDirections().flatMap(dir => raycast(board, from, dir, p.team)).toSet

object KnightRules extends PieceRules:
  def possibleMoves(board: Board, from: Position): Set[Position] =
    board.piecesMap.get(from) match
      case None => Set.empty
      case Some(p) =>
        val jumps = List((2,1), (2,-1), (-2,1), (-2,-1), (1,2), (1,-2), (-1,2), (-1,-2))
        jumps.map((dx, dy) => Position(from.x + dx, from.y + dy))
          .filter(isValidPos)
          .filter(pos => !board.piecesMap.get(pos).exists(_.team == p.team))
          .toSet

object KingRules extends PieceRules:
  def possibleMoves(board: Board, from: Position): Set[Position] =
    board.piecesMap.get(from) match
      case None => Set.empty
      case Some(p) =>
        getDirections().map((dx, dy) => Position(from.x + dx, from.y + dy))
          .filter(isValidPos)
          .filter(pos => !board.piecesMap.get(pos).exists(_.team == p.team))
          .toSet

object PawnRules extends PieceRules:
  def possibleMoves(board: Board, from: Position): Set[Position] =
    board.piecesMap.get(from) match
      case None => Set.empty
      case Some(p) =>
        val dir = if p.team == Team.White then 1 else -1 // Assuming white moves positive y
        val startRow = if p.team == Team.White then 1 else 6
        var moves = Set.empty[Position]
        
        val forward1 = Position(from.x, from.y + dir)
        if isValidPos(forward1) && !board.piecesMap.contains(forward1) then
          moves += forward1
          val forward2 = Position(from.x, from.y + 2 * dir)
          if from.y == startRow && !board.piecesMap.contains(forward2) then
             moves += forward2
        
        List(-1, 1).foreach { dx =>
          val cap = Position(from.x + dx, from.y + dir)
          if isValidPos(cap) && board.piecesMap.get(cap).exists(_.team != p.team) then
            moves += cap
        }
        
        moves

