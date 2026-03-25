package chess.logic

import chess.model.{Board, Position, Piece, PieceType, Team}

trait PieceRules:
  def possibleMoves(board: Board, from: Position): Set[Position]

object RuleSet:
  def getRules(pt: PieceType): PieceRules = pt match
    case PieceType.Pawn   => PawnRules
    case PieceType.Knight => KnightRules
    case PieceType.Bishop => BishopRules
    case PieceType.Rook   => RookRules
    case PieceType.Queen  => QueenRules
    case PieceType.King   => KingRules

