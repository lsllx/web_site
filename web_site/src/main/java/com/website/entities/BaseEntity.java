package com.website.entities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

import com.website.core.util.CommonUtil;

public abstract class BaseEntity {
	protected String UUID;
	private Timestamp insertTime;
	private Timestamp updateTime;

	String getUUID() {
		return UUID;
	}

	public void setUUID(String uuid) {
		this.UUID = uuid;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		sb.append("[" + this.getClass().getSimpleName() + ": ");
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (!field.getName().toLowerCase().equals("inserttime")
					&& !field.getName().toLowerCase().equals("updatetime"))
				try {
					sb.append(field.getName());
					String getter = "get"
							+ field.getName().substring(0, 1).toUpperCase()
							+ field.getName().substring(1);
					sb.append("= ");
					Object output = this.getClass().getMethod(getter)
							.invoke(this);
					sb.append(output.toString());
					if (i + 1 != fields.length)
						sb.append(" , ");

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		sb.append(" insertTime= "
				+ CommonUtil.parseToDateString(getInsertTime())
				+ " , updateTime= "
				+ CommonUtil.parseToDateString(getUpdateTime()) + "]");
		return sb.toString();
	}
}
