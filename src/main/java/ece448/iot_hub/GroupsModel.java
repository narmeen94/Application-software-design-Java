package ece448.iot_hub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import ece448.grading.GradeP3.MqttController;

import org.springframework.stereotype.Component;
@Component
 public class GroupsModel {
	
	private MqttController mqttController;

	public GroupsModel(MqttController mqttController) {
		
		this.mqttController=mqttController;
	}
	
	private HashMap<String, HashSet<String>> groups = new HashMap<>(); 

	synchronized public List<String> getGroups() {
		return new ArrayList<>(groups.keySet());  //only the names of the keys will be returned by keyset()
	}
    synchronized public List<String> getGroupMembers(String group) {
		HashSet<String> members = groups.get(group);
		return (members == null)? new ArrayList<>(): new ArrayList<>(members);
	}

	synchronized public Object getGroupState(String group) {
		ArrayList<String> memstate=new ArrayList<>();
		int count=0;
		String state="unknown";
		for (String member:getGroupMembers(group))
		{
			memstate.add(mqttController.getState(member));
	        
			if ((mqttController.getState(member))==null)
			{
				return null;
			}
			else if ((mqttController.getState(member)).equals("on"))
			{
				count=count+1;

			}
			else if ((mqttController.getState(member)).equals("off"))
			{
				count=0;
			}
			
			
		}
		if (count==memstate.size())
		{
		    state="on";
		}
		else if (count==0)
		{
			state= "off";
		}
		
		return state;
	}


	
	synchronized public void setGroupMembers(String group, List<String> members) {
		groups.put(group, new HashSet<>(members));
	}

	synchronized public void removeGroup(String group) {
		groups.remove(group);
	}
}