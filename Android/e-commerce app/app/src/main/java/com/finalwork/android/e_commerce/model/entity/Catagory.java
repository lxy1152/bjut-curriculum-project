package com.finalwork.android.e_commerce.model.entity;
/**
 * ��Ʒ����ʵ��
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Catagory implements java.io.Serializable {

	/*
	 * create table wm_catagory
		(
		   id                   bigint not null,
		   name                 varchar(255) not null,
		   upper                bigint,
		   primary key (id)
		);
	 */
	
	private Long id;
	
	private String name;
	
	private Catagory upperCatagory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Catagory getUpperCatagory() {
		return upperCatagory;
	}

	public void setUpperCatagory(Catagory upperCatagory) {
		this.upperCatagory = upperCatagory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((upperCatagory == null) ? 0 : upperCatagory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Catagory other = (Catagory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (upperCatagory == null) {
			if (other.upperCatagory != null)
				return false;
		} else if (!upperCatagory.equals(other.upperCatagory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Catagory [id=" + id + ", name=" + name + ", upperCatagory=" + upperCatagory + "]";
	}
}
