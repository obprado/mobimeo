package org.obprado.mobimeo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeLine {

    private Time time;
    private Line line;

    public TimeLine(Time time, Line line) {
        this.time = time;
        this.line = line;
    }

    public LocalTime actualArrivalTime() {
        return time.getTime().plusMinutes(line.getDelay());
    }

    public String lineName() {
        return line.getName();
    }

    public String regularTime() {
        return time.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
