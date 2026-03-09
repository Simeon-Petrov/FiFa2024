# FIFA 2024 — Players Played Together for the Longest Time

## Task Description

The application identifies the pair of football players who have played together in common matches for the longest cumulative time, and shows the duration for each of those matches.

---

## Input Data

Four CSV files located in the `csv/` folder:

- `players.csv` — `ID, TeamNumber, Position, FullName, TeamID`
- `teams.csv` — `ID, Name, ManagerFullName, Group`
- `matches.csv` — `ID, ATeamID, BTeamID, Date, Score`
- `records.csv` — `ID, PlayerID, MatchID, FromMinutes, ToMinutes`

---

## Algorithm

1. **Load CSV data** — parse all four files into Java model objects.
2. **Resolve NULL toMinutes** — if `toMinutes` is NULL, it is treated as the end of the match:
   - 90 minutes for regular time
   - 120 minutes if the score contains `(` indicating extra time
3. **Group records by match** — collect all player records per match.
4. **Find overlapping time for each pair** — for every pair of players in the same match, calculate how many minutes they were both on the field:

```
overlapStart = max(fromMinutes_A, fromMinutes_B)
overlapEnd   = min(toMinutes_A,   toMinutes_B)
overlap      = max(0, overlapEnd - overlapStart)
```

5. **Accumulate across all matches** — sum the overlap minutes for each unique player pair across all matches.
6. **Find the top pair(s)** — return the pair(s) with the highest total minutes together, along with a per-match breakdown.

---

## Output Format

```
Player1ID, Player2ID, TotalMinutes
MatchID, MinutesTogether
MatchID, MinutesTogether
...
```

---

## Project Structure

```
FiFa2024/
├── csv/
│   ├── players.csv
│   ├── teams.csv
│   ├── matches.csv
│   └── records.csv
├── src/main/java/com/sirma/
│   ├── Main.java
│   ├── model/
│   │   ├── Player.java
│   │   ├── Team.java
│   │   ├── Match.java
│   │   └── Record.java
│   ├── parser/
│   │   ├── CsvReader.java
│   │   └── CsvParser.java
│   ├── service/
│   │   └── PlayerPairService.java
│   ├── util/
│   │   └── DateParser.java
│   └── database/
│       ├── DatabaseManager.java
│       ├── DatabaseWriter.java
│       ├── PlayerRepository.java
│       ├── TeamRepository.java
│       ├── MatchRepository.java
│       └── CrudMenu.java
├── config.properties        ← not tracked by Git
├── .gitignore
└── pom.xml
```

---

## Date Formats Supported

The application supports 13 date formats, including:
`M/d/yyyy`, `MM/dd/yyyy`, `yyyy-MM-dd`, `yyyy/MM/dd`, `dd.MM.yyyy`, `dd-MM-yyyy`, `dd/MM/yyyy` and more.

All dates are stored in a unified format: `dd-MM-yyyy`.

---

## Database

Data is persisted in a **PostgreSQL** database (`fifa2024`).

Tables: `teams`, `players`, `matches`, `records`.

Connection is configured in `config.properties`.

The project uses the PostgreSQL JDBC driver — `postgresql-42.7.10.jar` must be on the classpath.

When running with `java`, load it explicitly:

```bash
# Windows
java -cp "FiFa2024.jar;postgresql-42.7.10.jar" com.sirma.Main

# Linux/macOS
java -cp "FiFa2024.jar:postgresql-42.7.10.jar" com.sirma.Main
```

---

## Bonus — CRUD Operations

Interactive console menu for managing:

- Players (add, view, update, delete)
- Teams (add, view, update, delete)
- Matches (add, view, update, delete)

---

## Requirements

- Java 21
- PostgreSQL
- Maven

---

## Configuration

Edit `config.properties` to set your database connection:

```properties
db.url=jdbc:postgresql://localhost:5432/fifa2024
db.user=your_user
db.password=your_password
```

> **Note:** `config.properties` is listed in `.gitignore` and is not tracked by Git.

---

## Author

**Simeon Petrov**
