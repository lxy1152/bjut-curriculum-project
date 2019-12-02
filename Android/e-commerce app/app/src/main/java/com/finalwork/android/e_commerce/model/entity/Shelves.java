package com.finalwork.android.e_commerce.model.entity;
/**
 * �̵����ʵ����.
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Shelves implements java.io.Serializable {
	
	/*
	 * create table wm_shelves
		(
		   id                   bigint not null,
		   shop                 bigint not null,
		   product              bigint not null,
		   spec_item            bigint not null,
		   is_hot               int not null,
		   is_new               int not null,
		   is_recom             int not null,
		   sell_point           varchar(255) not null,
		   visited              bigint not null,
		   saled                bigint not null,
		   comment              bigint not null,
		   favoratable_rate     numeric(3,2) not null,
		   primary key (id)
		);
	 */
	
	private Long id;
	
	private Shop shop;
	
	private Product product;
	
	private SpecItem specItem;
	
	private Boolean hot;
	
	private Boolean newable;
	
	private Boolean recommend;
	
	private String sellPoint;
	
	private Long totalVisited;
	
	private Long totalSaled;
	
	private Long totalComment;
	
	private Double favoratableRate;

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

	public SpecItem getSpecItem() {
		return specItem;
	}

	public void setSpecItem(SpecItem specItem) {
		this.specItem = specItem;
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

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
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

	public Double getFavoratableRate() {
		return favoratableRate;
	}

	public void setFavoratableRate(Double favoratableRate) {
		this.favoratableRate = favoratableRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((favoratableRate == null) ? 0 : favoratableRate.hashCode());
		result = prime * result + ((hot == null) ? 0 : hot.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((newable == null) ? 0 : newable.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((recommend == null) ? 0 : recommend.hashCode());
		result = prime * result + ((sellPoint == null) ? 0 : sellPoint.hashCode());
		result = prime * result + ((shop == null) ? 0 : shop.hashCode());
		result = prime * result + ((specItem == null) ? 0 : specItem.hashCode());
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
		Shelves other = (Shelves) obj;
		if (favoratableRate == null) {
			if (other.favoratableRate != null)
				return false;
		} else if (!favoratableRate.equals(other.favoratableRate))
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
		if (newable == null) {
			if (other.newable != null)
				return false;
		} else if (!newable.equals(other.newable))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (recommend == null) {
			if (other.recommend != null)
				return false;
		} else if (!recommend.equals(other.recommend))
			return false;
		if (sellPoint == null) {
			if (other.sellPoint != null)
				return false;
		} else if (!sellPoint.equals(other.sellPoint))
			return false;
		if (shop == null) {
			if (other.shop != null)
				return false;
		} else if (!shop.equals(other.shop))
			return false;
		if (specItem == null) {
			if (other.specItem != null)
				return false;
		} else if (!specItem.equals(other.specItem))
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
		return "Shelves [id=" + id + ", shop=" + shop + ", product=" + product + ", specItem=" + specItem + ", hot="
				+ hot + ", newable=" + newable + ", recommend=" + recommend + ", sellPoint=" + sellPoint
				+ ", totalVisited=" + totalVisited + ", totalSaled=" + totalSaled + ", totalComment=" + totalComment
				+ ", favoratableRate=" + favoratableRate + "]";
	}
}
