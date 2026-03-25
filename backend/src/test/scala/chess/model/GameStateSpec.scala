package chess.model

import chess.view.ViewObserver
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import scala.collection.mutable

class GameStateSpec extends AnyWordSpec with Matchers {

  "GameState" should {
    
    "be initialized with a GameID and Board" in {
      val gameId = GameID.generate()
      val board = Board.initial()
      val state = GameState(gameId, board)
      
      state.gameId shouldBe gameId
      state.board shouldBe board
      state.views.length shouldBe 0
    }

    "allow adding views" in {
      val gameId = GameID.generate()
      val state = GameState(gameId, Board.initial())
      val mockView = MockViewForState()
      
      state.views = mockView :: state.views
      
      state.views.length shouldBe 1
    }

    "update board state" in {
      val gameId = GameID.generate()
      val initialBoard = Board.initial()
      val state = GameState(gameId, initialBoard)
      
      state.board.pieces.size shouldBe 32
      
      // Simulate moving a piece
      val newBoard = Board(initialBoard.piecesMap - Position(0, 1) + (Position(0, 3) -> Piece(PieceType.Pawn, Team.White)))
      state.board = newBoard
      
      state.board.pieces.size shouldBe 32
    }
  }
}

// Mock observer for testing
class MockViewForState extends ViewObserver:
  def update(event: GameEvent): Unit = ()
