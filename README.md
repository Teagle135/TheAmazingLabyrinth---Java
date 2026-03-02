# Java Labyrinth

Java Labyrinth is a desktop board game project built with Java Swing/AWT. It recreates the core loop of the shifting-maze puzzle game: players insert the free tile, rotate the maze, move through open corridors, collect assigned treasures, and race to finish their objectives before the other players.

## Overview

The application uses a simple MVC-style structure:

- `Application` contains the main entry point.
- `Controller` coordinates game setup, turn flow, save/load logic, and board updates.
- `Model` stores tiles, cards, players, path data, and board state.
- `View` renders the title screen, setup screen, help screens, game board, and player panels.

The game is designed for local play on a single machine and includes a graphical menu flow for:

- starting a new game,
- loading a saved game,
- viewing help/instruction screens,
- configuring player names,
- selecting how many treasure cards each player receives.

## Gameplay

Each turn follows the same sequence:

1. Rotate the free tile with the clockwise or counter-clockwise buttons.
2. Insert the free tile using one of the valid edge arrows.
3. Move the active player with the keyboard arrow keys through connected tile openings.
4. End the turn once movement is complete.

The board is randomized at the start of a new game, so each match has a different maze layout. Player progress is shown in the side panels, which display:

- the player name,
- the player avatar,
- the assigned treasure cards,
- treasure cards that have already been found,
- whose turn is currently active.

## Features

- 4-player local turn-based gameplay
- Randomized 7x7 board generation
- Rotatable free-tile insertion system
- Keyboard-based movement
- Per-player treasure card tracking
- Save and load support using CSV files
- Multiple menu and help screens
- MVC-inspired package organization

## Project Structure

```text
.
├── src
│   ├── Application
│   ├── Controller
│   ├── Model
│   └── View
├── csv
│   ├── Cards.csv
│   ├── Treasures.csv
│   └── save.csv
├── Image
└── TileImage
```

### Important directories

- `src/Application`: app startup class (`LabyringthApp`)
- `src/Controller`: main game controller, file loading, saved-game loading, and experimental AI-related classes
- `src/Model`: board, tile, player, card, and path model classes
- `src/View`: game windows, board rendering, setup UI, and help screens
- `csv`: data files for treasures, cards, and save-state storage
- `Image`: menu, board, button, and avatar assets
- `TileImage`: tile and treasure card art

## Requirements

- Java 8 or newer
- A graphical desktop environment capable of running Swing/AWT applications

## Running the Project

### From an IDE

1. Open the project folder in IntelliJ IDEA, Eclipse, or another Java IDE.
2. Make sure the working directory is the repository root so relative asset paths like `Image//...` and `csv//...` resolve correctly.
3. Run `src/Application/LabyringthApp.java`.

### From the command line

Compile:

```bash
javac -d out $(find src -name "*.java")
```

Run:

```bash
java -cp out Application.LabyringthApp
```

## Controls

- `Start`: opens the game setup screen
- `Load`: loads the save stored in `csv/save.csv`
- `Help`: opens the in-game instruction screens
- `CW / CCW`: rotates the free tile
- Board edge arrows: inserts the free tile into the maze
- Keyboard arrow keys: moves the current player after a tile has been inserted
- `End Turn`: advances play to the next player
- `Save Game`: writes the current state to `csv/save.csv`

## Save Data

Saved games are stored in:

```text
csv/save.csv
```

The save file includes:

- player names,
- assigned treasure cards and completion status,
- player positions,
- the current free tile,
- turn order state,
- the full 7x7 board layout.

If you want to publish this repository without a real in-progress game state, replace `csv/save.csv` with a clean sample save or an empty placeholder before release.

## Assets and Data Files

The project depends on bundled image and CSV assets loaded through relative paths. If files are moved or the app is launched from a different working directory, image loading and CSV parsing will fail.

Key assets include:

- menu backgrounds in `Image/`
- board and button images in `Image/`
- treasure card and tile sprites in `TileImage/`
- tile and card configuration in `csv/Treasures.csv` and `csv/Cards.csv`

## Current Limitations

- The project does not use Maven or Gradle; compilation is manual.
- Asset loading depends on running from the repository root.
- `AIPlayer` and `PathFinder` classes exist, but AI-driven gameplay is not wired into the main game flow.
- Automated tests are not included.

## Screens

- Title/menu screen
- Game setup screen
- Four help/instruction screens
- Main game board with four player status panels

## Public Repository Notes

This repository has been prepared for public sharing by removing contributor-identifying comments from source files and replacing the README with generic, project-focused documentation.
