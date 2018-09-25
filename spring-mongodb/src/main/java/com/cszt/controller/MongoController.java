package com.cszt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongoController {
	
	@RequestMapping("/mongo")
	public String hello(){
		return "hello,MongoDB!";
	}

}
