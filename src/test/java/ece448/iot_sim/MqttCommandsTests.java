package ece448.iot_sim;

import org.eclipse.paho.client.mqttv3.*;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class MqttCommandsTests {

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
    
        String topicPrefix="iot_ece448";
        MqttCommands mqtt=new MqttCommands(plugs,topicPrefix);
        String topic ="iot_ece448/action/a/on";

        MqttMessage msg=new MqttMessage();
        
        mqtt.handleMessage(topic,msg);
        assertTrue(plug1.isOn());
        //assertEquals(plug1.getPower(),0,0);
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
    
        String topicPrefix="iot_ece448";
        MqttCommands mqtt=new MqttCommands(plugs,topicPrefix);
        String topic ="iot_ece448/action/a/off";

        MqttMessage msg=new MqttMessage();
        
        mqtt.handleMessage(topic,msg);
        assertFalse(plug1.isOn());
        //assertEquals(plug1.getPower(),0,0);
    }

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
    
        String topicPrefix="iot_ece448";
        MqttCommands mqtt=new MqttCommands(plugs,topicPrefix);
        String topic ="iot_ece448/action/a/toggle";

        MqttMessage msg=new MqttMessage();
        plug1.switchOn();
        mqtt.handleMessage(topic,msg);
        plug1.toggle();
        assertTrue(plug1.isOn());
        
    }

        @Test
        public void testgetTopic() {
        List<PlugSim> plugs = new ArrayList<PlugSim>();
        PlugSim plug1=new PlugSim("a");
        PlugSim plug2=new PlugSim("b.100");
        PlugSim plug3=new PlugSim("cc");
        PlugSim plug4=new PlugSim("dddd");
    
        plugs.add(plug1);
        plugs.add(plug2);
        plugs.add(plug3);
        plugs.add(plug4);
    
        //String topicPrefix="iot_ece448";
        String topicPrefix="iot_ece448";
        MqttCommands mqtt=new MqttCommands(plugs,topicPrefix);
        String topic=mqtt.getTopic();
        //String topic ="iot_ece448/action/a/on";

        MqttMessage msg=new MqttMessage();
        
        mqtt.handleMessage(topic,msg);
        assertEquals(topic, "iot_ece448/action/#");
        //assertEquals(plug1.getPower(),0,0);
    
    }

    @Test
    public void testActionOffb() {
        List<PlugSim> plugs = new ArrayList<PlugSim>();
        PlugSim plug1=new PlugSim("a");
        PlugSim plug2=new PlugSim("b.100");
        PlugSim plug3=new PlugSim("cc");
        PlugSim plug4=new PlugSim("dddd");
    
        plugs.add(plug1);
        plugs.add(plug2);
        plugs.add(plug3);
        plugs.add(plug4);
    
        String topicPrefix="iot_ece448";
        MqttCommands mqtt=new MqttCommands(plugs,topicPrefix);
        String topic ="iot_ece448/action/b.100/off";

        MqttMessage msg=new MqttMessage();
        
        mqtt.handleMessage(topic,msg);
        assertFalse(plug1.isOn());
        //assertEquals(plug1.getPower(),0,0);
    }
}
