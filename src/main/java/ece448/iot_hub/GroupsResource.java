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

import ece448.grading.GradeP3;
import ece448.grading.GradeP3.MqttController;

@RestController
public class GroupsResource {

	private final GroupsModel groups;
    private MqttController mqttController;

	public GroupsResource(GroupsModel groups,MqttController mqttController) {
        this.groups = groups;
        this.mqttController=mqttController;
	}
	private final HashMap<String, String> states = new HashMap<>();
	
	
	@GetMapping("/api/plugs")
	public Collection<Object> getGroups() throws Exception {
		ArrayList<Object> ret = new ArrayList<>();
		//String name=mqttController.plugName;
		Map<String,String> stateOnly=mqttController.getStates();
		//for (String group: groups.getGroups()) {
		  for (String group:stateOnly.keySet()){
			ret.add(makeGroup(group)); 
			//System.out.println(mqttController.getStates());
			//ret.add(mqttController.getStates());
			
		}
		//System.out.println(mqttController.getStates());
		logger.info("Groups: {}", ret);
		//return mqttController.getStates();
		return ret;
	}
    MqttMessage msg=new MqttMessage();

	@GetMapping("/api/plugs/{group}")
	public Object getGroup(
		@PathVariable("group") String group,
		@RequestParam(value = "action", required = false) String action) {
		if (action!= null)
		{
			mqttController.publishAction(group, action);
		}
	// 	if (action == null) {
	// 		Object ret = makeGroup(group);
	// 		logger.info("Group {}: {}", group, ret);
	// 		return ret;
	// 	}
	// 	else if (action.equals("on"))
	// 	{
	// 		mqttController.publishAction(group,"on");
	// 		logger.info(msg.toString()+"         i am here");
			

	// 	}

	// 	else if (action.equals("off"))
	//    {
	// 		mqttController.publishAction(group,msg.toString());
			
	// 	}

	// 	else if (action.equals("toggle"))
	// 	{
	// 		mqttController.publishAction(group,msg.toString());
			
			
	// 	}

		 //String topic = topicPrefix+"/action/"+group+"/"+action;
		 //MqttMessage msg=new MqttMessage();
        // mqttController.handleUpdate(topic,msg);
		// modify code below to control plugs by publishing messages to MQTT broker
		//List<String> members = groups.getGroupMembers(group);
		//mqttController.publishAction(group,action);
		logger.info("Group {}: action {}", group, action);
		//return null;
		Object ret = makeGroup(group);
	    logger.info("Group {}: {}", group, ret);
		return ret;
		
	}

	/*@PostMapping("/api/groups/{group}")
	public void createGroup(
		@PathVariable("group") String group,
		@RequestBody List<String> members) {
		groups.setGroupMembers(group, members);
		logger.info("Group {}: created {}", group, members);
	}

	@DeleteMapping("/api/groups/{group}")
	public void removeGroup(
		@PathVariable("group") String group) {
		groups.removeGroup(group);
		logger.info("Group {}: removed", group);
	}*/

	protected Object makeGroup(String group) {
		// modify code below to include plug states
		HashMap<String, Object> ret = new HashMap<>();
		ret.put("name", group);
		ret.put("state",mqttController.getState(group));
		ret.put("power",mqttController.getPower(group));
		//ret.put("members", groups.getGroupMembers(group));
		return ret;
	}

	private static final Logger logger = LoggerFactory.getLogger(GroupsResource.class);	
}


    

