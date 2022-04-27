package ece448.iot_hub;
import org.eclipse.paho.client.mqttv3.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.*;

import ece448.iot_sim.MqttCommands;
import ece448.iot_sim.PlugSim;
import ece448.grading.GradeP3;
import ece448.grading.GradeP3.MqttController;


public class PlugsResourceTest {

    private  final String broker = "tcp://127.0.0.1";
	private  final String clientId = "iot_hub";
    private  final String topicPrefix = "iot_ece448";
    private  MqttClient client;
   

    //MqttController mqtt=new MqttController( broker, clientId, topicPrefix);
    //private MqttController mqtt;
    

    @Test
	public void MakePlugNullTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		mqtt.start();
        Thread.sleep(1000);
        PlugsResource plugres = new PlugsResource(mqtt);
        Map<String, Object> ret = (HashMap<String, Object>) plugres.makePlug("e");
        assertEquals(ret.toString(),"{name=e, state=null, power=null}");

	}	


    @Test
	public void GetPlugOffTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		 mqtt.start();
         Thread.sleep(1000);
         PlugsResource plugres = new PlugsResource(mqtt);      
        plugres.getPlug("a","off");
        Thread.sleep(1000);
        Map<String, Object> ret = (HashMap<String, Object>) plugres.makePlug("a");
        assertEquals(ret.toString(),"{name=a, state=off, power=0.000}");
    }	

    @Test
	public void GetPlugOnTest() throws Exception {
        
        MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        
        
        mqtt.start();
        Thread.sleep(1000);	
        PlugsResource plugres = new PlugsResource(mqtt); 
        plugres.getPlug("b.100","off");
        Thread.sleep(1000);
        
        plugres.getPlug("b.100","on");
        Thread.sleep(1000);
        Map<String, Object> ret = (HashMap<String, Object>) plugres.makePlug("b.100");
        assertEquals(ret.toString(),"{name=b.100, state=on, power=100.000}");
       

    }	
    
    @Test
	public void GetPlugToggleTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		mqtt.start();
        Thread.sleep(1000);
        PlugsResource plugres = new PlugsResource(mqtt);      
        plugres.getPlug("a","on");
        Thread.sleep(1000);
        plugres.getPlug("a","toggle");
        Thread.sleep(1000);
        Map<String, Object> ret = (HashMap<String, Object>) plugres.makePlug("a");
        assertEquals(ret.toString(),"{name=a, state=off, power=0.000}");

    }	
    @Test
	public void getPlugActionNullTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		mqtt.start();
		Thread.sleep(1000);
        PlugsResource plugres = new PlugsResource(mqtt);      
        Map<String, Object> ret = (HashMap<String, Object>) plugres.makePlug("a");
        assertEquals(plugres.getPlug("a",null),ret);

    }

    @Test
	public void getPlugActionWrongTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		 mqtt.start();
		 Thread.sleep(1000);

        PlugsResource plugres = new PlugsResource(mqtt);      
        Map<String, Object> ret = (HashMap<String, Object>) plugres.makePlug("a");
        assertEquals(plugres.getPlug("a","hello"),ret);

    }

    @Test
	public void getStatesaTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		mqtt.start();
		Thread.sleep(1000);

        PlugsResource plugres = new PlugsResource(mqtt);      
        plugres.getPlug("a","off");
        Thread.sleep(1000);
        plugres.getStates();
        ArrayList<Object>ret = (ArrayList<Object>) plugres.getStates();
        assertEquals(ret.get(0).toString(),"{name=a, state=off, power=0.000}");
        

    }

    @Test
	public void getStatesbTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		mqtt.start();
		Thread.sleep(1000);

        PlugsResource plugres = new PlugsResource(mqtt);      
        plugres.getPlug("b.100","off");
        Thread.sleep(1000);
        plugres.getStates();
        ArrayList<Object>ret = (ArrayList<Object>) plugres.getStates();
        assertEquals(ret.get(1).toString(),"{name=b.100, state=off, power=0.000}");
    }
    @Test
	public void getStatesccTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		mqtt.start();
		Thread.sleep(1000);

        PlugsResource plugres = new PlugsResource(mqtt);      
        plugres.getPlug("cc","off");
        Thread.sleep(1000);
        plugres.getStates();
        ArrayList<Object>ret = (ArrayList<Object>) plugres.getStates();
        assertEquals(ret.get(2).toString(),"{name=cc, state=off, power=0.000}");
    }

    @Test
	public void getStatesddddTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		mqtt.start();
		Thread.sleep(1000);

        PlugsResource plugres = new PlugsResource(mqtt);      
        plugres.getPlug("dddd","off");
        Thread.sleep(1000);
        plugres.getStates();
        ArrayList<Object>ret = (ArrayList<Object>) plugres.getStates();
        assertEquals(ret.get(3).toString(),"{name=dddd, state=off, power=0.000}");
    }



}