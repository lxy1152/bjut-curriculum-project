package com.finalwork.android.e_commerce.model.entity;
/**
 * ��ݷ����۸�ʵ����
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ExpressRegion implements java.io.Serializable {

	/*
	 * create table wm_express_region
		(
		   id                   bigint not null,
		   region               bigint not null,
		   express              bigint not null,
		   partition            varchar(255) not null,
		   required             numeric(5,2) not null,
		   addition             numeric(5,2) not null,
		   primary key (id)
		);
	 */
	
	private Long id;
	
	private Region region;
	
	private Express express;
	
	private String partition;
	
	private Double required;
	
	private Double addition;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Express getExpress() {
		return express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}

	public String getPartition() {
		return partition;
	}

	public void setPartition(String partition) {
		this.partition = partition;
	}

	public Double getRequired() {
		return required;
	}

	public void setRequired(Double required) {
		this.required = required;
	}

	public Double getAddition() {
		return addition;
	}

	public void setAddition(Double addition) {
		this.addition = addition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addition == null) ? 0 : addition.hashCode());
		result = prime * result + ((express == null) ? 0 : express.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((partition == null) ? 0 : partition.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((required == null) ? 0 : required.hashCode());
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
		ExpressRegion other = (ExpressRegion) obj;
		if (addition == null) {
			if (other.addition != null)
				return false;
		} else if (!addition.equals(other.addition))
			return false;
		if (express == null) {
			if (other.express != null)
				return false;
		} else if (!express.equals(other.express))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (partition == null) {
			if (other.partition != null)
				return false;
		} else if (!partition.equals(other.partition))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (required == null) {
			if (other.required != null)
				return false;
		} else if (!required.equals(other.required))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExpressRegion [id=" + id + ", region=" + region + ", express=" + express + ", partition=" + partition
				+ ", required=" + required + ", addition=" + addition + "]";
	}
}
