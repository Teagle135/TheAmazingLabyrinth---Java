package Model;

import Model.Board;
import Model.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathFinder {
    private Board board; //

    public PathFinder(Board board) {
        this.board = board;
    }

    public List<Tile> findPath(Tile startTile, Tile goalTile) {
        List<Tile> path = new ArrayList<>();
        if (explore(startTile, goalTile, path)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean explore(Tile current, Tile goal, List<Tile> path) {
        if (current.equals(goal)) {
            path.add(current);
            return true;
        }

        for (Tile neighbor : board.getNeighbors(current)) {
            if (path.contains(neighbor)) continue;  // Avoid cycles
            path.add(neighbor);
            if (explore(neighbor, goal, path)) {
                return true;
            }
            path.remove(neighbor);
        }
        return false;
    }
}
