package ece448.iot_sim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.sql.Timestamp; 

/**g
 * Simulate a smart plug with power monitoring.
 */
public class PlugSim {

	private final String name;
	private boolean on = false;
	private double power = 0; // in watts



	public PlugSim(String name) {
		this.name = name;
	}

	/**
	 * No need to synchronize if read a final field.
	 */
	public String getName() {
		return name;
	}

	
	 // Switch the plug on.
	  synchronized public void switchOn() {
		updateState(true);
	}

	synchronized public void switchOff() {
		// P1: add your code here
		updateState(false);
	}

	synchronized public void toggle() {
		// P1: add your code here
		if(on){
			//on=false;
			switchOff();
		}
		else{
			//on=true;
			switchOn();
		}
	}
 
		
	

	 
	synchronized public void measurePower() {
		if (!on) {
			updatePower(0);
			return;
		}

		// a trick to help testing
		if (name.indexOf(".") != -1)
		{
			updatePower(Integer.parseInt(name.split("\\.")[1]));
		}
		// do some random walk
		else if (power < 100)
		{
			updatePower(power + Math.random() * 100);
		}
		else if (power > 300)
		{
			updatePower(power - Math.random() * 100);
		}
		else
		{
			updatePower(power + Math.random() * 40 - 20);
		}
	}


	protected void updateState(boolean o) {
		on = o;

	   logger.info("Plug {}: state {}", name, on? "on": "off");
	   for (Observer observer: observers) {
		   observer.update(name, "state", on? "on": "off");
	    }

	}
	protected void updatePower(double p) {
		power = p;
		logger.debug("Plug {}: power {}", name, power);
		for (Observer observer: observers) {
			observer.update(name, "power",String.format("%.3f", power));
			
		}
	}

	/**
	 * Getter: current state
	 */
	synchronized public boolean isOn() {
		return on;
	}

	/**
	 * Getter: last power reading
	 */
	synchronized public double getPower() {
		
		
		return power;
	}

	//for publishing to MQTT broker, defining the observer.
	public static interface Observer {
		 void update(String name, String key, String value);
	}

	private final ArrayList<Observer> observers = new ArrayList<>();

	synchronized public void addObserver(Observer observer) {

		observers.add(observer);
		observer.update(name, "state", on? "on": "off");
		observer.update(name, "power", String.format("%.3f", power));
	}

    
	private static final Logger logger = LoggerFactory.getLogger(PlugSim.class);






}
