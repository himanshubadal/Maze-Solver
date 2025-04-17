package org.example.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Cell;
import org.example.solver.MazeSolver;

public class MazeApp extends Application {

    private static final int SIZE = 12;
    private final Cell[][] grid = new Cell[SIZE][SIZE];

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        GridPane mazeGrid = new GridPane();

        // Build Maze Grid
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Cell cell = new Cell(row, col);
                grid[row][col] = cell;
                mazeGrid.add(cell.getRectangle(), col, row);
            }
        }

        // Set walls (ensures path exists)
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

        // Mark start and goal
        grid[0][0].setStart();
        grid[SIZE - 1][SIZE - 1].setGoal();

        // Start Button
        Button startButton = new Button("START");
        startButton.setStyle("-fx-font-size: 16px; -fx-padding: 10 20;");

        startButton.setOnAction(e -> {
            startButton.setDisable(true); // Disable to prevent multiple starts
            new Thread(() -> {
                try {
                    MazeSolver.solve(grid, 0, 0);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        // Layout
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        // HBox to center the button
        HBox buttonBox = new HBox(startButton);
        buttonBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(mazeGrid, buttonBox);
        root.setCenter(vbox);

        // Show
        primaryStage.setTitle("Maze Solver - DFS");
        primaryStage.setScene(new Scene(root, SIZE * Cell.SIZE, SIZE * Cell.SIZE + 50));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
