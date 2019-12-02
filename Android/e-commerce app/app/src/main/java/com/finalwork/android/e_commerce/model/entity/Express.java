package com.finalwork.android.e_commerce.model.entity;

import java.util.Date;

/**
 * ��ݹ�˾ʵ��
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Express implements java.io.Serializable {

	/*
	 * create table wm_express
		(
		   id                   bigint not null,
		   name                 varchar(255) not null,
		   contackNo            varchar(255) not null,
		   `join`               date not null,
		   rate                 numeric(3,2) not null,
		   total_used           bigint not null,
		   primary key (id)
		);
	 */
	
	private Long id;
	
	private String expressName;
	
	private String contactNumber;
	
	private Date joinTime;
	
	private Double raxRate;
	
	private Long totalUsed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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

	public Long getTotalUsed() {
		return totalUsed;
	}

	public void setTotalUsed(Long totalUsed) {
		this.totalUsed = totalUsed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((expressName == null) ? 0 : expressName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((raxRate == null) ? 0 : raxRate.hashCode());
		result = prime * result + ((totalUsed == null) ? 0 : totalUsed.hashCode());
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
		Express other = (Express) obj;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (expressName == null) {
			if (other.expressName != null)
				return false;
		} else if (!expressName.equals(other.expressName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (raxRate == null) {
			if (other.raxRate != null)
				return false;
		} else if (!raxRate.equals(other.raxRate))
			return false;
		if (totalUsed == null) {
			if (other.totalUsed != null)
				return false;
		} else if (!totalUsed.equals(other.totalUsed))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Express [id=" + id + ", expressName=" + expressName + ", contactNumber=" + contactNumber + ", raxRate="
				+ raxRate + ", totalUsed=" + totalUsed + "]";
	}
}
