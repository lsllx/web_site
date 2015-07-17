package com.website.core.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.website.admin.service.IAdminService;
import com.website.core.service.ISampleService;


@Controller
@RequestMapping("/sample")
public class SampleController extends CoreController{
	@Resource
	private ISampleService service;
	@Resource
	private IAdminService adminService;
	@RequestMapping("toSample")
	public String addSample(HttpServletRequest request){
		logger.debug("test success");
		System.err.println(adminService.getAdminUserById(1));
		request.setAttribute("message",service.addSample());
		return "/sample";
	}

}
