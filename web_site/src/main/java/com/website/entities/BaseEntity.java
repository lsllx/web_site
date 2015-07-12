package com.website.entities;

public abstract class BaseEntity {
	protected String UUID;

	abstract String getUUID();
	public void setUUID(String uuid){
		this.UUID = uuid;
	}
}
