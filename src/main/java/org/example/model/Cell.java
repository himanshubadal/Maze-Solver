package org.example.model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cell {

    public static final int SIZE = 40;

    private final int row;
    private final int col;
    private final StackPane stackPane;
    private final Rectangle rectangle;
    private final Text label;
    private boolean isWall = false;
    private boolean visited = false;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;

        this.rectangle = new Rectangle(SIZE, SIZE);
        this.rectangle.setStroke(Color.GRAY);
        this.rectangle.setFill(Color.WHITE);

        this.label = new Text("");
        this.label.setFont(Font.font(18));

        this.stackPane = new StackPane(rectangle, label);
    }

    public StackPane getRectangle() {
        return stackPane;
    }

    public void setWall() {
        isWall = true;
        rectangle.setFill(Color.BLACK);
        label.setText("");
    }

    public void setEmpty() {
        isWall = false;
        rectangle.setFill(Color.WHITE);
        label.setText("");
    }

    public void setVisited() {
        rectangle.setFill(Color.LIGHTBLUE);
    }

    public void setPath() {
        rectangle.setFill(Color.LIMEGREEN);
    }

    public void setBacktrack() {
        rectangle.setFill(Color.LIGHTCORAL);
    }

    public void setStart() {
        rectangle.setFill(Color.GREEN);
        label.setText("S");
        label.setFill(Color.WHITE);
    }

    public void setGoal() {
        rectangle.setFill(Color.RED);
        label.setText("G");
        label.setFill(Color.WHITE);
    }

    public boolean isWall() {
        return isWall;
    }

    public boolean isVisited() {
        return visited;
    }

    public void markVisited() {
        this.visited = true;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
