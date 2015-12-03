package bean.lee.push.notification.test.connect;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * MQTT subscriber implementation using Eclipse Paho MQTT client lib.
 * Author: Thilina
 * Date: 7/19/14
 */
public class Subscriber extends AbstractMqttClient{

    public static final String BROKER_URL = "tcp://127.0.0.1:1883";
    public static final String TOPIC = "mqtt";
    private static final String CLIENT_ID = "c1";

    private static final Logger logger = Logger.getLogger(Subscriber.class.getName());

    public Subscriber(String brokerUrl, String clientId, String topic) throws MqttException {
        super(brokerUrl, clientId, new SubscriberCallback());
        try {
            // subscribe
            getClient().subscribe(topic);
            logger.info("Connected to " + brokerUrl + " and subscribed to topic " + topic);
        } catch (MqttException e) {
            logger.log(Level.SEVERE, "Error instantiating the subscriber", e);
            throw e;
        }
    }

    public static void main(String[] args) throws MqttException {
        Subscriber subscriber = new Subscriber(BROKER_URL, CLIENT_ID, TOPIC);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.trim().toLowerCase().equals("quit")){
                break;
            }
        }
        subscriber.disconnect();
        logger.info("Exit!");
    }

}
