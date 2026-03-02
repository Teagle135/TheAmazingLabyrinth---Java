package Model;

import java.util.ArrayList;
//Import
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

//This is the template class for board
public class Board {

	// Fields

	// 2D Tile Array
	private Tile[][] tileArray = new Tile[7][7];

	// Free Tile
	private Tile freeTile;

	// Constructor
	public Board(Stack<Tile> tileStack) {

		// Distribute the tiles onto the board randomly
		setupBoard(tileStack);

	}

	// Second Constructor
	public Board(Tile[][] tileArray, Tile freeTile) {
		this.tileArray = tileArray;
		this.freeTile = freeTile;
	}

	// This method setup the board
	private void setupBoard(Stack<Tile> tileStack) {

		// Temporary variable to store the current tile
		Tile currentTile;

		// Shuffle the tile stack
		Collections.shuffle(tileStack);

		// Put the fixed tile to its fixed place first
		for (int count = 0; count < 50; count++) {

			// Check if it is fixed
			if (tileStack.get(count).isMovable() == false) {
				tileArray[tileStack.get(count).getX()][tileStack.get(count).getY()] = tileStack.get(count);
			}
		}

		// Distribute the all the tiles on the board
		do {
			currentTile = tileStack.pop();

			// Check for Fixed tiles
			if (currentTile.isMovable()) {

				// Counter variable
				boolean counter = false; // For helping breaking out the outer loop

				// Put the tile to the next empty place
				for (int row = 0; row < 7; row++) {
					if (counter == false) {
						for (int col = 0; col < 7; col++) {
							if (Objects.equals(null, tileArray[row][col])) {
								tileArray[row][col] = currentTile;
								counter = true;
								break;
							}
						}
					} else
						break; // Break the Outer loop
				}

			}

		} while (tileStack.size() > 1); // Repeat until the free tile piece is left

		// Indicate the free tile
		freeTile = tileStack.pop();

	}

	public List<Tile> getNeighbors(Tile tile) {
		List<Tile> neighbors = new ArrayList<>();
		int x = tile.getX();
		int y = tile.getY();

		//
		if (x > 0 && tileArray[x - 1][y] != null && tileArray[x][y].getOpenings()[0]
				&& tileArray[x - 1][y].getOpenings()[2]) {
			neighbors.add(tileArray[x - 1][y]);
		}

		//
		if (y < tileArray[x].length - 1 && tileArray[x][y + 1] != null && tileArray[x][y].getOpenings()[1]
				&& tileArray[x][y + 1].getOpenings()[3]) {
			neighbors.add(tileArray[x][y + 1]);
		}

		//
		if (x < tileArray.length - 1 && tileArray[x + 1][y] != null && tileArray[x][y].getOpenings()[2]
				&& tileArray[x + 1][y].getOpenings()[0]) {
			neighbors.add(tileArray[x + 1][y]);
		}

		//
		if (y > 0 && tileArray[x][y - 1] != null && tileArray[x][y].getOpenings()[3]
				&& tileArray[x][y - 1].getOpenings()[1]) {
			neighbors.add(tileArray[x][y - 1]);
		}

		return neighbors;
	}

	// Getters and Setters
	public Tile[][] getTileArray() {
		return tileArray;
	}

	public void setTileArray(Tile[][] tileArray) {
		this.tileArray = tileArray;
	}

	public Tile getFreeTile() {
		return freeTile;
	}

	public void setFreeTile(Tile freeTile) {
		this.freeTile = freeTile;
	}

	@Override
	public String toString() {
		return "Board [tileArray=" + Arrays.toString(tileArray) + ", freeTile=" + freeTile + "]";
	}

}
