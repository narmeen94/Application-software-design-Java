package ece448.iot_sim;

//import ece448.iot_sim.http_server.RequestHandler;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class HTTPCommandsTests {
    


@Test
public void testListAllPlugs() {
    List<PlugSim> plugs = new ArrayList<PlugSim>();
    PlugSim plug1=new PlugSim("a");
    PlugSim plug2=new PlugSim("b.100");
    PlugSim plug3=new PlugSim("cc");
    PlugSim plug4=new PlugSim("dddd");

    plugs.add(plug1);
    plugs.add(plug2);
    plugs.add(plug3);
    plugs.add(plug4);

    
    HTTPCommands HTTPCommands=new HTTPCommands(plugs);
    Map<String, String> params=new TreeMap<>();
    
    params.put("action","on");
    String path="/";
    HTTPCommands.handleGet(path,params);
    String pluglist="<html><body><p><a href='/a'>a</a></p><p><a href='/b.100'>b.100</a></p><p><a href='/cc'>cc</a></p><p><a href='/dddd'>dddd</a></p></body></html>";
    assertEquals(HTTPCommands.listPlugs(),pluglist);
}
@Test
public void testplugNotSpecified() {
    List<PlugSim> plugs = new ArrayList<PlugSim>();
    PlugSim plug1=new PlugSim("a");
    PlugSim plug2=new PlugSim("b.100");
    PlugSim plug3=new PlugSim("cc");
    PlugSim plug4=new PlugSim("dddd");

    plugs.add(plug1);
    plugs.add(plug2);
    plugs.add(plug3);
    plugs.add(plug4);

    
    HTTPCommands HTTPCommands=new HTTPCommands(plugs);
    Map<String, String> params=new TreeMap<>();
    
    params.put("action","on");
    String path="/";
    String output=HTTPCommands.handleGet(path,params);
    String pluglist="<html><body><p><a href='/a'>a</a></p><p><a href='/b.100'>b.100</a></p><p><a href='/cc'>cc</a></p><p><a href='/dddd'>dddd</a></p></body></html>";
    assertEquals(output,pluglist);
}

}
    
