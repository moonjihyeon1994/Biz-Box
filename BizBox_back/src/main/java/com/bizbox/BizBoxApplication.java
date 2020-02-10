package com.bizbox;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bizbox.interceptor.JwtInterceptor;

@Cacheable
@SpringBootApplication
public class BizBoxApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BizBoxApplication.class, args);
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/user/**") // 검사하는 부분
				.excludePathPatterns(Arrays.asList("/kakao/login/**")) // 예외처리하는 부분
				.excludePathPatterns(Arrays.asList("/user/login/**"))
				.excludePathPatterns(Arrays.asList("/user/signup/**"));
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*")
						.exposedHeaders("jwt-auth-token");
			}
		};
	}
}
