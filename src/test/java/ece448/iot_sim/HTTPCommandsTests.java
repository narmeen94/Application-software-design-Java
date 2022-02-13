package ece448.iot_sim;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

import ece448.iot_sim.http_server.RequestHandler;

public class HTTPCommandsTests {

    @Test
    @Test
	public void testSwitchOff() {
		PlugSim plug = new PlugSim("a");

		plug.switchOff();

		assertFalse(plug.isOn());
    }
    @Test
    public void testreport(){
        PlugSim plug = new PlugSim("a");
        plug.switchOff();
        //RequestHandler off = new HTTPCommands({"a","b.100"});  
          //off.handleget("/a",);
          //public List<PlugSim> plugs=new ArrayList<>();


        HTTPCommands httpCommand=new HTTPCommands(["a","bb"]);
    
        //HTTPCommands httpCommands= new  HTTPCommands({"a","b.100"});
        
         
    }

    
	

	



	
}
