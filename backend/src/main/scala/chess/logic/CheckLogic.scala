package chess.logic

import chess.model.{Board, Position, Team, PieceType}

object CheckLogic:
  def getKingPos(board: Board, team: Team): Option[Position] =
    board.piecesMap.find { case (_, p) => p.pieceType == PieceType.King && p.team == team }.map(_._1)

  def isUnderAttack(board: Board, pos: Position, byTeam: Team): Boolean =
    board.piecesMap.exists { case (from, p) =>
      p.team == byTeam && RuleSet.getRules(p.pieceType).possibleMoves(board, from).contains(pos)
    }

  def isCheck(board: Board, team: Team): Boolean =
    getKingPos(board, team).exists(k => isUnderAttack(board, k, team.opposite))

  def filterSafeMoves(board: Board, from: Position, moves: Set[Position], team: Team): Set[Position] =
    moves.filter { to =>
      val p = board.piecesMap(from)
      val newBoard = Board(board.piecesMap - from + (to -> p))
      !isCheck(newBoard, team)
    }

