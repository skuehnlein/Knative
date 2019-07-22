package de.demo.faas.quarkus;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class SayHelloService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SayHelloService.class);

	private int count = 0;

	private final SimpleDateFormat SDF = new SimpleDateFormat("HH:mm:ss");

	private static final String RESPONSE_STRING_FORMAT = "%s hello => '%s' : %d\n";

	private static final String HOSTNAME = parseContainerIdFromHostname(
			System.getenv().getOrDefault("HOSTNAME", "unknown"));

	static String parseContainerIdFromHostname(String hostname) {
		return hostname.replaceAll("hello-v\\d+-", "").trim();
	}

	public String sayHello(String prefix) {
		count++;
		return String.format(RESPONSE_STRING_FORMAT, prefix, HOSTNAME, count);
	}

	public String eventSayHello(String pKnativeEvent) {
		count++;
	    //String hostname = String.format(RESPONSE_STRING_FORMAT, ""," Event ", HOSTNAME, count);
	    JsonObject response = new JsonObject(pKnativeEvent);
//	                            .put("host", hostname.replace("\n", "").trim())
//	                            .put("time", SDF.format(new Date()));
	    LOGGER.info("Event Message Received \n {}", pKnativeEvent);
	    return response.encode();
	}


}
