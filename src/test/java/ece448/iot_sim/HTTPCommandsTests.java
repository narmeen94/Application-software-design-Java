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
    
    params.put("action","null");
    String path="/";
    String output=HTTPCommands.handleGet(path,params);
    String pluglist="<html><body><p><a href='/a'>a</a></p><p><a href='/b.100'>b.100</a></p><p><a href='/cc'>cc</a></p><p><a href='/dddd'>dddd</a></p></body></html>";
    if (path.equals("/")){
        assertEquals(output,pluglist);}
        
    //String pluglist="<html><body><p><a href='/a'>a</a></p><p><a href='/b.100'>b.100</a></p><p><a href='/cc'>cc</a></p><p><a href='/dddd'>dddd</a></p></body></html>";
        
    
    
}

@Test
public void testNullPlug() {
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
    Map<String, PlugSim> plugsTree=new TreeMap<>();
    plugsTree.put("a",plug1);
    
    params.put("action","on");
    String path="/er";
    //HTTPCommands.handleGet(path,params);
    PlugSim plug=plugsTree.get(path.substring(1));
    //assertTrue(plug==null);
    assertTrue(HTTPCommands.handleGet(path,params)==null);
    
        //assertEquals(HTTPCommands.handleGet(path,params),plug);}
    
}


@Test
public void testActionOn() {
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
    String path="/b.100";
    HTTPCommands.handleGet(path,params);
    assertTrue(plug2.isOn());
    assertEquals(plug2.getPower(),100,0);
    
}

@Test
public void testActionOff() {
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
    
    params.put("action","off");
    String path="/b.100";
    HTTPCommands.handleGet(path,params);
    assertFalse(plug2.isOn());
    assertTrue(plug2.getPower()==0);}
    

    

@Test
public void testActionToggle() {
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
    
    params.put("action","toggle");
    String path="/b.100";
    plug2.switchOn();
    HTTPCommands.handleGet(path,params);
    assertFalse(plug2.isOn());
    assertEquals(plug2.getPower(),0,0);
}

@Test
public void testActionNull() {
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
    
    params.put("action",null);
    String path="/b.100";
    HTTPCommands.handleGet(path,params);
    

    assertEquals(HTTPCommands.report(plug2),HTTPCommands.handleGet(path,params));
}


}
    
