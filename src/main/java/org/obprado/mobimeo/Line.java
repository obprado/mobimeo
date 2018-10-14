package org.obprado.mobimeo;

import java.util.List;

public class Line {

    private static final int LINE_ID_INDEX = 0;
    private static final int LINE_NAME_IN_LINES = 1;

    private static final int LINES_NAME_IN_DELAYS = 0;
    private static final int DELAY_AMOUNT_IN_DELAYS = 1;

    private int id;
    private String name;
    private int delay;

    public Line(int id, String name, int delay) {
        this.id = id;
        this.name = name;
        this.delay = delay;
    }

    public static Line fromRow(List<String> lineRow, Delays delays) {
        String name = getName(lineRow);
        return new Line(getId(lineRow), name, delays.findDelayFor(name));
    }

    private static Integer getId(List<String> row) {
        return Integer.valueOf(row.get(LINE_ID_INDEX));
    }

    private static String getName(List<String> row) {
        return row.get(LINE_NAME_IN_LINES);
    }

    public int getId() {
        return id;
    }

    public int getDelay() {
        return delay;
    }

    public String getName() {
        return name;
    }
}
