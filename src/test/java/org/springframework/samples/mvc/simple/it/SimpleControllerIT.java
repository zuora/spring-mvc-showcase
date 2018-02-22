package org.springframework.samples.mvc.simple.it;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.Assert;
import org.junit.Test;

public class SimpleControllerIT {

	@Test
	public void submitSuccess() throws Exception {
		ResteasyClient client = new ResteasyClientBuilder().build();
		Response response = client.target("http://localhost:8080/spring-mvc-showcase/simple").request().get();
		Assert.assertEquals(200, response.getStatus());
	}
}
