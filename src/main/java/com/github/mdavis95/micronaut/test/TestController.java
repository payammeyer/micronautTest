package com.github.mdavis95.micronaut.test;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/test/")
public class TestController {

	@Filter("/test/secure/**")
	public static class AuthSecureFilter extends BaseSecurityFilter {

	}

	@Get("/public/list")
	@Produces(MediaType.TEXT_PLAIN)
	public String simpleTest() {
		return "Hello World";
	}

	@Get("/secure/list")
	@Produces(MediaType.TEXT_PLAIN)
	public String secureTest() {
		return "Hello Secret World";
	}
}
