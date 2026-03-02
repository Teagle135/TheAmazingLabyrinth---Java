package Controller;

import Model.Board;
import Model.Tile;

import java.util.LinkedList;
import java.util.Queue;

public class AIPlayer {
	private final Board board;
	private final int startX, startY;
	private final int targetX, targetY;

	public AIPlayer(Board board, int startX, int startY, int targetX, int targetY) {
		this.board = board;
		this.startX = startX;
		this.startY = startY;
		this.targetX = targetX;
		this.targetY = targetY;
	}

	public LinkedList<int[]> findShortestPath() {
		boolean[][] visited = new boolean[board.getTileArray().length][board.getTileArray()[0].length];
		Queue<int[]> queue = new LinkedList<>();
		int[][][] parent = new int[board.getTileArray().length][board.getTileArray()[0].length][2]; // Use 2D array to
																									// store parent
																									// coordinates

		// Initialize parent array with invalid values
		for (int i = 0; i < parent.length; i++) {
			for (int j = 0; j < parent[0].length; j++) {
				parent[i][j][0] = -1;
				parent[i][j][1] = -1;
			}
		}

		queue.add(new int[] { startX, startY });
		visited[startX][startY] = true;

		int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int x = current[0], y = current[1];

			if (x == targetX && y == targetY) {
				// Trace back the path
				LinkedList<int[]> path = new LinkedList<>();
				while (x != -1 && y != -1) {
					path.addFirst(new int[] { x, y });
					int tempX = parent[x][y][0];
					int tempY = parent[x][y][1];
					x = tempX;
					y = tempY;
				}
				return path;
			}

			for (int[] dir : directions) {
				int newX = x + dir[0];
				int newY = y + dir[1];

				if (isValidMove(newX, newY) && !visited[newX][newY]) {
					queue.add(new int[] { newX, newY });
					visited[newX][newY] = true;
					parent[newX][newY][0] = x;
					parent[newX][newY][1] = y;
				}
			}
		}
		return null; // Return null if no path is found
	}

	private boolean isValidMove(int x, int y) {
		return x >= 0 && x < board.getTileArray().length && y >= 0 && y < board.getTileArray()[0].length
				&& board.getTileArray()[x][y].isMovable();
	}
}
