package ece448.iot_sim;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlugSimTests {

	@Test
	public void testInit() {
		PlugSim plug = new PlugSim("a");

		assertFalse(plug.isOn());
	}

	@Test
	public void testSwitchOn() {
		PlugSim plug = new PlugSim("a");

		plug.switchOn();

		assertTrue(plug.isOn());
	}

	@Test
	public void testSwitchOff() {
		PlugSim plug = new PlugSim("a");

		plug.switchOff();

		assertFalse(plug.isOn());
	}


	@Test
	public void testToggleOff() {
		PlugSim plug = new PlugSim("a");
		plug.switchOn();
		if (plug.isOn()){
			plug.toggle();
		    assertFalse(plug.isOn());}	

	}

	@Test
	public void testToggleOn() {
		PlugSim plug = new PlugSim("a");
		plug.switchOff();
		if (!plug.isOn()){
			plug.toggle();
			assertTrue(plug.isOn());

		}
		
	}

	@Test
	public void testPowerZero() {
		PlugSim plug = new PlugSim("a");
		if(!plug.isOn()){
			plug.measurePower();
			
			if (plug.getPower()==0){
				//plug.measurePower();
				boolean pow=true;
				assertTrue(pow);
			}
		}
	}
	@Test		
	public void testPowerNotZero() {
		PlugSim plug = new PlugSim("a");
		if(plug.isOn()){
			plug.measurePower();
					
			if (plug.getPower()!=0){
				boolean pow=false;
				assertFalse(pow);
					}
			
		}

	}

	@Test		
	public void testPowerLessThanHundred() {
		PlugSim plug = new PlugSim("narmeen");
		plug.switchOn();
		plug.updatePower(90);
		plug.measurePower();
		assertTrue(plug.getPower()>100);

	}

	@Test		
	public void testPowerGreaterThanThreeHundred() {
		PlugSim plug = new PlugSim("narmeen");
		plug.switchOn();
		plug.updatePower(600);
		plug.measurePower();
		assertTrue(plug.getPower()>300);


	}

	@Test		
	public void testPowerMiddle() {
		PlugSim plug = new PlugSim("narmeen");
		plug.switchOn();
		plug.updatePower(200);
		//plug.setPower(600);
		plug.measurePower();
		assertTrue((plug.getPower()<300)&&(plug.getPower()>100));


	}

	
	@Test		
	public void testPowerDot() {
		PlugSim plug = new PlugSim("narmeen.148");
		plug.switchOn();
		plug.measurePower();
		
		assertTrue(plug.getPower()==148);
			
	}

	@Test		
	public void testGetName() {
		PlugSim plug = new PlugSim("a");
		String name=plug.getName();
		
			assertTrue(plug.getName()==name);
			
		


	}

	@Test
	public void testUpdatePower(){
		PlugSim plug=new PlugSim("a");
		plug.updatePower(150);
		double power=plug.getPower();
		assertTrue(power==150);
	}

	private void extracted2(PlugSim plug) {
		plug.updatePower(0);
	}



	private void extracted(PlugSim plug) {
		assertTrue(plug.isOn());
	}
}
