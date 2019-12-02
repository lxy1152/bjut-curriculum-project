package com.finalwork.android.e_commerce.model.entity;

import java.util.Date;

/**
 * ��Ʒʵ����
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Product implements java.io.Serializable {

	/*
	 * create table wm_product
		(
		   id                   bigint not null,
		   productNo            varchar(255) not null,
		   productName          varchar(255) not null,
		   productImage         varchar(255) not null,
		   marketPrice          numeric(20,2) not null,
		   discountPrice        numeric(20,2) not null,
		   stock                bigint not null,
		   is_sale              int not null,
		   is_hot               int not null,
		   is_new               int not null,
		   is_recom             int not null,
		   catagory             bigint not null,
		   brand                bigint not null,
		   visited              bigint not null,
		   saled                bigint not null,
		   appraise             bigint not null,
		   sale_time            datetime not null,
		   primary key (id)
		);
	 */
	
	private Long id;
	
	private String productNumber;
	
	private String productName;
	
	private String productImageLocation;
	
	private Double marketPrice;
	
	private Double discountPrice;
	
	private Long stock;
	
	private Boolean saled;
	
	private Boolean hot;
	
	private Boolean newable;
	
	private Boolean recommend;
	
	private Catagory catagory;
	
	private Brand brand;
	
	private Long totalVisited;
	
	private Long totalSaled;
	
	private Long totalComment;
	
	private Date saleTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImageLocation() {
		return productImageLocation;
	}

	public void setProductImageLocation(String productImageLocation) {
		this.productImageLocation = productImageLocation;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Boolean getSaled() {
		return saled;
	}

	public void setSaled(Boolean saled) {
		this.saled = saled;
	}

	public Boolean getHot() {
		return hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	public Boolean getNewable() {
		return newable;
	}

	public void setNewable(Boolean newable) {
		this.newable = newable;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	public Catagory getCatagory() {
		return catagory;
	}

	public void setCatagory(Catagory catagory) {
		this.catagory = catagory;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Long getTotalVisited() {
		return totalVisited;
	}

	public void setTotalVisited(Long totalVisited) {
		this.totalVisited = totalVisited;
	}

	public Long getTotalSaled() {
		return totalSaled;
	}

	public void setTotalSaled(Long totalSaled) {
		this.totalSaled = totalSaled;
	}

	public Long getTotalComment() {
		return totalComment;
	}

	public void setTotalComment(Long totalComment) {
		this.totalComment = totalComment;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((catagory == null) ? 0 : catagory.hashCode());
		result = prime * result + ((discountPrice == null) ? 0 : discountPrice.hashCode());
		result = prime * result + ((hot == null) ? 0 : hot.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marketPrice == null) ? 0 : marketPrice.hashCode());
		result = prime * result + ((newable == null) ? 0 : newable.hashCode());
		result = prime * result + ((productImageLocation == null) ? 0 : productImageLocation.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productNumber == null) ? 0 : productNumber.hashCode());
		result = prime * result + ((recommend == null) ? 0 : recommend.hashCode());
		result = prime * result + ((saleTime == null) ? 0 : saleTime.hashCode());
		result = prime * result + ((saled == null) ? 0 : saled.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + ((totalComment == null) ? 0 : totalComment.hashCode());
		result = prime * result + ((totalSaled == null) ? 0 : totalSaled.hashCode());
		result = prime * result + ((totalVisited == null) ? 0 : totalVisited.hashCode());
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
		Product other = (Product) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (catagory == null) {
			if (other.catagory != null)
				return false;
		} else if (!catagory.equals(other.catagory))
			return false;
		if (discountPrice == null) {
			if (other.discountPrice != null)
				return false;
		} else if (!discountPrice.equals(other.discountPrice))
			return false;
		if (hot == null) {
			if (other.hot != null)
				return false;
		} else if (!hot.equals(other.hot))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marketPrice == null) {
			if (other.marketPrice != null)
				return false;
		} else if (!marketPrice.equals(other.marketPrice))
			return false;
		if (newable == null) {
			if (other.newable != null)
				return false;
		} else if (!newable.equals(other.newable))
			return false;
		if (productImageLocation == null) {
			if (other.productImageLocation != null)
				return false;
		} else if (!productImageLocation.equals(other.productImageLocation))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productNumber == null) {
			if (other.productNumber != null)
				return false;
		} else if (!productNumber.equals(other.productNumber))
			return false;
		if (recommend == null) {
			if (other.recommend != null)
				return false;
		} else if (!recommend.equals(other.recommend))
			return false;
		if (saleTime == null) {
			if (other.saleTime != null)
				return false;
		} else if (!saleTime.equals(other.saleTime))
			return false;
		if (saled == null) {
			if (other.saled != null)
				return false;
		} else if (!saled.equals(other.saled))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (totalComment == null) {
			if (other.totalComment != null)
				return false;
		} else if (!totalComment.equals(other.totalComment))
			return false;
		if (totalSaled == null) {
			if (other.totalSaled != null)
				return false;
		} else if (!totalSaled.equals(other.totalSaled))
			return false;
		if (totalVisited == null) {
			if (other.totalVisited != null)
				return false;
		} else if (!totalVisited.equals(other.totalVisited))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productNumber=" + productNumber + ", productName=" + productName
				+ ", productImageLocation=" + productImageLocation + ", marketPrice=" + marketPrice + ", discountPrice="
				+ discountPrice + ", stock=" + stock + ", saled=" + saled + ", hot=" + hot + ", newable=" + newable
				+ ", recommend=" + recommend + ", catagory=" + catagory + ", brand=" + brand + ", totalVisited="
				+ totalVisited + ", totalSaled=" + totalSaled + ", totalComment=" + totalComment + ", saleTime="
				+ saleTime + "]";
	}
}
