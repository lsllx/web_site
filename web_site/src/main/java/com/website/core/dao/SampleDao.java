package com.website.core.dao;

import javax.annotation.Resource;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.website.entities.SampleEntity;

@Repository
public class SampleDao {
	@Resource
	private SessionFactory factory;
	public void addSampleEntity(SampleEntity sample){
		Session session = factory.getCurrentSession();
		//session.beginTransaction();
		System.err.println(session.getFlushMode());
		//session.setFlushMode(FlushMode.AUTO);
		session.save(sample);
		//session.getTransaction().commit();
		//Transaction ts = session.beginTransaction();
		
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
