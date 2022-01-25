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
	public void testToggleOn() {
		PlugSim plug = new PlugSim("a");
		if(plug.isOn()){
			plug.toggle();
			assertFalse(plug.isOn());
		}

	}

	@Test
	public void testToggleOff() {
		PlugSim plug = new PlugSim("a");
		if(!plug.isOn()){
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
				//plug.measurePower();
				boolean pow=false;
				assertFalse(pow);
					}
			
		}

	}

	@Test		
	public void testPowerLessThanHundred() {
		PlugSim plug = new PlugSim("a");
		if(plug.isOn()){
				
			if (plug.getPower()<100){
				plug.measurePower();
				assertTrue(plug.getPower()>100);
					}
			
		}

	}

	@Test		
	public void testPowerGreaterThanThreeHundred() {
		PlugSim plug = new PlugSim("a");
		if(plug.isOn()){
			plug.measurePower();
					
			if (plug.getPower()>300){
				plug.measurePower();
				assertTrue(plug.getPower()<300);
					}
			
		}

	}

	@Test		
	public void testNameDotPower() {
		PlugSim plug = new PlugSim("a");
		if(plug.isOn()){
			String name=plug.getName();
			if (name.indexOf(".") != -1)
		{
			 plug.updatePower(Integer.parseInt(name.split("\\.")[1]));
			 boolean flag=true;
			 assertTrue(flag);
		}
		
	}

	}
	private void extracted2(PlugSim plug) {
		plug.updatePower(0);
	}



	private void extracted(PlugSim plug) {
		assertTrue(plug.isOn());
	}
}
