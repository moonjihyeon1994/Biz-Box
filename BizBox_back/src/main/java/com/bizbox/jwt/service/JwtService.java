package com.bizbox.jwt.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bizbox.vo.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	public String create(final User user) {
		log.trace("time: {}", expireMin);
		final JwtBuilder builder = Jwts.builder();
		
		builder.setHeaderParam("type", "JWT");
		
		builder.setSubject("로그인토큰")
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*expireMin))
				.claim("User", user).claim("second", "더 담고 싶은거 있어?");
		
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		final String jwt = builder.compact();
		log.debug("토큰발생: {}", jwt);
		return jwt;
	}
	
	public void checkValid(final String jwt) {
		log.trace("토큰 점검: {}", jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	
	public Map<String, Object> get(final String jwt){
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch(final Exception e) {
			throw new RuntimeException();
		}
		log.trace("claims: {}", claims);
		return claims.getBody();
	}
}
