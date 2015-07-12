package com.website.core.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.website.core.dao.SampleDao;
import com.website.entities.SampleEntity;

@Service
@Transactional
public class SampleServiceImpl implements ISampleService{
	@Resource
	private SampleDao sampleDao;
	
	public String addSample(){
		SampleEntity sample = new SampleEntity();
		sampleDao.addBean(sample);
		System.err.println(sample);
		return sample.toString();
	}
}
