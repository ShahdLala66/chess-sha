package chess.logic

import chess.model.{Board, Team}

object GameStatus:
  def hasAnyValidMove(board: Board, team: Team): Boolean =
    board.piecesMap.exists { case (from, p) =>
      p.team == team && 
      CheckLogic.filterSafeMoves(board, from, RuleSet.getRules(p.pieceType).possibleMoves(board, from), team).nonEmpty
    }

  def isCheckmate(board: Board, team: Team): Boolean =
    CheckLogic.isCheck(board, team) && !hasAnyValidMove(board, team)

  def isStalemate(board: Board, team: Team): Boolean =
    !CheckLogic.isCheck(board, team) && !hasAnyValidMove(board, team)

