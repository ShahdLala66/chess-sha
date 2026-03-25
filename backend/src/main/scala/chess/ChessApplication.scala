package chess

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import chess.controller.MainController

@SpringBootApplication
class ChessApplication:
  @Bean
  def mainController(): MainController = MainController()

object ChessApplication:
  // THE ONLY MAIN METHOD in the entire application
  // Starts Spring Boot server. Views (TUI/WebView) connect and request game sessions via REST API.
  // The Controller does NOT auto-create a game; views request session creation.
  def main(args: Array[String]): Unit =
    SpringApplication.run(classOf[ChessApplication], args: _*)
