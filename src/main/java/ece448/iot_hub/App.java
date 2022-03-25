package ece448.iot_hub;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import ece448.grading.GradeP3.MqttController;

import org.springframework.beans.factory.annotation.Autowired;


@SpringBootApplication

public class App {

	@Bean(destroyMethod = "close")

	public MqttController mqttController(Environment env) throws Exception {
		String broker = env.getProperty("mqtt.broker");
		String clientId = env.getProperty("mqtt.clientId");
		String topicPrefix=env.getProperty("mqtt.topicPrefix");
		MqttController mqttController = new MqttController(broker, clientId,topicPrefix);
		mqttController.start();
		logger.info("MqttClient {} connected: {}", clientId, broker);
		return mqttController;
	}

	private static final Logger logger = LoggerFactory.getLogger(App.class);
}


