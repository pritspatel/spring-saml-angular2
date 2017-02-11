package com.prits.samlapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {
	private static final Logger logger = LoggerFactory.getLogger(GreetingsController.class);

	@RequestMapping(value = "/greetings",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public @ResponseBody Greeting sayHello(){
		logger.info("Serving greetings controller.....");
		Greeting g = new Greeting("Hello SAML");
		return g;
	}
}
