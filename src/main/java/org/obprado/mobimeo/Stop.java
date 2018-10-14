package org.obprado.mobimeo;

import java.util.List;

public class Stop {

    private static final int STOP_ID_INDEX = 0;
    private static final int X_INDEX = 1;
    private static final int Y_INDEX = 2;

    private int x;
    private int y;
    private int id;

    Stop(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public static Stop fromRow(List<String> row) {
        return new Stop(getX(row), getY(row), getIndex(row));
    }

    private static Integer getIndex(List<String> row) {
        return Integer.valueOf(row.get(STOP_ID_INDEX));
    }


    private static Integer getX(List<String> row) {
        return Integer.valueOf(row.get(X_INDEX));
    }

    private static Integer getY(List<String> row) {
        return Integer.valueOf(row.get(Y_INDEX));
    }

    public boolean matches(int coordinateX, int coordinateY) {
        return this.x == coordinateX && this.y == coordinateY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}
