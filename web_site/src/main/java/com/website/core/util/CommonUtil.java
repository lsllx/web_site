package com.website.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import sun.misc.BASE64Encoder;

public class CommonUtil {
	private CommonUtil() {
	}

	private static Logger logger = Logger.getLogger(CommonUtil.class);

	/**
	 * TODO unused need exam
	 * 
	 * @param q
	 * @param param
	 * @return
	 */
	public static Query fillHqlQuery(Query q, Object... param) {
		for (int i = 0; i < param.length; i++) {
			if (param[i] != null) {
				Object o = param[i];
				if (o instanceof String) {
					q.setString(i, (String) o);
				} else if (o instanceof Boolean) {
					q.setBoolean(i, (Boolean) o);
				} else if (o instanceof Integer) {
					q.setInteger(i, (Integer) o);
				} else if (o instanceof Long) {
					q.setLong(i, (Long) o);
				} else if (o instanceof Date) {
					q.setDate(i, (Date) o);
				} else if (o instanceof Double) {
					q.setDouble(i, (Double) o);
				} else {
					logger.error("unrecognized param");
					return null;
				}
			}
		}
		return q;
	}

	/**
	 * TODO unused need exam
	 * 
	 * @param q
	 * @param param
	 * @return
	 */
	public static SQLQuery fillSQLQuery(SQLQuery q, Object... param) {
		for (int i = 0; i < param.length; i++) {
			if (param[i] != null) {
				Object o = param[i];
				if (o instanceof String) {
					q.setString(i, (String) o);
				} else if (o instanceof Boolean) {
					q.setBoolean(i, (Boolean) o);
				} else if (o instanceof Integer) {
					q.setInteger(i, (Integer) o);
				} else if (o instanceof Long) {
					q.setLong(i, (Long) o);
				} else if (o instanceof Date) {
					q.setDate(i, (Date) o);
				} else if (o instanceof Double) {
					q.setDouble(i, (Double) o);
				} else {
					logger.error("unrecognized param");
					return null;
				}
			}
		}
		return q;
	}

	/**
	 * UUID generator
	 * 
	 * @return
	 */
	public static UUID getUUID() {
		return UUID.randomUUID();
	}

	/**
	 * encrypt password
	 * 
	 * @param original
	 * @return
	 */
	public static String MD5AndBase64Encrypt(String original) {
		byte[] b = original.getBytes();
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error("", e);
			return null;
		}
		digest.update(b);
		byte[] md = digest.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < md.length; i++) {
			int v = md[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				sb.append(0);
			}
			sb.append(hv);
		}
		return new BASE64Encoder().encode(sb.toString().getBytes());
	}

	public static LocalDateTime parseToDate(Timestamp time) {
		// LocalDateTime ldt = java.time.LocalDate.
		if (time == null)
			return LocalDateTime.now();
		return time.toLocalDateTime();
	}

	public static String parseToDateString(Timestamp time) {
		return CommonUtil.parseToDate(time).format(
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public static void main(String[] argv) {
		System.err.println();
	}
}
