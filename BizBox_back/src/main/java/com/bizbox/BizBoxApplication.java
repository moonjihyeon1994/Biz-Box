package com.bizbox;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bizbox.jwt.interceptor.JwtInterceptor;

@SpringBootApplication
public class BizBoxApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(BizBoxApplication.class, args);
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/auth/**")
				.excludePathPatterns(Arrays.asList("/auth/user/**"));
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}
}
