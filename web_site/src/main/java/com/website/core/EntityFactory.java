package com.website.core;

import org.apache.log4j.Logger;

import com.website.core.util.CommonUtil;
import com.website.entities.BaseEntity;

public class EntityFactory {
	private static Logger logger = Logger.getLogger(EntityFactory.class);

	private EntityFactory() {
	}

	@SuppressWarnings("unchecked")
	public static <T extends BaseEntity> T createEntity(String entityName) {
		T entity = null;
		try {
			Class<?> clazz = Class
					.forName(BaseEntity.class.getName().replace(BaseEntity.class.getSimpleName(), "") + entityName);
			entity = (T) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			logger.error("invalid entity name :" + entityName, e);
			return null;
		} catch (InstantiationException e) {
			logger.error("instantial faled! ClassName:" + entityName, e);
		} catch (IllegalAccessException e) {
			logger.error("illegalAccess! ClassName:" + entityName, e);
		}
		return (T) addUUID(entity);
	}

	@SuppressWarnings("unchecked")
	public static <T extends BaseEntity> T createEntity(Class<? extends BaseEntity> clazz) {
		if (clazz == null) {
			return null;
		}

		T entity = null;
		try {
			entity = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			logger.error("instantial faled! ClassName:" + clazz.getName(), e);
		} catch (IllegalAccessException e) {
			logger.error("illegalAccess! ClassName:" + clazz.getName(), e);
		}
		return (T) addUUID(entity);
	}
	
	private static BaseEntity addUUID(BaseEntity entity){
		//entity.getClass().("setUUID", String.class);
		entity.setUUID(CommonUtil.getUUID().toString());
		return entity;
	}
}
