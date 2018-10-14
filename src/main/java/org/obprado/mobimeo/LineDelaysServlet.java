package org.obprado.mobimeo;

import javax.servlet.http.HttpServletRequest;

public class LineDelaysServlet extends AppServlet {

    @Override
    protected String handleGet(HttpServletRequest request) {
        return "Line delays!";
    }

}