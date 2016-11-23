package com.fanjc.open.controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping("/test")
	private String test() {

		
		logger.debug("测试");
		logger.error("错误");
		return "test";
	}
}
