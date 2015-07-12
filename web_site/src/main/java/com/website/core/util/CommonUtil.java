package com.website.core.util;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

public class CommonUtil {
	private CommonUtil(){		
	}
	private static Logger logger = Logger.getLogger(CommonUtil.class);
	public static Query fillHqlQuery(Query q,Object... param){
		for(int i =0;i<param.length;i++){
			if(param[i]!=null){
				Object o = param[i];
				if(o instanceof String){
					q.setString(i, (String)o);
				}else if(o instanceof Boolean){
					q.setBoolean(i, (Boolean)o);
				}else if(o instanceof Integer){
					q.setInteger(i, (Integer) o);
				}else if(o instanceof Long){
					q.setLong(i, (Long)o);
				}else if(o instanceof Date){
					q.setDate(i, (Date)o);
				}else if(o instanceof Double){
					q.setDouble(i, (Double)o);
				}else {
					logger.error("unrecognized param");
					return null;
				}
			}
		}
		return q;
	}
	
	public static SQLQuery fillSQLQuery(SQLQuery q,Object... param){
		for(int i =0;i<param.length;i++){
			if(param[i]!=null){
				Object o = param[i];
				if(o instanceof String){
					q.setString(i, (String)o);
				}else if(o instanceof Boolean){
					q.setBoolean(i, (Boolean)o);
				}else if(o instanceof Integer){
					q.setInteger(i, (Integer) o);
				}else if(o instanceof Long){
					q.setLong(i, (Long)o);
				}else if(o instanceof Date){
					q.setDate(i, (Date)o);
				}else if(o instanceof Double){
					q.setDouble(i, (Double)o);
				}else {
					logger.error("unrecognized param");
					return null;
				}
			}
		}
		return q;
	}
}
