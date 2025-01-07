package com.tsb.service;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsb.component.JwtTokenUtils;

@RequestMapping(path = "/api")
@RestController
public class TokenService {
	
	@RequestMapping(path = "/validate", consumes = "application/json")
	public ResponseEntity tokenValid(@RequestBody HashMap<String, String> user) {
		
		String token = new JwtTokenUtils().generateToken(user);
		ResponseEntity resEntity = ResponseEntity.status(HttpStatus.OK).body(token);
		return resEntity;
	}
}
