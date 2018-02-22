package org.springframework.samples.mvc.form.it;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.Test;

import org.junit.Assert;

public class FormControllerIT {

	@Test
	public void submitSuccess() throws Exception {
		CookieHandler.setDefault(new CookieManager(null /*=default in-memory store*/, CookiePolicy.ACCEPT_ALL));
		ResteasyClient client = new ResteasyClientBuilder().build();
		Response getResponse = client.target("http://localhost:8080/spring-mvc-showcase/form").request().get();
		String responseBody = getResponse.readEntity(String.class);
		String csrfToken = extractCsrfToken(responseBody);

		Form form = new Form();
		form.param("name", "Joe")
			.param("age", "56")
			.param("birthDate", "1941-12-16")
			.param("phone", "(347) 888-1234")
			.param("currency", "$123.33")
			.param("percent", "89%")
			.param("inquiry", "comment")
			.param("inquiryDetails", "what is?")
			.param("additionalInfo[mvc]", "true")
			.param("_additionalInfo[mvc]", "on")
			.param("additionalInfo[java]", "true")
			.param("_additionalInfo[java]", "on")
			.param("subscribeNewsletter", "false");
		Entity<Form> formEntity = Entity.form(form);
		Response postResponse = client.target("http://localhost:8080/spring-mvc-showcase/form").request()
				.header("X-CSRF-TOKEN", csrfToken)
				.post(formEntity);
		Assert.assertEquals(302, postResponse.getStatus());
	}

	private String extractCsrfToken(final String responseBody) {
		for (String line : responseBody.split("\n")) {
			Pattern pattern = Pattern.compile("<input type=\"hidden\" name=\"_csrf\" value=\"([0-9a-z-]+)\" />");
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches()) {
				return matcher.group(1);
			}
		}
		Assert.fail("failed to extract CSRF token from response");
		return null;
	}
}
