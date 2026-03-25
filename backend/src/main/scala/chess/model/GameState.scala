package chess.model

import chess.view.ViewObserver

case class GameState(
  gameId: GameID,
  var board: Board,
  var views: List[ViewObserver] = List.empty,
  var turn: Team = Team.White,
  var moveHistory: List[Move] = List.empty
):
  def addMove(move: Move): Unit =
    moveHistory = move :: moveHistory
    turn = turn.opposite

