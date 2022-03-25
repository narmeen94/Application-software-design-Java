// package ece448.iot_hub;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.List;

// import org.eclipse.paho.client.mqttv3.MqttClient;
// import org.springframework.stereotype.Component;

// import ece448.grading.GradeP3.MqttController;

// @Component
// public class GroupsModel {

//      //i have added mqttclient here

//     // private MqttController mqttController;

// 	//public GroupsModel(MqttController mqttController)
//      //{
//          //this.mqttController=mqttController;
//      //}
// //till here.

// //groups=plugs
// 	private HashMap<String, HashSet<String>> groups = new HashMap<>();
	

// 	synchronized public List<String> getGroups() { //getPlugs
// 		return new ArrayList<>(groups.keySet());  //only the names of theplugs will be returned by keyset()
// 	}

// 	synchronized public List<String> getGroupMembers(String group) {//groupmembers=plugNames
// 		HashSet<String> members = groups.get(group);                 //group=plug
// 		return (members == null)? new ArrayList<>(): new ArrayList<>(members);
//     }
//  //i dont think these are needed for now
//     synchronized public void setGroupMembers(String group, List<String> members) {
// 		groups.put(group, new HashSet<>(members));
// 	}

// 	synchronized public void removeGroup(String group) {
// 		groups.remove(group);
// 	}
// }

