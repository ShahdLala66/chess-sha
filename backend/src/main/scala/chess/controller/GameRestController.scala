package chess.controller

import chess.model.{Position, GameID}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chess")
class GameRestController:
  @Autowired
  private val mainController: MainController = null

  @PostMapping("/games")
  def createGame(): GameID =
    mainController.createGame()

  @PostMapping("/games/{gameId}/move")
  def makeMove(
    @PathVariable gameId: GameID,
    @RequestParam from: String,
    @RequestParam to: String
  ): Unit =
    val fromPos = parsePosition(from)
    val toPos = parsePosition(to)
    mainController.movePiece(gameId, fromPos, toPos)

  private def parsePosition(notation: String): Position =
    val x = notation.charAt(0) - 'a'
    val y = notation.charAt(1).asDigit - 1
    Position(x, y)
