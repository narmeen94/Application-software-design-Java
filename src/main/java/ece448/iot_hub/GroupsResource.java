 package ece448.iot_hub;

//version 2
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
			//ret.add(addGroup(group));
			ret.add(makeGroup(group));
			
			
		}
		logger.info("Groups: {}", ret);
		return ret;
	}

	@GetMapping("/api/groups/{group}")
	public Object getGroup(
		@PathVariable("group") String group,
		@RequestParam(value = "action", required = false) String action) {
			PlugsResource plugRes=new PlugsResource(mqttController);
		for (String plugName:groups.getGroupMembers(group))
		{
			plugRes.getPlug(plugName, action);

		}
		
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
		PlugsResource plugRes=new PlugsResource(mqttController);
		// modify code below to include plug states
		HashMap<String, Object> ret = new HashMap<>();
		ret.put("name", group);
		ArrayList<Object>mem=new ArrayList<>();
		for (String plugName : groups.getGroupMembers(group))
		{
			mem.add(plugRes.makePlug(plugName));

		}
		ret.put("members",mem);
		//ret.put("state",groups.getGroupState(group));
		//ret.put("members", groups.getGroupMembers(group));
		return ret;
	}
	// protected Object addGroup(String group) {
		
	// 	HashMap<String, Object> retgroup = new HashMap<>();
	// 	retgroup.put("name", group);
	// 	retgroup.put("state",groups.getGroupState(group));
		
	// 	return retgroup;
	// }

	private static final Logger logger = LoggerFactory.getLogger(GroupsResource.class);	
}
