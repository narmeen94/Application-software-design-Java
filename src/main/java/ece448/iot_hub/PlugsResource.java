package ece448.iot_hub;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ece448.grading.GradeP3.MqttController;

@RestController
public class PlugsResource {

	
    private MqttController mqttController;

	public PlugsResource(MqttController mqttController) {
        //this.plugs = plugs;
        this.mqttController=mqttController;
	}
    
    
    @GetMapping("/api/plugs")
    
    public Collection<Object> getStates() throws Exception {
    ArrayList<Object> ret = new ArrayList<>();
	Map<String,String> stateOnly=mqttController.getStates();
	for (String plug:stateOnly.keySet()){
        ret.add(makePlug(plug)); 

        }
		
		logger.info("Plugs: {}", ret);
		return ret;
    }

	// public Collection<Object> getPlugs() throws Exception {
	// 	ArrayList<Object> ret = new ArrayList<>();
	// 	Map<String,String> stateOnly=mqttController.getStates()
    

	@GetMapping("/api/plugs/{plugname:.+}")
	public Object getPlug(
		@PathVariable("plugname") String plugName,
		@RequestParam(value = "action", required = false) String action) {
		if (action!=null)
		{
            if(action.equals("on")||action.equals("off")||action.equals("toggle")){
               mqttController.publishAction(plugName, action);
            }
        }
        
        logger.info("Group {}: action {}", plugName, action);
		//return null;
		Object ret = makePlug(plugName);
	    logger.info("PlugName {}: {}", plugName, ret);
        return ret;
    }
    
        
        protected Object makePlug(String plugName) {
            // modify code below to include plug states
            HashMap<String, Object> ret = new HashMap<>();
            ret.put("name", plugName);
            ret.put("state",mqttController.getState(plugName));
            ret.put("power",mqttController.getPower(plugName));
            //ret.put("members", groups.getGroupMembers(group));
            return ret;
        }
        
        private static final Logger logger = LoggerFactory.getLogger(PlugsResource.class);	
    }


