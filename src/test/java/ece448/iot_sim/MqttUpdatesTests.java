package ece448.iot_sim;

import org.eclipse.paho.client.mqttv3.*;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class MqttUpdatesTests {

    @Test
    public void testgetTopic1() {
        
        String topicPrefix="iot_ece448";
        MqttUpdates mqttUp=new MqttUpdates(topicPrefix);
        String topic=mqttUp.getTopic("a","state");
        assertEquals(topic,topicPrefix+"/update/a/state");
    }
    @Test
    public void testgetTopic2() {
        
        String topicPrefix="iot_ece448";
        MqttUpdates mqttUp=new MqttUpdates(topicPrefix);
        String topic=mqttUp.getTopic("a","power");
        assertEquals(topic,topicPrefix+"/update/a/power");
    }

    
        //String topic ="iot_ece448/action/a/on";

    //     MqttMessage msg=new MqttMessage();
        
    //     mqtt.handleMessage(topic,msg);
    //     assertTrue(plug1.isOn());
    //     //assertEquals(plug1.getPower(),0,0);
    // }

    
    
}
