package ece448.iot_hub;
import org.eclipse.paho.client.mqttv3.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import ece448.iot_sim.PlugSim;
import ece448.grading.GradeP3;
import ece448.grading.GradeP3.MqttController;


public class PlugsResourceTest {

    private  final String broker = "tcp://127.0.0.1";
	private  final String clientId = "iot_hub";
    private  final String topicPrefix = "iot_ece448";

    //MqttController mqtt=new MqttController( broker, clientId, topicPrefix);
    //private MqttController mqtt;

    @Test
	public void testMakePlug() throws Exception {
        
		MqttController mqtt0 = new MqttController(broker, clientId, topicPrefix);
		 mqtt0.start();
		 Thread.sleep(1000);
		// clear(mqtt0);
		// Thread.sleep(5000);

		PlugsResource plugres = new PlugsResource(mqtt0);
        Map<String, Object> ret = (HashMap<String, Object>) plugres.makePlug("a");
        System.out.println(ret.get("state"));
        System.out.println(ret);


        //assertEquals(ret.get("state"),"off");
        assertEquals(ret.toString(),"{name=a, state=off, power=0.000}");

	}	
}

    // @Test
    // public void getPlugTestOn() {
        
    //     MqttController mqtt=new MqttController(broker,clientId,topicPrefix);
    
    //     PlugsResource plugres=new PlugsResource(mqtt);

    //     HashMap<String,Object>obj=(HashMap<String,Object>)plugres.getPlug("a","on");
    //     assertEquals(obj.get("state"),"on");
    // }   

    // @Test
    // public void getPlugTestOff() {
        
    //      //MqttController mqtt=new MqttController(broker,clientId,topicPrefix);
    
    //     PlugsResource plugres=new PlugsResource(mqtt);

    //     HashMap<String,Object>obj=(HashMap<String,Object>)plugres.getPlug("a","off");
    //     assertEquals(obj.get("state"),"off");
    // }   
    // }
