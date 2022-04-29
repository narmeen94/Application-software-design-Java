package ece448.iot_hub;
import org.eclipse.paho.client.mqttv3.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import ece448.iot_sim.PlugSim;
import ece448.grading.GradeP3;
import ece448.grading.GradeP3.MqttController;


public class GroupsResourceTest {

    private  final String broker = "tcp://127.0.0.1";
	private  final String clientId = "iot_hub";
    private  final String topicPrefix = "iot_ece448";
    
   

    //MqttController mqtt=new MqttController( broker, clientId, topicPrefix);
    //private MqttController mqtt;
    

    @Test
	public void MakeGroupTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        mqtt.start();
        Thread.sleep(1000);
        GroupsModel gm=new GroupsModel(mqtt);
        GroupsResource groupres = new GroupsResource(gm,mqtt);
        
        Map<String, Object> ret = (HashMap<String, Object>) groupres.makeGroup("plugs1");
        //ret.get("members")

        assertEquals(ret.get("name"),"plugs1");

    }	
    
    @Test
	public void getGroupTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        mqtt.start();
        Thread.sleep(1000);
        GroupsModel gm=new GroupsModel(mqtt);
        GroupsResource groupres = new GroupsResource(gm,mqtt);
        
        Map<String, Object> ret = (HashMap<String, Object>) groupres.getGroup("plugs1",null);
        //ret.get("members")

        assertEquals(ret.get("name"),"plugs1");

    }	
    
    @Test
	public void getGroupsTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        mqtt.start();
        Thread.sleep(1000);
        GroupsModel gm=new GroupsModel(mqtt);
        GroupsResource groupres = new GroupsResource(gm,mqtt);
        ArrayList<String> members=new ArrayList<>();
        //members.add("a");
        members.add("e");
        
        gm.setGroupMembers("plugs1",members);

        ArrayList<Object> ret = (ArrayList<Object>)groupres.getGroups();
        
        //assertEquals(ret.toString(),"[{members=[{name=a, state=off, power=0.000}, {name=e, state=null, power=null}], name=plugs1}]");
        assertEquals(ret.toString(),"[{members=[{name=e, state=null, power=null}], name=plugs1}]");
    }
    
    @Test
	public void createGroupTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        mqtt.start();
        Thread.sleep(1000);
        GroupsModel gm=new GroupsModel(mqtt);
        GroupsResource groupres = new GroupsResource(gm,mqtt);
        ArrayList<String> members=new ArrayList<>();
        //members.add("a");
        members.add("e");
        
        //gm.setGroupMembers("plugs1",members);
        groupres.createGroup("plugs1",members);

        ArrayList<Object> ret = (ArrayList<Object>)groupres.getGroups();
        
        //assertEquals(ret.toString(),"[{members=[{name=a, state=off, power=0.000}, {name=e, state=null, power=null}], name=plugs1}]");
        assertEquals(ret.toString(),"[{members=[{name=e, state=null, power=null}], name=plugs1}]");
    }
    
    @Test
	public void removeGroupTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        mqtt.start();
        Thread.sleep(1000);
        GroupsModel gm=new GroupsModel(mqtt);
        GroupsResource groupres = new GroupsResource(gm,mqtt);
        ArrayList<String> members=new ArrayList<>();
        //members.add("a");
        members.add("e");
        groupres.createGroup("plugs1",members);
        groupres.removeGroup("plugs1");

        ArrayList<Object> ret = (ArrayList<Object>)groupres.getGroups();
        
        //assertEquals(ret.toString(),"[{members=[{name=a, state=off, power=0.000}, {name=e, state=null, power=null}], name=plugs1}]");
        assertEquals(ret.toString(),"[]");
    }
    
    @Test
	public void getGroupActionOffTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        mqtt.start();
        Thread.sleep(1000);
        GroupsModel gm = new GroupsModel(mqtt);
        GroupsResource groupres = new GroupsResource(gm,mqtt);
        ArrayList<String> members=new ArrayList<>();
        members.add("a");
        members.add("cc");
        members.add("e");
        gm.setGroupMembers("plugs1",members);
        
        Map<String, Object> ret = (HashMap<String, Object>) groupres.getGroup("plugs1","on");
        ret = (HashMap<String, Object>) groupres.getGroup("plugs1","off");
        //ret.get("members")

        assertEquals(ret.get("name"),"plugs1");

    }

    @Test
	public void getGroupActionOnTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        mqtt.start();
        Thread.sleep(1000);
        GroupsModel gm = new GroupsModel(mqtt);
        GroupsResource groupres = new GroupsResource(gm,mqtt);
        ArrayList<String> members=new ArrayList<>();
        members.add("a");
        members.add("cc");
        members.add("e");
        gm.setGroupMembers("plugs1",members);
        
        Map<String, Object> ret = (HashMap<String, Object>) groupres.getGroup("plugs1","off");
        ret = (HashMap<String, Object>) groupres.getGroup("plugs1","on");
        //ret.get("members")

        assertEquals(ret.get("name"),"plugs1");

    }

    @Test
	public void getGroupActionToggleTest() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        mqtt.start();
        Thread.sleep(1000);
        GroupsModel gm = new GroupsModel(mqtt);
        GroupsResource groupres = new GroupsResource(gm,mqtt);
        ArrayList<String> members=new ArrayList<>();
        members.add("a");
        members.add("cc");
        members.add("e");
        gm.setGroupMembers("plugs1",members);
        
        Map<String, Object> ret = (HashMap<String, Object>) groupres.getGroup("plugs1","off");
        ret = (HashMap<String, Object>) groupres.getGroup("plugs1","toggle");
        //ret.get("members")

        assertEquals(ret.get("name"),"plugs1");

    }

    @Test
	public void removeGroupPlugs2Test() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        mqtt.start();
        Thread.sleep(1000);
        GroupsModel gm=new GroupsModel(mqtt);
        GroupsResource groupres = new GroupsResource(gm,mqtt);
        ArrayList<String> members=new ArrayList<>();
        //members.add("a");
        members.add("a");
        groupres.createGroup("plugs2",members);
        groupres.removeGroup("plugs2");

        ArrayList<Object> ret = (ArrayList<Object>)groupres.getGroups();
        
        //assertEquals(ret.toString(),"[{members=[{name=a, state=off, power=0.000}, {name=e, state=null, power=null}], name=plugs1}]");
        assertEquals(ret.toString(),"[]");
    }

    @Test
	public void createGroupPlugs2Test() throws Exception {
        
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
        mqtt.start();
        Thread.sleep(1000);
        GroupsModel gm=new GroupsModel(mqtt);
        GroupsResource groupres = new GroupsResource(gm,mqtt);
        ArrayList<String> members=new ArrayList<>();
        members.add("e");
        groupres.createGroup("plugs2",members);

        ArrayList<Object> ret = (ArrayList<Object>)groupres.getGroups();
        assertEquals(ret.toString(),"[{members=[{name=e, state=null, power=null}], name=plugs2}]");
    }
    
}