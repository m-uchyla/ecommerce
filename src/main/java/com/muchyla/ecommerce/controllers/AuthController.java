package com.muchyla.ecommerce.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/login")
		public String getLoginPage() {return "loginPageTh";}
	
	@GetMapping("/oauth2/code/google")
		public String getCreditentialsFromGoogle(OAuth2AuthenticationToken auth) {
			OAuth2User user = auth.getPrincipal();
			System.out.println(user.toString());
			return "redirect:/test";
		}
	
}
