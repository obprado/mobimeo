package org.obprado.mobimeo;

import javax.servlet.http.HttpServletRequest;

public class LineDelaysServlet extends AppServlet {

    private final Lines lines;

    private static final String TEMPLATE = "{\n" +
            "  \"minutesDelayed\": \"%s\"\n" +
            "}";

    public LineDelaysServlet(Lines lines) {
        super();
        this.lines = lines;
    }

    @Override
    protected String handleGet(HttpServletRequest request) {
        Integer lineId = Integer.valueOf(request.getParameter("lineId"));
        Line line = lines.lineForId(lineId);
        return String.format(TEMPLATE, line.getDelay());
    }

}