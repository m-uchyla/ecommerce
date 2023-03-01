package com.muchyla.ecommerce.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.muchyla.ecommerce.models.User;
import com.muchyla.ecommerce.security.UserPricipal;
import com.muchyla.ecommerce.services.IUserService;
import com.nimbusds.jose.proc.SecurityContext;

import antlr.Token;

@Controller
public class TestController {
	
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	@Autowired
	private IUserService userService;


	@GetMapping("/test")public String test() {return "testPageTh";}
	
	@GetMapping("/oauth2/code/google123")
	public String getCreditentialsFromGoogle(OAuth2AuthenticationToken auth, Authentication authentication) {
		OAuth2User user = auth.getPrincipal();
		System.out.println(user.toString());
		
		//Long id = Long.valueOf(authentication.getName());
		String username = user.getAttribute("name");
		String email = user.getAttribute("email");
		
		System.out.println("AUTH: [username]"+username+" [email]"+email);
		
		//OAuth2User oAuth2User = auth.getPrincipal();
		//User user = new User(oAuth2User.getAttribute(null), null, null)
		
		return "redirect:/test";
	}
	
	@GetMapping("/qw")
	public String getCreditentialsFromGoogle(Authentication authentication) {
		System.out.println("\n AUTH TEST");
		System.out.println("Name: "+authentication.getName());
		System.out.println("IsAuthenticated: "+authentication.isAuthenticated());
		System.out.println("Object: "+authentication.toString());
		System.out.println("Object: "+authentication.getPrincipal().toString());
		
		System.out.println("\n USER: "+userService.getLoggedUser().toString());
		
		return "redirect:/test";
	}
	
	@GetMapping("/users")
	public String getAllUsers() {
		userService.getUsersList().forEach(user ->{
			System.out.println("\n USER:"+user.toString());
		});
		
		return "redirect:/test";
	}
}
