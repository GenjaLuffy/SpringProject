package com.appsoft.springdemo.service;

import com.appsoft.springdemo.model.User;

public interface UserService {
	
	void signup(User usr);
	User login(String un, String psw);
	
}
