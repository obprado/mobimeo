package org.obprado.mobimeo;

public class NextVehicleAtStopServlet extends AppServlet {

    @Override
    protected String handleGet() {
        return "Next vehicle for stop X!";
    }
}