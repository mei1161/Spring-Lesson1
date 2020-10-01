package com.example.demo.login.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.login.domain.service.RestService;
import com.example.demo.login.domain.User;
@RestController
public class UserRestController {
	
	@Autowired
	RestService service;
	
	//ユーザー全件取得
	@GetMapping("/rest/get")
	public List<User> getUserMany(){
		
		//ユーザー全件取得
		return service.selectMany();
	}
	
	//ユーザー1件取得
	@GetMapping("/rest/get/{id:.+}")
	public User getUserOne(@PathVariable("id")String userId) {
		
		//ユーザー1件取得
		return service.selectOne(userId);
	}
}
