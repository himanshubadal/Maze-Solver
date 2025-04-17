package org.example.solver;

import javafx.application.Platform;
import org.example.model.Cell;

public class MazeSolver {

    public static boolean solve(Cell[][] grid, int row, int col) throws InterruptedException {
        int size = grid.length;

        if (row < 0 || col < 0 || row >= size || col >= size)
            return false;

        Cell cell = grid[row][col];

        if (cell.isWall() || cell.isVisited())
            return false;

        cell.markVisited();

        Platform.runLater(cell::setVisited);
        Thread.sleep(100);

        if (row == size - 1 && col == size - 1) {
            Platform.runLater(cell::setPath);
            return true;
        }

        if (solve(grid, row + 1, col) ||
                solve(grid, row - 1, col) ||
                solve(grid, row, col + 1) ||
                solve(grid, row, col - 1)) {

            Platform.runLater(cell::setPath);
            return true;
        }

        Platform.runLater(cell::setBacktrack);
        return false;
    }
}
