package com.website.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.core.EntityFactory;
import com.website.core.dao.SampleDao;
import com.website.entities.SampleEntity;

@Service
@Transactional
public class SampleServiceImpl implements ISampleService{
	@Autowired
	private SampleDao sampleDao;
	
	public String addSample(){
		SampleEntity sample = EntityFactory.createEntity(SampleEntity.class);
		sampleDao.addBean(sample);
		System.err.println(sample);
		return sample.toString();
	}
}
