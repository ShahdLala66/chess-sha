package chess.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class InitialSetupSpec extends AnyWordSpec with Matchers {

  "A Chess Game Setup" should {
    
    "define two distinct teams (White and Black)" in {
      Team.White should not be (Team.Black)
    }

    "correctly define a Piece with a Type and a Team" in {
      val whitePawn = Piece(PieceType.Pawn, Team.White)
      whitePawn.team shouldBe Team.White
      whitePawn.pieceType shouldBe PieceType.Pawn
    }

    "initialize a board with 32 pieces for a standard game" in {
      val board = Board.initial()
      board.pieces.size shouldBe 32
    }

    "have 16 pieces for each team on the initial board" in {
      val board = Board.initial()
      board.pieces.count(_.team == Team.White) shouldBe 16
      board.pieces.count(_.team == Team.Black) shouldBe 16
    }
  }
}
