package ece448.iot_sim;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Collections;


public class MqttCommands {

    private final TreeMap<String, PlugSim> plugs = new TreeMap<>();
    private final String topicPrefix;

    public MqttCommands(List<PlugSim> plugs, String topicPrefix) {
        //populating the treemap plugs:

        for (PlugSim plug: plugs)
        {
        this.plugs.put(plug.getName(), plug);
    }
        this.topicPrefix = topicPrefix;
    }

    public String getTopic() {
        return topicPrefix+"/action/#";
        }

    public void handleMessage(String topic, MqttMessage msg) {

        try {
            logger.info("MqttCmd {}", topic);
        
            // switch on/off/toggle here
            //String[] splittedTopic=topic.substring(topicPrefix.length()+1).split("/");
            String splittedTopic=topic.substring(topic.indexOf("action"));
            String[] str=splittedTopic.split("/");
            String command=str[0]; //action
            System.out.println(str[0]);
            String plugName=str[1]; //a
            System.out.println(str[1]);
            String val=str[2]; //on,off,toggle
            System.out.println(str[2]);

            PlugSim plug=plugs.get(plugName);

            if (command.equals("action")){
                
                if (val.equals("on")){
                    plug.switchOn();
                }
                else if(val.equals("off")){
                    plug.switchOff();
                }
                else if (val.equals("toggle")){
                    plug.toggle();
                }
                

            }


        }

        catch (Throwable th) {
        // Otherwise, Mqtt client will disconnect w/o error information
          logger.error("MqttCmd {}: {}", topic, th.getMessage(), th);
        }
    }
        
    private static final Logger logger = LoggerFactory.getLogger(MqttCommands.class);
    
}
