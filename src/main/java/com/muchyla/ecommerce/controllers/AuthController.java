package com.muchyla.ecommerce.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.muchyla.ecommerce.models.User;
import com.muchyla.ecommerce.security.UserPricipal;
import com.muchyla.ecommerce.services.ITwoFactorCodeService;
import com.muchyla.ecommerce.services.IUserService;

@Controller
public class AuthController {
	
	private IUserService userService;
	private ITwoFactorCodeService twoFactorService;
	
	public AuthController(IUserService userService,ITwoFactorCodeService twoFactorService) {
		this.userService = userService;
		this.twoFactorService = twoFactorService;
	}

	@GetMapping("/auth/login")
		public String getLoginPage() {return "loginPageTh";}
	
	@GetMapping("/oauth2/code/google")
	public String getCreditentialsFromGoogle(OAuth2AuthenticationToken oAuth2) {

		UserPricipal pricipal = userService.loadGoogleUser(oAuth2.getPrincipal().getAttribute("email"), oAuth2.getPrincipal().getAttribute("name"));
		Authentication auth = new UsernamePasswordAuthenticationToken(pricipal, pricipal.getPassword(), pricipal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);

		return "redirect:/products";
	}
	
	@GetMapping("/twoFactorAuth")
	public String getTwoFactorPage() {
		User user = userService.getLoggedUser();
		if(!user.isTwoFactorEnabled()) {
			return "redirect:/products";
		}else {
			System.out.println(twoFactorService.generateCode(user).toString()); 
			return "twoFactorPage";
		}
	}
	
	@PostMapping("/twoFactorAuth")
	public String twoFactorAuth(@RequestParam("code")Long twoFactorCode) {
		return (twoFactorService.checkCode(userService.getLoggedUser(), twoFactorCode)) ? "redirect:/test" : "redirect:/twoFactorAuth";
	}
	
}
