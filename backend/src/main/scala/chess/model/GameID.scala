package chess.model

opaque type GameID = String

object GameID:
  def apply(id: String): GameID = id
  def generate(): GameID = java.util.UUID.randomUUID().toString()
