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
	public void MakePlugNullTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		 mqtt.start();
		 Thread.sleep(1000);
		// clear(mqtt0);
		// Thread.sleep(5000);

        PlugsResource plugres = new PlugsResource(mqtt);
       // plugres.getPlug("a","off");
        //Thread.sleep(1000);
        Map<String, Object> ret = (HashMap<String, Object>) plugres.makePlug("e");
        //System.out.println(ret.get("state"));
        //System.out.println(ret);
        //plugres.getPlug("a","off");



        //assertEquals(ret.get("state"),"off");
        assertEquals(ret.toString(),"{name=e, state=null, power=null}");

	}	


    @Test
	public void MakePlugOffTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		 mqtt.start();
		 Thread.sleep(1000);

        PlugsResource plugres = new PlugsResource(mqtt);      
        plugres.getPlug("a","off");
        Thread.sleep(1000);

        Map<String, Object> ret = (HashMap<String, Object>) plugres.makePlug("a");
        //plugres.getPlug("a","off");
        //PlugSim plug=new PlugSim("a");
        //plug.switchOff();

        //System.out.println(ret.get("state"));
        System.out.println(ret);
        //plugres.getPlug("a","off");

        assertEquals(ret.toString(),"{name=a, state=off, power=0.000}");



        //assertEquals(ret.get("state"),"off");
        // assertEquals(ret.toString(),"{name=a, state="+mqtt.getState("a")+", power="+mqtt.getPower("a")+"}");

    }	
    @Test
	public void MakePlugOnTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		 mqtt.start();
		 Thread.sleep(1000);

        PlugsResource plugres = new PlugsResource(mqtt);      
        plugres.getPlug("b.100","on");
        Thread.sleep(1000);

        Map<String, Object> ret = (HashMap<String, Object>) plugres.makePlug("b.100");
        //plugres.getPlug("a","off");
        //PlugSim plug=new PlugSim("a");
        //plug.switchOff();

        //System.out.println(ret.get("state"));
        System.out.println(ret);
        //plugres.getPlug("a","off");

        assertEquals(ret.toString(),"{name=b.100, state=on, power=100.000}");



        //assertEquals(ret.get("state"),"off");
        // assertEquals(ret.toString(),"{name=a, state="+mqtt.getState("a")+", power="+mqtt.getPower("a")+"}");

	}	

    // @Test
    // public void getPlugTestOn() throws Exception{
        
    //     MqttController mqtt=new MqttController(broker,clientId,topicPrefix);
    //     mqtt.start();
    //     Thread.sleep(1000);
    
    //     PlugsResource plugres=new PlugsResource(mqtt);

    //     HashMap<String,Object>ret=(HashMap<String,Object>)plugres.getPlug("a","on");
    //     System.out.println(ret.get("state"));
    //     //ret=(HashMap<String,Object>)plugres.getPlug("a","on");
    //     assertEquals(ret.get("state"),"on");
    
    // }   

    // @Test
    // public void getPlugTestOff() {
        
    //      //MqttController mqtt=new MqttController(broker,clientId,topicPrefix);
    
    //     PlugsResource plugres=new PlugsResource(mqtt);

    //     HashMap<String,Object>obj=(HashMap<String,Object>)plugres.getPlug("a","off");
    //     assertEquals(obj.get("state"),"off");
    // }   
    }
