package org.obprado.mobimeo;

import javax.servlet.http.HttpServletRequest;

public class FindVehicleServlet extends AppServlet {

    @Override
    protected String handleGet(HttpServletRequest request) {
        return "Find a vehicle!";
    }

}