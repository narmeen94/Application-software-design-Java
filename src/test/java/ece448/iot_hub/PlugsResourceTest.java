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

    private static final String broker = "tcp://127.0.0.1";
	private static final String clientId = "iot_hub";
    private static final String topicPrefix = "iot_ece448";
    private MqttController mqtt;

    @Test
    public void getPlugTest() {
        
         //MqttController mqtt=new MqttController(broker,clientId,topicPrefix);
    
        PlugsResource plugres=new PlugsResource(mqtt);

        HashMap<String,Object>obj=(HashMap<String,Object>)plugres.getPlug("a","toggle");
        assertEquals(obj.get("state"),"toggle");
    }   
    }
