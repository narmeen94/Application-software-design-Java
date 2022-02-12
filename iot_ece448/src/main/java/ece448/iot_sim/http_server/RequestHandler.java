package ece448.iot_sim.http_server;

import java.util.Map;

/**
 * Return a string upon a GET request.interface does not has a body.the body is through "implement" class
 */
public interface RequestHandler {// interface is a completely abstract class. to use it in another class, "implement" is used
    public String handleGet(String path, Map<String, String> params);
}
