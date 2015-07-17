package com.website.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name = "test")
@Entity
public class SampleEntity extends BaseEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "increment")
	private int id;

	@Column(name = "uuid")
	protected String UUID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

}
