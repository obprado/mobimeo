package org.obprado.mobimeo;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.String.format;

public class FindVehicleServlet extends AppServlet {

    private final Lines lines;
    private final Times times;
    private final Stops stops;

    private static final String RESPONSE_TEMPLATE = "{ \n" +
            "  \"line\": \"%s\",\n" +
            "  \"regularTime\": \"%s\",\n" +
            "  \"actualTime\": \"%s\"\n" +
            "}";


    public FindVehicleServlet(Lines lines, Times times, Stops stops) {
        this.lines = lines;
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

        TimeLine actualVehicle = arrivalOnTimeAndPlace(time, timeLines);

        return format(RESPONSE_TEMPLATE, actualVehicle.lineName(), actualVehicle.regularTime(), actualVehicle.actualArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    private TimeLine arrivalOnTimeAndPlace(LocalTime time, List<TimeLine> arrivals) {
        return arrivals
                    .stream()
                    .filter(arrival -> arrival.actualArrivalTime().compareTo(time) == 0)
                    .findFirst()
                    .orElseThrow(() -> new UnsupportedOperationException("TODO: handle sad path"));
    }

}