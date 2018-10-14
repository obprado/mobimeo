package org.obprado.mobimeo;

import javax.servlet.http.HttpServletRequest;

public class LineDelaysServlet extends AppServlet {

    private final Lines lines;
    private final Delays delays;
    private final Times times;
    private final Stops stops;

    private static final String TEMPLATE = "{\n" +
            "  \"minutesDelayed\": \"%s\"\n" +
            "}";

    public LineDelaysServlet(Lines lines, Delays delays, Times times, Stops stops) {
        super();
        this.lines = lines;
        this.delays = delays;
        this.times = times;
        this.stops = stops;
    }

    @Override
    protected String handleGet(HttpServletRequest request) {
        Integer lineId = Integer.valueOf(request.getParameter("lineId"));
        Line line = lines.lineForId(lineId);
        return String.format(TEMPLATE, line.getDelay());
    }

}