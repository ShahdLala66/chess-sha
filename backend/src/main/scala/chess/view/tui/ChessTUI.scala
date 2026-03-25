package chess.view.tui

import chess.model.{GameEvent, GameID, Position}
import chess.view.ViewObserver
import chess.controller.MainController
import scala.io.StdIn

class ChessTUI(controller: MainController, gameId: GameID) extends ViewObserver:
  private var isRunning = false

  def start(): Unit =
    isRunning = true
    println("♟️  Welcome to Arabian Chess! ♟️")
    println("Enter moves in algebraic notation (e.g., e2e4)")
    println("Type 'quit' to exit\n")
    gameLoop()

  private def gameLoop(): Unit =
    while isRunning do
      controller.getGameState(gameId).foreach { state =>
        println("\n" + BoardRenderer.render(state.board))
        println(s"Current turn: ${state.turn}")
        
        val input = StdIn.readLine("Enter move: ").trim
        if input == "quit" then
          isRunning = false
          println("Thanks for playing!")
        else if input.nonEmpty then
          MoveParser.parse(input) match
            case Some((from, to)) =>
              controller.movePiece(gameId, from, to)
            case None =>
              println(s"Invalid move format: $input. Use format like 'e2e4'")
      }

  def update(event: GameEvent): Unit =
    event match
      case GameEvent.GameStarted(board) =>
        println("\n🎮 Game Started!")
      case GameEvent.MoveMade(board, from, to) =>
        println(s"✓ Move: ${posToNotation(from)} → ${posToNotation(to)}")
      case GameEvent.InvalidMove(reason) =>
        println(s"✗ Invalid move: $reason")
      case GameEvent.Check(team) =>
        println(s"⚠️  ${team} is in check!")
      case GameEvent.Checkmate(winner) =>
        println(s"♔ Checkmate! ${winner} wins!")
        isRunning = false
      case GameEvent.Draw(reason) =>
        println(s"🤝 Draw: $reason")
        isRunning = false

  private def posToNotation(pos: Position): String =
    s"${('a' + pos.x).toChar}${pos.y + 1}"
