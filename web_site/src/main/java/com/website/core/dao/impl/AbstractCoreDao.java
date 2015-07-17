package com.website.core.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.core.util.CommonUtil;
import com.website.entities.BaseEntity;

@Repository
public class AbstractCoreDao<T extends BaseEntity> {
	@Autowired
	private SessionFactory sessionFactory;
	protected Class<T> clazz;
	private Logger logger = Logger.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public AbstractCoreDao() {
		@SuppressWarnings("rawtypes")
		Class clazz = getClass();

		while (clazz != Object.class) {
			Type t = clazz.getGenericSuperclass();
			if (t instanceof ParameterizedType) {
				Type[] args = ((ParameterizedType) t).getActualTypeArguments();
				if (args[0] instanceof Class) {
					this.clazz = (Class<T>) args[0];
					break;
				}
			}
			clazz = clazz.getSuperclass();
		}
	}

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private Session prepareSession() {
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}

	private boolean closeSession(Session session) {
		session.flush();
		session.getTransaction().commit();
		// manage by spring
		// session.close();
		return true;
	}

	public void addBean(T bean) {
		Session session = prepareSession();
		modifyupdateTime(bean);
		session.save(bean);
		closeSession(session);
	}

	@SuppressWarnings("unchecked")
	public T getBeanById(int id) {
		Session session = prepareSession();
		T bean = (T) session.get(clazz, id);
		closeSession(session);
		return bean;
	}

	public void deleteBean(T bean) {
		Session session = prepareSession();
		session.delete(bean);
		closeSession(session);
	}

	public void updateBean(T bean) {
		Session session = prepareSession();
		modifyupdateTime(bean);
		session.update(bean);
		closeSession(session);
	}

	public void mergeBean(T bean) {
		Session session = prepareSession();
		modifyupdateTime(bean);
		session.merge(bean);
		closeSession(session);
	}

	private SQLQuery createSqlQuery(Session session, String sql,
			Object... params) {
		SQLQuery q = session.createSQLQuery(sql);
		q = CommonUtil.fillSQLQuery(q, params);
		return q;
	}

	private Query createHqlQuery(Session session, String hql, Object... params) {
		Query q = session.createQuery(hql);
		q = CommonUtil.fillHqlQuery(q, params);
		return q;
	}

	public int deleteByKey(String... keyValues) {
		Session session = prepareSession();
		StringBuffer hql = new StringBuffer("delete from %s ");
		if (keyValues.length % 2 > 0) {
			logger.error("key value size not correct");
			return -1;
		}
		String[] values = new String[keyValues.length / 2];
		for (int i = 0; i < keyValues.length; i++) {
			if (i == 0) {
				hql.append("where " + keyValues[i] + "=");
			} else {
				if (i % 2 == 0)
					hql.append(" and " + keyValues[i] + "= ");
				else {
					hql.append(" %s ");
				}
			}
		}
		return createHqlQuery(session, hql.toString(), (Object[]) values)
				.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<T> queryListById(int id, int page, int pageSize, String orderBy) {
		Session session = prepareSession();
		Query query = null;
		String order = "";
		if (orderBy != null && !orderBy.equals("")) {
			order = "order by ?";
			query = createHqlQuery(session, "from " + clazz.getSimpleName()
					+ "where id = ? " + order, id, orderBy);
		} else {
			query = createHqlQuery(session, "from " + clazz.getSimpleName()
					+ "where id = ? " + order, id);
		}
		if (page >= 0 && pageSize > 0) {
			query.setFirstResult(page * pageSize);
			query.setMaxResults(page * pageSize + pageSize);
		}
		return (List<T>) query.list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> queryBySql(String sql, Object... params) {
		Session session = prepareSession();
		SQLQuery q = createSqlQuery(session, sql, params);
		return (List<T>) q.list();
	}

	@SuppressWarnings("unchecked")
	protected T querySingleBySql(String sql, Object... params) {
		Session session = prepareSession();
		SQLQuery q = createSqlQuery(session, sql, params);
		return (T) q.uniqueResult();
	}

	private void modifyupdateTime(Object object) {
		if (object instanceof BaseEntity) {
			((BaseEntity) object).setUpdateTime(Timestamp.valueOf(LocalDateTime
					.now()));
		}
	}

	public Logger getLogger() {
		return logger;
	}
}
