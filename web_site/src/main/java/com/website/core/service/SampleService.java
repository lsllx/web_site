package com.website.core.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.website.core.dao.SampleDao;
import com.website.entities.SampleEntity;

@Service
public class SampleService {
	@Resource
	private SampleDao sampleDao;
	
	public ModelAndView addSample(){
		SampleEntity sample = new SampleEntity();
		ModelAndView mv = new ModelAndView();
		sampleDao.addSampleEntity(sample);
		mv.addObject("message",sample.toString());
		return mv;
	}
}
