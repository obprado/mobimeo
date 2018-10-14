package org.obprado.mobimeo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;

public class MobilityApp {

    private Server server;

    public static void main(String[] args) throws Exception {
        new MobilityApp().run();
    }

    public void run() throws Exception {
        int port = 8081;
        server = new Server();
        server.addConnector(serverConnector(port, server));
        server.setHandler(mobilityServletHandler());
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    private static ServletContextHandler mobilityServletHandler() throws IOException {
        ServletContextHandler servletHandler = new ServletContextHandler();

        CsvReader csvReader = new CsvReader();
        Delays delays = Delays.loadFile(csvReader);
        Lines lines = Lines.loadFile(csvReader, delays);
        Times times = Times.loadFile(csvReader);
        Stops stops = Stops.loadFile(csvReader);

        servletHandler.addServlet(new ServletHolder(new FindVehicleServlet()), "/find");
        servletHandler.addServlet(new ServletHolder(new NextVehicleAtStopServlet(lines, delays, times, stops)), "/next");
        servletHandler.addServlet(new ServletHolder(new LineDelaysServlet(lines, delays, times, stops)), "/delays");
        return servletHandler;
    }

    private ServerConnector serverConnector(int port, Server server) {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        return connector;
    }
}
