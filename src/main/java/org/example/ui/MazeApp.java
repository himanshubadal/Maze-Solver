package org.example.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.model.Cell;
import org.example.solver.MazeSolver;

public class MazeApp extends Application {

    private static final int SIZE = 12;
    private final Cell[][] grid = new Cell[SIZE][SIZE];

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();

        // Initialize cell grid
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Cell cell = new Cell(row, col);
                grid[row][col] = cell;
                root.add(cell.getRectangle(), col, row);
            }
        }

        // Mark walls (harder path)
        int[][] walls = {
                {0, 1}, {0, 2}, {0, 3}, {0, 4},
                {1, 4}, {1, 10},
                {2, 3}, {2, 4}, {2, 6}, {2, 7}, {2, 10},
                {3, 0}, {3, 1}, {3, 4}, {3, 6}, {3, 7}, {3, 10},
                {4, 2}, {4, 4}, {4, 6}, {4, 10},
                {5, 0}, {5, 2}, {5, 4}, {5, 6}, {5, 8}, {5, 9}, {5, 10},
                {6, 1}, {6, 2}, {6, 6}, {6, 7},
                {7, 0}, {7, 4}, {7, 8},
                {8, 0}, {8, 1}, {8, 2}, {8, 4}, {8, 6},
                {9, 6}, {10, 6}, {11, 6}
        };

        for (int[] wall : walls) {
            grid[wall[0]][wall[1]].setWall();
        }

        // Set start and goal
        grid[0][0].setStart();
        grid[SIZE - 1][SIZE - 1].setGoal();

        new Thread(() -> {
            try {
                MazeSolver.solve(grid, 0, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        primaryStage.setTitle("Maze Solver - DFS");
        primaryStage.setScene(new Scene(root, SIZE * Cell.SIZE, SIZE * Cell.SIZE));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
