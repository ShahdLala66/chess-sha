# Running Tests

## Quick Start
```bash
cd /home/shahd/chess/backend
sbt test
```

## Specific Test Suites
```bash
sbt "testOnly chess.controller.MainControllerSpec"
sbt "testOnly chess.model.GameIDSpec"
sbt "testOnly chess.model.GameStateSpec"
sbt "testOnly chess.model.InitialSetupSpec"
```

## Coverage Report
```bash
sbt clean coverage test coverageReport
```

**Report Location:**
```
/home/shahd/chess/backend/target/scala-3.3.1/scoverage-report/index.html
```

## Watch Mode (Auto-run on changes)
```bash
sbt ~test
```

## Test Summary
- **Total Tests:** 17
- **Coverage Target:** 100%
- **Framework:** ScalaTest 3.2.17
