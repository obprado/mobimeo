package org.obprado.mobimeo;

import java.time.LocalTime;
import java.util.List;

public class Time {

    private static final int LINE_ID_INDEX = 0;
    private static final int STOP_ID_INDEX = 1;
    private static final int TIMESTAMP_INDEX = 2;

    private int lineId;
    private int stopId;
    private LocalTime time;

    private Time(int lineId, int stopId, LocalTime time) {
        this.lineId = lineId;
        this.stopId = stopId;
        this.time = time;
    }

    public boolean forStopId(int stopId) {
        return this.stopId == stopId;
    }

    public static Time fromRow(List<String> row) {
        return new Time(getLineId(row), getStopId(row), getTime(row));
    }

    private static Integer getLineId(List<String> row) {
        return Integer.valueOf(row.get(LINE_ID_INDEX));
    }

    private static Integer getStopId(List<String> row) {
        return Integer.valueOf(row.get(STOP_ID_INDEX));
    }

    private static LocalTime getTime(List<String> row) {
        return LocalTime.parse(row.get(TIMESTAMP_INDEX));
    }

    public int getLineId() {
        return lineId;
    }

    public LocalTime getTime() {
        return time;
    }
}
