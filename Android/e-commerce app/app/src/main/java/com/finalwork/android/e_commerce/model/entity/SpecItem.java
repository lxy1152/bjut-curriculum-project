package com.finalwork.android.e_commerce.model.entity;
/**
 * ��Ʒ���ʵ����.
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SpecItem implements java.io.Serializable {

	/*
	 * create table wm_spec_item
		(
		   id                   bigint not null,
		   shop                 bigint not null,
		   product              bigint not null,
		   itemName             varchar(255) not null,
		   itemDesc             varchar(255) not null,
		   itemPhoto            varchar(255) not null,
		   itemPrice            numeric(20,2) not null,
		   primary key (id)
		);
	 */
	
	private Long id;
	
	private Shop shop;
	
	private Product product;
	
	private String specItemName;
	
	private String specItemDescript;
	
	private String specItemPhotoLocation;
	
	private Double specItemPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSpecItemName() {
		return specItemName;
	}

	public void setSpecItemName(String specItemName) {
		this.specItemName = specItemName;
	}

	public String getSpecItemDescript() {
		return specItemDescript;
	}

	public void setSpecItemDescript(String specItemDescript) {
		this.specItemDescript = specItemDescript;
	}

	public String getSpecItemPhotoLocation() {
		return specItemPhotoLocation;
	}

	public void setSpecItemPhotoLocation(String specItemPhotoLocation) {
		this.specItemPhotoLocation = specItemPhotoLocation;
	}

	public Double getSpecItemPrice() {
		return specItemPrice;
	}

	public void setSpecItemPrice(Double specItemPrice) {
		this.specItemPrice = specItemPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((shop == null) ? 0 : shop.hashCode());
		result = prime * result + ((specItemDescript == null) ? 0 : specItemDescript.hashCode());
		result = prime * result + ((specItemName == null) ? 0 : specItemName.hashCode());
		result = prime * result + ((specItemPhotoLocation == null) ? 0 : specItemPhotoLocation.hashCode());
		result = prime * result + ((specItemPrice == null) ? 0 : specItemPrice.hashCode());
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
		SpecItem other = (SpecItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (shop == null) {
			if (other.shop != null)
				return false;
		} else if (!shop.equals(other.shop))
			return false;
		if (specItemDescript == null) {
			if (other.specItemDescript != null)
				return false;
		} else if (!specItemDescript.equals(other.specItemDescript))
			return false;
		if (specItemName == null) {
			if (other.specItemName != null)
				return false;
		} else if (!specItemName.equals(other.specItemName))
			return false;
		if (specItemPhotoLocation == null) {
			if (other.specItemPhotoLocation != null)
				return false;
		} else if (!specItemPhotoLocation.equals(other.specItemPhotoLocation))
			return false;
		if (specItemPrice == null) {
			if (other.specItemPrice != null)
				return false;
		} else if (!specItemPrice.equals(other.specItemPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpecItem [id=" + id + ", shop=" + shop + ", product=" + product + ", specItemName=" + specItemName
				+ ", specItemDescript=" + specItemDescript + ", specItemPhotoLocation=" + specItemPhotoLocation
				+ ", specItemPrice=" + specItemPrice + "]";
	}
}
