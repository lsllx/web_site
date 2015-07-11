package com.website.core.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.website.core.service.SampleService;



@Controller
@RequestMapping("/sample")
public class SampleController {
	private static Logger logger = Logger.getLogger(SampleController.class);
	@Resource
	private SampleService service;
	@RequestMapping("toSample")
	public String addSample(){
		logger.debug("test success");
		service.addSample().toString();
		return "/sample";
	}

}
