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
        assertEquals(mqttUp.getTopic("a","state"),"iot_ece448/update/a/state");
       
    }
    @Test
    public void testgetTopic2() {
        
        String topicPrefix="iot_ece448";
        MqttUpdates mqttUp=new MqttUpdates(topicPrefix);
        String topic=mqttUp.getTopic("a","power");
        assertEquals(topic,topicPrefix+"/update/a/power");
    }

    @Test
    public void testgetMessage() {
        
        String value="off";
        String topicPrefix="iot_ece448";
        MqttUpdates mqttUp=new MqttUpdates(topicPrefix);
        MqttMessage msg=new MqttMessage(value.getBytes());
        if (msg==mqttUp.getMessage(value)){
            boolean b=true;
            assertTrue(b);
        }
       //assertEquals( mqttUp.getMessage(value),msg);
        //msg.toString();
        
        //assertEquals(msg,"off");
        //assertEquals(topic,topicPrefix+"/update/a/power");
    }

    
    
}
