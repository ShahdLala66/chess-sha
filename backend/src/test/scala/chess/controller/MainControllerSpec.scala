package chess.controller

import chess.model.{Board, Position, GameEvent, GameID}
import chess.view.ViewObserver
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import scala.collection.mutable

class MainControllerSpec extends AnyWordSpec with Matchers {

  "MainController" should {
    
    "create a new game and return a unique GameID" in {
      val controller = MainController()
      val gameId1 = controller.createGame()
      val gameId2 = controller.createGame()
      
      gameId1 should not be gameId2
    }

    "addObserver a view to a game and notify it with GameStarted event" in {
      val controller = MainController()
      val gameId = controller.createGame()
      val mockView = MockViewObserver()
      
      controller.addObserver(gameId, mockView)
      
      mockView.events.length shouldBe 1
      mockView.events(0) match
        case GameEvent.GameStarted(board) => 
          board.pieces.size shouldBe 32
        case _ => fail("Expected GameStarted event")
    }

    "process a valid move and notify all views" in {
      val controller = MainController()
      val gameId = controller.createGame()
      val mockView = MockViewObserver()
      
      controller.addObserver(gameId, mockView)
      mockView.events.clear()
      
      // Move pawn from e2 (4,1) to e4 (4,3)
      controller.movePiece(gameId, Position(4, 1), Position(4, 3))
      
      mockView.events.length shouldBe 1
      mockView.events(0) match
        case GameEvent.MoveMade(board, from, to) => 
          from shouldBe Position(4, 1)
          to shouldBe Position(4, 3)
        case _ => fail("Expected MoveMade event")
    }

    "reject a move from an empty position" in {
      val controller = MainController()
      val gameId = controller.createGame()
      val mockView = MockViewObserver()
      
      controller.addObserver(gameId, mockView)
      mockView.events.clear()
      
      // Try moving from an empty square (3,3)
      controller.movePiece(gameId, Position(3, 3), Position(3, 4))
      
      mockView.events.length shouldBe 1
      mockView.events(0) match
        case GameEvent.InvalidMove(reason) => 
          reason shouldBe "No piece at position"
        case _ => fail("Expected InvalidMove event")
    }

    "support multiple independent game sessions" in {
      val controller = MainController()
      val gameId1 = controller.createGame()
      val gameId2 = controller.createGame()
      
      val mockView1 = MockViewObserver()
      val mockView2 = MockViewObserver()
      
      controller.addObserver(gameId1, mockView1)
      controller.addObserver(gameId2, mockView2)
      
      // Clear initial GameStarted events
      mockView1.events.clear()
      mockView2.events.clear()
      
      // Make move in game 1
      controller.movePiece(gameId1, Position(0, 1), Position(0, 3))
      
      // Only game 1 view should be notified
      mockView1.events.length shouldBe 1
      mockView2.events.length shouldBe 0
    }

    "broadcast move events to all views addObservered to a game" in {
      val controller = MainController()
      val gameId = controller.createGame()
      val mockView1 = MockViewObserver()
      val mockView2 = MockViewObserver()
      
      controller.addObserver(gameId, mockView1)
      controller.addObserver(gameId, mockView2)
      mockView1.events.clear()
      mockView2.events.clear()
      
      controller.movePiece(gameId, Position(1, 1), Position(1, 3))
      
      mockView1.events.length shouldBe 1
      mockView2.events.length shouldBe 1
    }

    "handle moves to non-existent game gracefully" in {
      val controller = MainController()
      val fakeGameId = GameID("non-existent-id")
      
      // Should not throw exception
      controller.movePiece(fakeGameId, Position(0, 1), Position(0, 3))
    }

    "addObserver view to non-existent game gracefully" in {
      val controller = MainController()
      val fakeGameId = GameID("non-existent-id")
      val mockView = MockViewObserver()
      
      // Should not throw exception
      controller.addObserver(fakeGameId, mockView)
      
      mockView.events.length shouldBe 0
    }
  }
}

// Mock observer for testing
class MockViewObserver extends ViewObserver:
  val events: mutable.ListBuffer[GameEvent] = mutable.ListBuffer.empty
  
  def update(event: GameEvent): Unit =
    events += event
