package com.finalwork.android.e_commerce.model.entity;
/**
 * ����ʵ����
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Order implements java.io.Serializable {

	/*
	 * create table wm_order
		(
		   id                   bigint not null,
		   orderNo              varchar(255) not null,
		   shop                 bigint not null,
		   user                 bigint not null,
		   status               bigint not null,
		   total_money          numeric(20,2) not null,
		   delivery_type        bigint not null,
		   delivery_money       numeric(5,2) not null,
		   real_total_money     numeric(20,2) not null,
		   discount_total_money numeric(10,2) not null,
		   pay_type             bigint not null,
		   pay_from             bigint not null,
		   is_paid              int not null,
		   order_attain_score   int not null,
		   order_assume_score   int not null,
		   is_invoice           int not null,
		   invoice_client       varchar(255) not null,
		   remark               varchar(255) not null,
		   order_source         bigint not null,
		   need_pay             numeric(20,2) not null,
		   is_refund            int not null,
		   is_comment           int not null,
		   cancel_reason        varchar(255) not null,
		   reject_reason        varchar(255) not null,
		   is_close             int not null,
		   receive_time         datetime not null,
		   delivery_time        datetime not null,
		   express              bigint not null,
		   create_time          datetime not null,
		   commissionFee        numeric(20,2) not null,
		   address              bigint not null,
		   primary key (id)
		);
	 */

	private Long id;
	
	private String orderNumber;
	
	private Shop shop;
	
	private User user;
	
	private BasicDic orderStatus;
	
	private Double totalMoney;
	
	private BasicDic deliverType;
	
	private Double deliveryMoney;
	
	private Double realTotalMoney;
	
	private Double discountTotalMoney;
	
	private BasicDic payType;
	
	private BasicDic payFrom;
	
	private Boolean paid;
	
	private Integer orderAttainScore;
	
	private Integer orderAssumeScore;
	
	private Boolean invoice;
	
	private String invoiceClient;
	
	private String remark;
	
	private BasicDic orderSource;
	
	private Double needPay;
	
	private Boolean refund;
	
	private Boolean comment;
	
	private String cancelReason;
	
	private String rejectReason;
	
	private Boolean close;
	
	private java.util.Date receiveTime;
	
	private java.util.Date deliveryTime;
	
	private java.util.Date createTime;
	
	private Double commissionFee;
	
	private Express express;
	
	private Address address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BasicDic getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(BasicDic orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BasicDic getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(BasicDic deliverType) {
		this.deliverType = deliverType;
	}

	public Double getDeliveryMoney() {
		return deliveryMoney;
	}

	public void setDeliveryMoney(Double deliveryMoney) {
		this.deliveryMoney = deliveryMoney;
	}

	public Double getRealTotalMoney() {
		return realTotalMoney;
	}

	public void setRealTotalMoney(Double realTotalMoney) {
		this.realTotalMoney = realTotalMoney;
	}

	public Double getDiscountTotalMoney() {
		return discountTotalMoney;
	}

	public void setDiscountTotalMoney(Double discountTotalMoney) {
		this.discountTotalMoney = discountTotalMoney;
	}

	public BasicDic getPayType() {
		return payType;
	}

	public void setPayType(BasicDic payType) {
		this.payType = payType;
	}

	public BasicDic getPayFrom() {
		return payFrom;
	}

	public void setPayFrom(BasicDic payFrom) {
		this.payFrom = payFrom;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Integer getOrderAttainScore() {
		return orderAttainScore;
	}

	public void setOrderAttainScore(Integer orderAttainScore) {
		this.orderAttainScore = orderAttainScore;
	}

	public Integer getOrderAssumeScore() {
		return orderAssumeScore;
	}

	public void setOrderAssumeScore(Integer orderAssumeScore) {
		this.orderAssumeScore = orderAssumeScore;
	}

	public Boolean getInvoice() {
		return invoice;
	}

	public void setInvoice(Boolean invoice) {
		this.invoice = invoice;
	}

	public String getInvoiceClient() {
		return invoiceClient;
	}

	public void setInvoiceClient(String invoiceClient) {
		this.invoiceClient = invoiceClient;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BasicDic getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(BasicDic orderSource) {
		this.orderSource = orderSource;
	}

	public Double getNeedPay() {
		return needPay;
	}

	public void setNeedPay(Double needPay) {
		this.needPay = needPay;
	}

	public Boolean getRefund() {
		return refund;
	}

	public void setRefund(Boolean refund) {
		this.refund = refund;
	}

	public Boolean getComment() {
		return comment;
	}

	public void setComment(Boolean comment) {
		this.comment = comment;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Boolean getClose() {
		return close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

	public java.util.Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(java.util.Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public java.util.Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(java.util.Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public Double getCommissionFee() {
		return commissionFee;
	}

	public void setCommissionFee(Double commissionFee) {
		this.commissionFee = commissionFee;
	}

	public Express getExpress() {
		return express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cancelReason == null) ? 0 : cancelReason.hashCode());
		result = prime * result + ((close == null) ? 0 : close.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((commissionFee == null) ? 0 : commissionFee.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((deliverType == null) ? 0 : deliverType.hashCode());
		result = prime * result + ((deliveryMoney == null) ? 0 : deliveryMoney.hashCode());
		result = prime * result + ((deliveryTime == null) ? 0 : deliveryTime.hashCode());
		result = prime * result + ((discountTotalMoney == null) ? 0 : discountTotalMoney.hashCode());
		result = prime * result + ((express == null) ? 0 : express.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		result = prime * result + ((invoiceClient == null) ? 0 : invoiceClient.hashCode());
		result = prime * result + ((needPay == null) ? 0 : needPay.hashCode());
		result = prime * result + ((orderAssumeScore == null) ? 0 : orderAssumeScore.hashCode());
		result = prime * result + ((orderAttainScore == null) ? 0 : orderAttainScore.hashCode());
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		result = prime * result + ((orderSource == null) ? 0 : orderSource.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((paid == null) ? 0 : paid.hashCode());
		result = prime * result + ((payFrom == null) ? 0 : payFrom.hashCode());
		result = prime * result + ((payType == null) ? 0 : payType.hashCode());
		result = prime * result + ((realTotalMoney == null) ? 0 : realTotalMoney.hashCode());
		result = prime * result + ((receiveTime == null) ? 0 : receiveTime.hashCode());
		result = prime * result + ((refund == null) ? 0 : refund.hashCode());
		result = prime * result + ((rejectReason == null) ? 0 : rejectReason.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((shop == null) ? 0 : shop.hashCode());
		result = prime * result + ((totalMoney == null) ? 0 : totalMoney.hashCode());
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
		Order other = (Order) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cancelReason == null) {
			if (other.cancelReason != null)
				return false;
		} else if (!cancelReason.equals(other.cancelReason))
			return false;
		if (close == null) {
			if (other.close != null)
				return false;
		} else if (!close.equals(other.close))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (commissionFee == null) {
			if (other.commissionFee != null)
				return false;
		} else if (!commissionFee.equals(other.commissionFee))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (deliverType == null) {
			if (other.deliverType != null)
				return false;
		} else if (!deliverType.equals(other.deliverType))
			return false;
		if (deliveryMoney == null) {
			if (other.deliveryMoney != null)
				return false;
		} else if (!deliveryMoney.equals(other.deliveryMoney))
			return false;
		if (deliveryTime == null) {
			if (other.deliveryTime != null)
				return false;
		} else if (!deliveryTime.equals(other.deliveryTime))
			return false;
		if (discountTotalMoney == null) {
			if (other.discountTotalMoney != null)
				return false;
		} else if (!discountTotalMoney.equals(other.discountTotalMoney))
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
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		if (invoiceClient == null) {
			if (other.invoiceClient != null)
				return false;
		} else if (!invoiceClient.equals(other.invoiceClient))
			return false;
		if (needPay == null) {
			if (other.needPay != null)
				return false;
		} else if (!needPay.equals(other.needPay))
			return false;
		if (orderAssumeScore == null) {
			if (other.orderAssumeScore != null)
				return false;
		} else if (!orderAssumeScore.equals(other.orderAssumeScore))
			return false;
		if (orderAttainScore == null) {
			if (other.orderAttainScore != null)
				return false;
		} else if (!orderAttainScore.equals(other.orderAttainScore))
			return false;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		if (orderSource == null) {
			if (other.orderSource != null)
				return false;
		} else if (!orderSource.equals(other.orderSource))
			return false;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		if (paid == null) {
			if (other.paid != null)
				return false;
		} else if (!paid.equals(other.paid))
			return false;
		if (payFrom == null) {
			if (other.payFrom != null)
				return false;
		} else if (!payFrom.equals(other.payFrom))
			return false;
		if (payType == null) {
			if (other.payType != null)
				return false;
		} else if (!payType.equals(other.payType))
			return false;
		if (realTotalMoney == null) {
			if (other.realTotalMoney != null)
				return false;
		} else if (!realTotalMoney.equals(other.realTotalMoney))
			return false;
		if (receiveTime == null) {
			if (other.receiveTime != null)
				return false;
		} else if (!receiveTime.equals(other.receiveTime))
			return false;
		if (refund == null) {
			if (other.refund != null)
				return false;
		} else if (!refund.equals(other.refund))
			return false;
		if (rejectReason == null) {
			if (other.rejectReason != null)
				return false;
		} else if (!rejectReason.equals(other.rejectReason))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (shop == null) {
			if (other.shop != null)
				return false;
		} else if (!shop.equals(other.shop))
			return false;
		if (totalMoney == null) {
			if (other.totalMoney != null)
				return false;
		} else if (!totalMoney.equals(other.totalMoney))
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
		return "Order [id=" + id + ", orderNumber=" + orderNumber + ", shop=" + shop + ", user=" + user
				+ ", orderStatus=" + orderStatus + ", totalMoney=" + totalMoney + ", deliverType=" + deliverType
				+ ", deliveryMoney=" + deliveryMoney + ", realTotalMoney=" + realTotalMoney + ", discountTotalMoney="
				+ discountTotalMoney + ", payType=" + payType + ", payFrom=" + payFrom + ", paid=" + paid
				+ ", orderAttainScore=" + orderAttainScore + ", orderAssumeScore=" + orderAssumeScore + ", invoice="
				+ invoice + ", invoiceClient=" + invoiceClient + ", remark=" + remark + ", orderSource=" + orderSource
				+ ", needPay=" + needPay + ", refund=" + refund + ", comment=" + comment + ", cancelReason="
				+ cancelReason + ", rejectReason=" + rejectReason + ", close=" + close + ", receiveTime=" + receiveTime
				+ ", deliveryTime=" + deliveryTime + ", createTime=" + createTime + ", commissionFee=" + commissionFee
				+ ", express=" + express + ", address=" + address + "]";
	}
}
