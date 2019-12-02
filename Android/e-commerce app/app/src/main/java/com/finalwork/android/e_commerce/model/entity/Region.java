package com.finalwork.android.e_commerce.model.entity;
/**
 * ����ʵ����
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Region implements java.io.Serializable {

	/*
	 * create table wm_region
		(
		   id                   bigint not null,
		   upper                bigint,
		   name                 varchar(255) not null,
		   used                 bigint not null,
		   primary key (id)
		);
	 */
	
	private Long id;
	
	private Region upperRegion;
	
	private String name;
	
	private Long usedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Region getUpperRegion() {
		return upperRegion;
	}

	public void setUpperRegion(Region upperRegion) {
		this.upperRegion = upperRegion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Long usedTime) {
		this.usedTime = usedTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((upperRegion == null) ? 0 : upperRegion.hashCode());
		result = prime * result + ((usedTime == null) ? 0 : usedTime.hashCode());
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
		Region other = (Region) obj;
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
		if (upperRegion == null) {
			if (other.upperRegion != null)
				return false;
		} else if (!upperRegion.equals(other.upperRegion))
			return false;
		if (usedTime == null) {
			if (other.usedTime != null)
				return false;
		} else if (!usedTime.equals(other.usedTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", upperRegion=" + upperRegion + ", name=" + name + ", usedTime=" + usedTime + "]";
	}	
}
