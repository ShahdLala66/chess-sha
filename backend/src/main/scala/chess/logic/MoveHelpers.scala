package chess.logic

import chess.model.{Board, Position, Team}

object MoveHelpers:
  def raycast(board: Board, from: Position, dir: (Int, Int), team: Team): Set[Position] =
    val (dx, dy) = dir
    val next = Position(from.x + dx, from.y + dy)
    
    if !isValidPos(next) then Set.empty
    else
      board.piecesMap.get(next) match
        case None => Set(next) ++ raycast(board, next, dir, team)
        case Some(p) if p.team != team => Set(next)
        case _ => Set.empty

  def isValidPos(pos: Position): Boolean =
    pos.x >= 0 && pos.x < 8 && pos.y >= 0 && pos.y < 8

  def getDirections(): List[(Int, Int)] =
    List((1,1), (1,-1), (-1,1), (-1,-1), (1,0), (-1,0), (0,1), (0,-1))

