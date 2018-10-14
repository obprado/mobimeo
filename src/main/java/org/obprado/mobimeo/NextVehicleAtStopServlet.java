package org.obprado.mobimeo;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.String.format;

public class NextVehicleAtStopServlet extends AppServlet {

    private final Lines lines;
    private final Delays delays;
    private final Times times;
    private final Stops stops;

    private static final String RESPONSE_TEMPLATE = "{ \n" +
            "  \"line\": \"%s\",\n" +
            "  \"regularTime\": \"%s\",\n" +
            "  \"actualTime\": \"%s\"\n" +
            "}";

    public NextVehicleAtStopServlet(Lines lines, Delays delays, Times times, Stops stops) {
        super();
        this.lines = lines;
        this.delays = delays;
        this.times = times;
        this.stops = stops;
    }

    @Override
    protected String handleGet(HttpServletRequest request) {
        int coordinateX = Integer.valueOf(request.getParameter("coordinateX"));
        int coordinateY = Integer.valueOf(request.getParameter("coordinateY"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));

        Stop stop = stops.find(coordinateX, coordinateY);
        List<Time> arrivals = times.timesForStop(stop);
        List<TimeLine> timeLines = lines.forTimes(arrivals);

        TimeLine nextArrival = findNextAfter(time, timeLines);

        return format(RESPONSE_TEMPLATE, nextArrival.lineName(), nextArrival.regularTime(), nextArrival.actualArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    private TimeLine findNextAfter(LocalTime currentTime, List<TimeLine> timeLines) {
        return timeLines
                .stream()
                .filter(timeLine -> timeLine.actualArrivalTime().isAfter(currentTime))
                .min((tl1, tl2) -> Integer.compare(
                        tl1.actualArrivalTime().toSecondOfDay(),
                        tl2.actualArrivalTime().toSecondOfDay()
                ))
                .get();
    }
}