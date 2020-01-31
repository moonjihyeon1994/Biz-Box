package com.bizbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.crawler.NaverNewsCrawler;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin({ "*" })
public class CrawlingController {
	
	@Autowired
	NaverNewsCrawler NNcrawler;
	
	@GetMapping("/crawling/naver")
	public ResponseEntity<Object> getNaverNews(){
		try {
			String news = NNcrawler.getNaverNews();
			return new ResponseEntity<Object>(news,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}
