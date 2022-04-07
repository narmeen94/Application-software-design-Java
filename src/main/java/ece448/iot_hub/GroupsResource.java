 package ece448.iot_hub;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.TreeMap;
// import java.util.concurrent.SynchronousQueue;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import ece448.grading.GradeP3.MqttController;
// @RestController
// public class GroupsResource {

//      private final GroupsModel groups;
// 	 private MqttController mqttController;
     

// 	public GroupsResource(GroupsModel groups,MqttController mqttController) {
//         this.groups = groups;
//         this.mqttController=mqttController;
// 	}
	
// 	@GetMapping("/api/groups")
// 	public Collection<Object> getGroups() throws Exception {
// 		ArrayList<Object> ret = new ArrayList<>();
// 		for (String group: groups.getGroups()) {
// 			ret.add(makeGroup(group));
// 		}
// 		logger.info("Groups: {}", ret);
// 		return ret;
// 	 }

//     @GetMapping("/api/groups/{group}")
// 	public Object getGroup(
// 		@PathVariable("group") String group,
// 		@RequestParam(value = "action", required = false) String action) {
// 		// if (action == null) {
// 		// 	Object ret = makeGroup(group);
// 		// 	logger.info("Group {}: {}", group, ret);
// 		// 	return ret;
// 		// }
// 		if (action!=null)
// 		{
//             if(action.equals("on")||action.equals("off")||action.equals("toggle")){
// 				HashMap<String, Object> ret = new HashMap<>();
// 		        ret.put("name", group);
// 		         HashMap<String,String> mem=new HashMap<>();
// 		        for (String member:groups.getGroupMembers(group))
// 			    {
// 					mqttController.publishAction(member, action);
// 				//mem.put("name",member);
// 				//mem.put("state",mqttController.getState(member));
// 				mem.put(member,mqttController.getState(member));
				
// 			    }
// 		        ret.put("members",mem);
		        

//                //mqttController.publishAction(plugName, action);
//             }
//         }
//         // modify code below to control plugs by publishing messages to MQTT broker
// 		List<String> members = groups.getGroupMembers(group);
// 		logger.info("Group {}: action {}, {}", group, action, members);
// 		//return null;
// 		Object ret = makeGroup(group);
// 		return ret;
// 	}
//     @PostMapping("/api/groups/{group}")
// 	public void createGroup(
// 		@PathVariable("group") String group,
// 		@RequestBody List<String> members) {
// 		groups.setGroupMembers(group, members);
// 		logger.info("Group {}: created {}", group, members);
//     }
//     @DeleteMapping("/api/groups/{group}")
// 	public void removeGroup(
// 		@PathVariable("group") String group) {
// 		groups.removeGroup(group);
// 		logger.info("Group {}: removed", group);
// 	}
//     protected Object makeGroup(String group) {
// 		// modify code below to include plug states
// 		HashMap<String, Object> ret = new HashMap<>();
// 		ret.put("name", group);
// 		HashMap<String,String> mem=new HashMap<>();
// 		for (String member:groups.getGroupMembers(group))
// 			{
// 				//mem.put("name",member);
// 				//mem.put("state",mqttController.getState(member));
// 				mem.put(member,mqttController.getState(member));
				
// 			}
// 		ret.put("members",mem);
// 		return ret;
// 	}

// 		private static final Logger logger = LoggerFactory.getLogger(GroupsResource.class);	
// }

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
public class GroupsResource {

	private final GroupsModel groups;
	private MqttController mqttController;

	public GroupsResource(GroupsModel groups,MqttController mqttController) {
		this.groups = groups;
		this.mqttController=mqttController;
	}
	
	@GetMapping("/api/groups")
	public Collection<Object> getGroups() throws Exception {
		ArrayList<Object> ret = new ArrayList<>();
		for (String group: groups.getGroups()) {
			ret.add(makeGroup(group));
		}
		logger.info("Groups: {}", ret);
		return ret;
	}

	@GetMapping("/api/groups/{group}")
	public Object getGroup(
		@PathVariable("group") String group,
		@RequestParam(value = "action", required = false) String action) {
		// if (action == null) {
		// 	Object ret = makeGroup(group);
		// 	logger.info("Group {}: {}", group, ret);
		// 	return ret;
		// }
		if (action!=null)
		{
            if(action.equals("on")||action.equals("off")||action.equals("toggle")){

		        for (String member:groups.getGroupMembers(group))
			    {
					mqttController.publishAction(member, action);				
			    }
            }
        }

		// modify code below to control plugs by publishing messages to MQTT broker
		//List<String> members = groups.getGroupMembers(group);
		Object ret = makeGroup(group);
		//logger.info("Group {}: action {}, {}", group, action, members);
		return ret;
	}
	@PostMapping("/api/groups/{group}")
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
	}
	protected Object makeGroup(String group) {
		// modify code below to include plug states
		HashMap<String, Object> ret = new HashMap<>();
		ret.put("name", group);
		ret.put("state",groups.getGroupState(group));
		ret.put("members", groups.getGroupMembers(group));
		return ret;
	}

	private static final Logger logger = LoggerFactory.getLogger(GroupsResource.class);	
}
