package com.springmvc.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class TestController {

	@RequestMapping("/")
	@ResponseBody
	public String test(){
		return "test";
	}
}
