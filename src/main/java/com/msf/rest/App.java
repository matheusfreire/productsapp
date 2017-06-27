package com.msf.rest;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.msf.rest.bd.EntityManagerUtil;

/**
 * Main class.
 *
 */
public class App {
    public static final String BASE_URI = "http://localhost:9090/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.msf.rest");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        EntityManagerUtil.initiateManager();
        System.out.println("Running");
        System.in.read();
        server.shutdownNow();;
    }
    
    
}
