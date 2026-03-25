package chess.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class GameIDSpec extends AnyWordSpec with Matchers {

  "GameID" should {
    
    "be generated with unique values" in {
      val id1 = GameID.generate()
      val id2 = GameID.generate()
      
      id1 should not be id2
    }

    "be created from a string" in {
      val id = GameID("test-game-123")
      val id2 = GameID("test-game-123")
      
      id shouldBe id2
    }

    "support equality comparison" in {
      val id1 = GameID("same-id")
      val id2 = GameID("same-id")
      
      id1 shouldBe id2
    }
  }
}
