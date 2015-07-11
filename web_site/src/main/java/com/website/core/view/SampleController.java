package com.website.core.view;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.website.core.service.SampleService;



@Controller
@RequestMapping("/sample")
public class SampleController {
	private static Logger logger = Logger.getLogger(SampleController.class);
	@Resource
	private SampleService service;
	@RequestMapping("/*.*")
	public ModelAndView sampleAdd(HttpRequest requie){
		logger.debug("test success");
		return service.addSample();
	}

}
