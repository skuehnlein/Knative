package de.demo.faas.quarkus;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/")
public class SayHelloResource {

	@Inject
	SayHelloService greetingService;
	
	@ConfigProperty(name = "service.hostname")
	String hostName;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String hello() {
		return greetingService.sayHello("Hello: This is the resonsponse of: " + hostName);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/")
	public String eventHello(String pKnativeEvent) {
		return greetingService.eventSayHello(pKnativeEvent);
	}

}