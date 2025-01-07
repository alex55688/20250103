package com.tsb.component;

import java.io.Serializable;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtils implements Serializable{
	
	private static final String CRECT_KEY = "Come to TSB family Come to TSB family";
	
	private static final Key KEY = Keys.hmacShaKeyFor(CRECT_KEY.getBytes());
	
	public String generateToken(HashMap<String, String> userDetails) {
		
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("userName", userDetails.get("userName"));
		
//		String token = Jwts.builder().setClaims(claims).setExpiration( new Date(Instant.now().toEpochMilli()+60000))
//			.signWith(SignatureAlgorithm.HS512, key).compact();
		
		String token = Jwts.builder().claims().empty().add(claims).and().expiration(new Date(Instant.now().toEpochMilli()+60000))
			.signWith(KEY).compact();
		
		return token;
	}
}
