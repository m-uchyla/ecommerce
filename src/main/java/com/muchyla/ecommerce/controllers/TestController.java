package com.muchyla.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test")public String test() {return "testPage";}
	
	@GetMapping("/auth/login")public String getLoginPage() {return "loginPage";}
}
