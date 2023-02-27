package com.muchyla.ecommerce.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.muchyla.ecommerce.security.UserPricipal;
import com.muchyla.ecommerce.services.IUserService;

@Controller
public class AuthController {
	
	private IUserService userService;
	
	public AuthController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/auth/login")
		public String getLoginPage() {return "loginPageTh";}
	
	@GetMapping("/oauth2/code/google")
	public String getCreditentialsFromGoogle(OAuth2AuthenticationToken oAuth2) {

		UserPricipal pricipal = userService.loadGoogleUser(oAuth2.getPrincipal().getAttribute("email"), oAuth2.getPrincipal().getAttribute("name"));
		Authentication auth = new UsernamePasswordAuthenticationToken(pricipal, pricipal.getPassword(), pricipal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		return "redirect:/test";
	}
	
}