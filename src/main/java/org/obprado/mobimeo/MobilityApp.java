package org.obprado.mobimeo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MobilityApp {

    private Server server;

    public static void main(String[] args) throws Exception {
        new MobilityApp().run();
    }

    public void run() throws Exception {
        int port = 8081;
        server = new Server();
        server.addConnector(serverConnector(port, server));
        server.setHandler(helloServletHandler());
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    private static ServletContextHandler helloServletHandler() {
        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addServlet(new ServletHolder(new FindVehicleServlet()), "/find");
        servletHandler.addServlet(new ServletHolder(new NextVehicleAtStopServlet()), "/next");
        servletHandler.addServlet(new ServletHolder(new LineDelaysServlet()), "/delays");
        return servletHandler;
    }

    private ServerConnector serverConnector(int port, Server server) {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        return connector;
    }
}
