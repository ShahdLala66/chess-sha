package chess

import chess.controller.MainController
import chess.view.tui.ChessTUI

@main def runChess(): Unit =
  val controller = MainController()
  val gameId = controller.createGame()
  val tui = ChessTUI(controller, gameId)
  controller.addObserver(gameId, tui)
  tui.start()
