# Java Labyrinth

A fully functional 4-player digital adaptation of the Amazing Labyrinth board game, 
built in Java using the MVC architecture pattern and AWT graphics library.

## Overview
Players take turns navigating a shifting maze to collect their treasures and return 
to their starting position. The board is randomly generated each game, ensuring a 
unique experience every playthrough.

## Features
- 4-player local multiplayer
- Randomized board generation for unique games every time
- Turn-based game logic with player movement
- Save and load game functionality
- Built using MVC architecture for clean code separation

## Technologies Used
- Java
- AWT Graphics Library
- MVC Architecture Pattern
- Java File I/O (for save/load system)

## How to Run
1. Clone the repository
   git clone https://github.com/Teagle135/java-labyrinth.git
2. Open the project in your preferred Java IDE (IntelliJ, Eclipse, etc.)
3. Run the main class to launch the game

## Project Structure
src/
├── model/        # Game logic and data
├── view/         # AWT graphics and UI
└── controller/   # Input handling and game flow

## What I Learned
- Implementing MVC architecture in a non-trivial Java application
- Randomized board generation algorithms
- File-based save/load systems using Java I/O
- Managing turn-based game state across multiple players
