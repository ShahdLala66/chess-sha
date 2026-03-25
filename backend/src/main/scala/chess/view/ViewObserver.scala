package chess.view

import chess.model.GameEvent

// Clean interface for any View (TUI, Web, etc.) to implement
trait ViewObserver:
  def update(event: GameEvent): Unit
