package com.finalwork.android.e_commerce.model.entity;
/**
 * ��Ʒ����ʵ����
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Comment implements java.io.Serializable {

	/*
	 * create table wm_comment
		(
		   id                   bigint not null,
		   shop                 bigint not null,
		   `order`              bigint not null,
		   product              bigint not null,
		   product_spec_item    bigint not null,
		   user                 bigint not null,
		   product_score        int not null,
		   service_score        int not null,
		   time_score           int not null,
		   content              varchar(255) not null,
		   reply                varchar(255) not null,
		   images               varchar(255) not null,
		   cmt_time             datetime not null,
		   reply_time           datetime not null,
		   primary key (id)
		);
	 */
	
	private Long id;
	
	private Shop shop;
	
	private Order order;
	
	private Product product;
	
	private SpecItem specItem;
	
	private User user;
	
	private Integer productScore;

	private Integer serviceScore;
	
	private Integer timeScore;
	
	private String content;
	
	private String reply;
	
	private String imageLocations;
	
	private java.util.Date commentTime;
	
	private java.util.Date replyTime;

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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getProductScore() {
		return productScore;
	}

	public void setProductScore(Integer productScore) {
		this.productScore = productScore;
	}

	public Integer getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(Integer serviceScore) {
		this.serviceScore = serviceScore;
	}

	public Integer getTimeScore() {
		return timeScore;
	}

	public void setTimeScore(Integer timeScore) {
		this.timeScore = timeScore;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getImageLocations() {
		return imageLocations;
	}

	public void setImageLocations(String imageLocations) {
		this.imageLocations = imageLocations;
	}

	public java.util.Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(java.util.Date commentTime) {
		this.commentTime = commentTime;
	}

	public java.util.Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(java.util.Date replyTime) {
		this.replyTime = replyTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentTime == null) ? 0 : commentTime.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageLocations == null) ? 0 : imageLocations.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((productScore == null) ? 0 : productScore.hashCode());
		result = prime * result + ((reply == null) ? 0 : reply.hashCode());
		result = prime * result + ((replyTime == null) ? 0 : replyTime.hashCode());
		result = prime * result + ((serviceScore == null) ? 0 : serviceScore.hashCode());
		result = prime * result + ((shop == null) ? 0 : shop.hashCode());
		result = prime * result + ((specItem == null) ? 0 : specItem.hashCode());
		result = prime * result + ((timeScore == null) ? 0 : timeScore.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Comment other = (Comment) obj;
		if (commentTime == null) {
			if (other.commentTime != null)
				return false;
		} else if (!commentTime.equals(other.commentTime))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imageLocations == null) {
			if (other.imageLocations != null)
				return false;
		} else if (!imageLocations.equals(other.imageLocations))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (productScore == null) {
			if (other.productScore != null)
				return false;
		} else if (!productScore.equals(other.productScore))
			return false;
		if (reply == null) {
			if (other.reply != null)
				return false;
		} else if (!reply.equals(other.reply))
			return false;
		if (replyTime == null) {
			if (other.replyTime != null)
				return false;
		} else if (!replyTime.equals(other.replyTime))
			return false;
		if (serviceScore == null) {
			if (other.serviceScore != null)
				return false;
		} else if (!serviceScore.equals(other.serviceScore))
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
		if (timeScore == null) {
			if (other.timeScore != null)
				return false;
		} else if (!timeScore.equals(other.timeScore))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", shop=" + shop + ", order=" + order + ", product=" + product + ", specItem="
				+ specItem + ", user=" + user + ", productScore=" + productScore + ", serviceScore=" + serviceScore
				+ ", timeScore=" + timeScore + ", content=" + content + ", reply=" + reply + ", imageLocations="
				+ imageLocations + ", commentTime=" + commentTime + ", replyTime=" + replyTime + "]";
	}
}
