package chess.controller

import chess.model.{GameID, GameState, Board, Position, Move, GameEvent, PieceType}
import chess.logic.{GameManager, GameStatus, CheckLogic}
import chess.view.ViewObserver
import scala.collection.mutable

class MainController:
  private val activeGames = mutable.Map.empty[GameID, GameState]

  def createGame(): GameID =
    val id = GameID.generate()
    val state = GameState(id, Board.initial())
    activeGames(id) = state
    notifyViews(id, GameEvent.GameStarted(state.board))
    id

  def getGameState(id: GameID): Option[GameState] = activeGames.get(id)

  def addObserver(id: GameID, view: ViewObserver): Unit =
    activeGames.get(id).foreach { state =>
      state.views = view :: state.views
      view.update(GameEvent.GameStarted(state.board))
    }

  def movePiece(id: GameID, from: Position, to: Position, promotion: Option[PieceType] = None): Unit =
    activeGames.get(id) match
      case Some(state) =>
        GameManager.applyMove(state, from, to, promotion) match
          case Right(newState) =>
            activeGames(id) = newState
            notifyViews(id, GameEvent.MoveMade(newState.board, from, to))
            
            val opponent = newState.turn
            if GameStatus.isCheckmate(newState.board, opponent) then
              notifyViews(id, GameEvent.Checkmate(opponent.opposite))
            else if GameStatus.isStalemate(newState.board, opponent) then
              notifyViews(id, GameEvent.Draw("Stalemate"))
            else if CheckLogic.isCheck(newState.board, opponent) then
              notifyViews(id, GameEvent.Check(opponent))

          case Left(reason) =>
            notifyViews(id, GameEvent.InvalidMove(reason))
      case None => // Game not found
        ()

  private def notifyViews(id: GameID, event: GameEvent): Unit =
    activeGames.get(id).foreach { state =>
      state.views.foreach(_.update(event))
    }

