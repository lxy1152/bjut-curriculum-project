package com.finalwork.android.e_commerce.model.entity;

import java.util.Date;

/**
 * ��ƷƷ��ʵ��
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Brand implements java.io.Serializable {

	/*
	 *  create table wm_brand
		(
		   id                   bigint not null,
		   name                 varchar(255) not null,
		   `join`               date not null,
		   rate                 numeric(3,2) not null,
		   total_saled          bigint not null,
		   primary key (id)
		);
	 */
	
	private Long id;
	
	private String brandName;
	
	private Date joinTime;
	
	private Double raxRate;
	
	private Long totalSaled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public Double getRaxRate() {
		return raxRate;
	}

	public void setRaxRate(Double raxRate) {
		this.raxRate = raxRate;
	}

	public Long getTotalSaled() {
		return totalSaled;
	}

	public void setTotalSaled(Long totalSaled) {
		this.totalSaled = totalSaled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((joinTime == null) ? 0 : joinTime.hashCode());
		result = prime * result + ((raxRate == null) ? 0 : raxRate.hashCode());
		result = prime * result + ((totalSaled == null) ? 0 : totalSaled.hashCode());
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
		Brand other = (Brand) obj;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (joinTime == null) {
			if (other.joinTime != null)
				return false;
		} else if (!joinTime.equals(other.joinTime))
			return false;
		if (raxRate == null) {
			if (other.raxRate != null)
				return false;
		} else if (!raxRate.equals(other.raxRate))
			return false;
		if (totalSaled == null) {
			if (other.totalSaled != null)
				return false;
		} else if (!totalSaled.equals(other.totalSaled))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", brandName=" + brandName + ", joinTime=" + joinTime + ", raxRate=" + raxRate
				+ ", totalSaled=" + totalSaled + "]";
	}
}
