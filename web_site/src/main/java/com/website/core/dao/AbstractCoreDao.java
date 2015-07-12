package com.website.core.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.website.core.util.CommonUtil;

@Repository
public class AbstractCoreDao {
	@Resource
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger(getClass());

	public SessionFactory getSessionFactory() {
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

	public void addBean(Object object) {
		Session session = prepareSession();
		session.save(object);
		closeSession(session);
	}

	public void deleteBean(Object object) {
		Session session = prepareSession();
		session.delete(object);
		closeSession(session);
	}

	public void updateBean(Object object) {
		Session session = prepareSession();
		session.update(object);
		closeSession(session);
	}

	public void mergeBean(Object object) {
		Session session = prepareSession();
		session.merge(object);
		closeSession(session);
	}

	private SQLQuery createSqlQuery(Session session, String sql, Object... params) {
		SQLQuery q = session.createSQLQuery(sql);
		q = CommonUtil.fillSQLQuery(q, params);
		return q;
	}

	private Query createHqlQuery(Session session, String hql, Object... params) {
		Query q = session.createQuery(hql);
		q = CommonUtil.fillHqlQuery(q, params);
		return q;
	}

	public int deleteByKey(Class<?> clazz, String... keyValues) {
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
		return createHqlQuery(session, hql.toString(), (Object[]) values).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public <V> List<V> queryListById(Class<?> clazz, int id, int page, int pageSize, String orderBy) {
		Session session = prepareSession();
		Query query = null;
		String order = "";
		if (orderBy != null && !orderBy.equals("")) {
			order = "order by ?";
			query = createHqlQuery(session, "from " + clazz.getSimpleName() + "where id = ? " + order, id, orderBy);
		} else {
			query = createHqlQuery(session, "from " + clazz.getSimpleName() + "where id = ? " + order, id);
		}
		if (page >= 0 && pageSize > 0) {
			query.setFirstResult(page * pageSize);
			query.setMaxResults(page * pageSize + pageSize);
		}
		return (List<V>) query.list();
	}

	@SuppressWarnings("unchecked")
	protected <V> List<V> queryBySql(String sql, Object... params) {
		Session session = prepareSession();
		SQLQuery q = createSqlQuery(session, sql, params);
		return (List<V>) q.list();
	}

	@SuppressWarnings("unchecked")
	protected <V> V querySingleBySql(String sql, Object... params) {
		Session session = prepareSession();
		SQLQuery q = createSqlQuery(session, sql, params);
		return (V) q.uniqueResult();
	}

	public Logger getLogger() {
		return logger;
	}
}
