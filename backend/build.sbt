name := "arabian-chess-backend"
version := "0.1.0-SNAPSHOT"
scalaVersion := "3.3.1"

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-web" % "3.2.3",
  "org.scalatest" %% "scalatest" % "3.2.17" % Test
)

coverageMinimumStmtTotal := 100
coverageFailOnMinimum := false
