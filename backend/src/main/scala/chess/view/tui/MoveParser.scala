package chess.view.tui

import chess.model.Position

object MoveParser:
  def parse(input: String): Option[(Position, Position)] =
    val cleaned = input.toLowerCase().trim
    if cleaned.length < 4 then return None
    
    val from = for
      x <- fileToX(cleaned(0))
      y <- rankToY(cleaned(1))
    yield Position(x, y)
    
    val to = for
      x <- fileToX(cleaned(2))
      y <- rankToY(cleaned(3))
    yield Position(x, y)
    
    for
      f <- from
      t <- to
    yield (f, t)

  private def fileToX(c: Char): Option[Int] =
    if c >= 'a' && c <= 'h' then Some(c - 'a') else None

  private def rankToY(c: Char): Option[Int] =
    if c >= '1' && c <= '8' then Some(c - '1') else None
