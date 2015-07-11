package com.website.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name = "test")
@Entity
public class SampleEntity {
	@Column(name = "id")
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "increment")
	private int id;
	@Id
	@GeneratedValue(generator = "test")
	@GenericGenerator(name = "test", strategy = "uuid")
	@Column(name = "uuid")
	private String uuid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "SampleEntity [id=" + id + ", uuid=" + uuid + "]";
	}
	
}
