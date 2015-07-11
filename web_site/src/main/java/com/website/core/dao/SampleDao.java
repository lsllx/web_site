package com.website.core.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.website.entities.SampleEntity;

@Repository
public class SampleDao {
	@Resource
	private SessionFactory factory;
	
	public void addSampleEntity(SampleEntity sample){
		factory.getCurrentSession().save(sample);
	}
	
	public boolean deleteSampleEntity(SampleEntity sample){
		factory.getCurrentSession().delete(sample);
		return true;
	}
	
	public boolean updateSampleEntity(SampleEntity sample){
		factory.getCurrentSession().update(sample);
		return true;
	}

}
