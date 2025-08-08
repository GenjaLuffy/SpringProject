package com.appsoft.springdemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appsoft.springdemo.model.User;
import com.appsoft.springdemo.repository.ProductRepository;
import com.appsoft.springdemo.service.UserService;
import com.appsoft.springdemo.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class UserController {
@Autowired
    private  UploadController uploadController;
	
@Autowired
private ProductRepository prodRepo;


	@Autowired
	private UserService userService;


    UserController(UploadController uploadController) {
        this.uploadController = uploadController;
    }
	
	
	@GetMapping("/home")
	public String getHome() {
		
		return "Home";
	}
	
	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("pList",prodRepo.findAll());
		return"CustomerHome";
	}
	@GetMapping("/login")
	public String getLogin() {
		
		return "LoginForm";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute User u, Model model, HttpSession session, @RequestParam("g-recaptcha-response") String greCode) throws IOException {
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		User usr = userService.login(u.getUsername(), u.getPassword());
		
		if (VerifyRecaptcha.verify(greCode)) {
			if (usr != null) {
				
				log.info("----- User Login Success -----");
				// model.addAttribute("uname", usr.getFname());
				session.setAttribute("activeuser", usr);
				session.setMaxInactiveInterval(500);
				if(usr.getRole().equals("CUSTOMER")) {
					
					model.addAttribute("pList",prodRepo.findAll());
					return"CustomerHome";
				}
				return "Home";
			}else {
				log.info("----- User Login Failed -----");
				model.addAttribute("error", "User Not Found !!");
				return "LoginForm";
			}
		}	
		log.info("----- User Login Failed GReCaptcha Failed -----");
		model.addAttribute("error", "You Are Robot !!");
		return "LoginForm";
	}
	
	
	@GetMapping("/signup")
	public String getSignup() {
		
		
		return "SignupForm";
	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User u) {
		
		u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
		userService.signup(u);
		return "LoginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("----- User LogOut -----");
		session.invalidate();
		
		return"LoginForm";
	}
	
	
	@GetMapping("/profile")
	public String getProfile() {
		
		return"Profile";
	}
}
