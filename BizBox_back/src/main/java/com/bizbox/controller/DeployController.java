package com.bizbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeployController {

	@RequestMapping(value="/")
	public String main() {
		return "index";
	}
	@Controller
	public class RouteController {
	    @RequestMapping(value = "/{path:[^\\.]*}")
	    public String redirect() {
	        return "forward:/";
	    }
	}
}
