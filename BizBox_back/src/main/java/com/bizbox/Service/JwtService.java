package com.bizbox.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.apache.tomcat.jni.Buffer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bizbox.vo.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtService {
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	public String create(final User user) {
		log.trace("time: {}", expireMin);
		final JwtBuilder builder = Jwts.builder();
		
		builder.setHeaderParam("typ", "JWT");
		
		builder.setSubject("로그인토큰")
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*expireMin))
				.claim("User", user);
//				.claim("second", "더 담고 싶은거 있어?");
				
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
//		builder.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(salt.getBytes()));
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
