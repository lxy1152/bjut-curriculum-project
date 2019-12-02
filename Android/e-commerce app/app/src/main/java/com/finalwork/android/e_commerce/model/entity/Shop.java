package com.finalwork.android.e_commerce.model.entity;
/**
 * ����ʵ����
 * @author ZkyMs
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Shop implements java.io.Serializable {

	/*
	 * create table wm_shop
		(
		   id                   bigint not null,
		   shopNo               varchar(255) not null,
		   owner                bigint not null,
		   address              bigint not null,
		   is_self              int not null,
		   name                 varchar(255) not null,
		   brand                bigint not null,
		   photo                varchar(255) not null,
		   contactNo            varchar(255) not null,
		   bankcardNo           varchar(255) not null,
		   is_invoice           varchar(255) not null,
		   freight              numeric(5,2) not null,
		   status               bigint not null,
		   `join`               datetime not null,
		   money                numeric(30,2) not null,
		   total_focused        bigint not null,
		   total_products       bigint not null,
		   total_order          bigint not null,
		   settled_order        bigint not null,
		   unsettled_order      bigint not null,
		   rate                 numeric(3,2) not null,
		   total_requirement    numeric(20,2) not null,
		   primary key (id)
		);
	 */
	
	private Long id;
	
	private String shopNumber;
	
	private User owner;
	
	private Address address;
	
	private Boolean self;
	
	private String shopName;
	
	private Brand brand;
	
	private String photoLocation;
	
	private String contactNumber;
	
	private String bandcardNumber;
	
	private Boolean invoice;
	
	private Double freight;
	
	private BasicDic shopStatus;
	
	private java.util.Date joinTime;
	
	private Double currentMoney;
	
	private Long totalFocused;
	
	private Long totalProducts;
	
	private Long totalOrders;
	
	private Long settledOrders;
	
	private Long unsettledOrders;
	
	private Double raxRate;
	
	private Double totalRequirements;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShopNumber() {
		return shopNumber;
	}

	public void setShopNumber(String shopNumber) {
		this.shopNumber = shopNumber;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getSelf() {
		return self;
	}

	public void setSelf(Boolean self) {
		this.self = self;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getPhotoLocation() {
		return photoLocation;
	}

	public void setPhotoLocation(String photoLocation) {
		this.photoLocation = photoLocation;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getBandcardNumber() {
		return bandcardNumber;
	}

	public void setBandcardNumber(String bandcardNumber) {
		this.bandcardNumber = bandcardNumber;
	}

	public Boolean getInvoice() {
		return invoice;
	}

	public void setInvoice(Boolean invoice) {
		this.invoice = invoice;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public BasicDic getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(BasicDic shopStatus) {
		this.shopStatus = shopStatus;
	}

	public java.util.Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(java.util.Date joinTime) {
		this.joinTime = joinTime;
	}

	public Double getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(Double currentMoney) {
		this.currentMoney = currentMoney;
	}

	public Long getTotalFocused() {
		return totalFocused;
	}

	public void setTotalFocused(Long totalFocused) {
		this.totalFocused = totalFocused;
	}

	public Long getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(Long totalProducts) {
		this.totalProducts = totalProducts;
	}

	public Long getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(Long totalOrders) {
		this.totalOrders = totalOrders;
	}

	public Long getSettledOrders() {
		return settledOrders;
	}

	public void setSettledOrders(Long settledOrders) {
		this.settledOrders = settledOrders;
	}

	public Long getUnsettledOrders() {
		return unsettledOrders;
	}

	public void setUnsettledOrders(Long unsettledOrders) {
		this.unsettledOrders = unsettledOrders;
	}

	public Double getRaxRate() {
		return raxRate;
	}

	public void setRaxRate(Double raxRate) {
		this.raxRate = raxRate;
	}

	public Double getTotalRequirements() {
		return totalRequirements;
	}

	public void setTotalRequirements(Double totalRequirements) {
		this.totalRequirements = totalRequirements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bandcardNumber == null) ? 0 : bandcardNumber.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((currentMoney == null) ? 0 : currentMoney.hashCode());
		result = prime * result + ((freight == null) ? 0 : freight.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		result = prime * result + ((joinTime == null) ? 0 : joinTime.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((photoLocation == null) ? 0 : photoLocation.hashCode());
		result = prime * result + ((raxRate == null) ? 0 : raxRate.hashCode());
		result = prime * result + ((self == null) ? 0 : self.hashCode());
		result = prime * result + ((settledOrders == null) ? 0 : settledOrders.hashCode());
		result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
		result = prime * result + ((shopNumber == null) ? 0 : shopNumber.hashCode());
		result = prime * result + ((shopStatus == null) ? 0 : shopStatus.hashCode());
		result = prime * result + ((totalFocused == null) ? 0 : totalFocused.hashCode());
		result = prime * result + ((totalOrders == null) ? 0 : totalOrders.hashCode());
		result = prime * result + ((totalProducts == null) ? 0 : totalProducts.hashCode());
		result = prime * result + ((totalRequirements == null) ? 0 : totalRequirements.hashCode());
		result = prime * result + ((unsettledOrders == null) ? 0 : unsettledOrders.hashCode());
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
		Shop other = (Shop) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bandcardNumber == null) {
			if (other.bandcardNumber != null)
				return false;
		} else if (!bandcardNumber.equals(other.bandcardNumber))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (currentMoney == null) {
			if (other.currentMoney != null)
				return false;
		} else if (!currentMoney.equals(other.currentMoney))
			return false;
		if (freight == null) {
			if (other.freight != null)
				return false;
		} else if (!freight.equals(other.freight))
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
		if (joinTime == null) {
			if (other.joinTime != null)
				return false;
		} else if (!joinTime.equals(other.joinTime))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (photoLocation == null) {
			if (other.photoLocation != null)
				return false;
		} else if (!photoLocation.equals(other.photoLocation))
			return false;
		if (raxRate == null) {
			if (other.raxRate != null)
				return false;
		} else if (!raxRate.equals(other.raxRate))
			return false;
		if (self == null) {
			if (other.self != null)
				return false;
		} else if (!self.equals(other.self))
			return false;
		if (settledOrders == null) {
			if (other.settledOrders != null)
				return false;
		} else if (!settledOrders.equals(other.settledOrders))
			return false;
		if (shopName == null) {
			if (other.shopName != null)
				return false;
		} else if (!shopName.equals(other.shopName))
			return false;
		if (shopNumber == null) {
			if (other.shopNumber != null)
				return false;
		} else if (!shopNumber.equals(other.shopNumber))
			return false;
		if (shopStatus == null) {
			if (other.shopStatus != null)
				return false;
		} else if (!shopStatus.equals(other.shopStatus))
			return false;
		if (totalFocused == null) {
			if (other.totalFocused != null)
				return false;
		} else if (!totalFocused.equals(other.totalFocused))
			return false;
		if (totalOrders == null) {
			if (other.totalOrders != null)
				return false;
		} else if (!totalOrders.equals(other.totalOrders))
			return false;
		if (totalProducts == null) {
			if (other.totalProducts != null)
				return false;
		} else if (!totalProducts.equals(other.totalProducts))
			return false;
		if (totalRequirements == null) {
			if (other.totalRequirements != null)
				return false;
		} else if (!totalRequirements.equals(other.totalRequirements))
			return false;
		if (unsettledOrders == null) {
			if (other.unsettledOrders != null)
				return false;
		} else if (!unsettledOrders.equals(other.unsettledOrders))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", shopNumber=" + shopNumber + ", owner=" + owner + ", address=" + address + ", self="
				+ self + ", shopName=" + shopName + ", brand=" + brand + ", photoLocation=" + photoLocation
				+ ", contactNumber=" + contactNumber + ", bandcardNumber=" + bandcardNumber + ", invoice=" + invoice
				+ ", freight=" + freight + ", shopStatus=" + shopStatus + ", joinTime=" + joinTime + ", currentMoney="
				+ currentMoney + ", totalFocused=" + totalFocused + ", totalProducts=" + totalProducts
				+ ", totalOrders=" + totalOrders + ", settledOrders=" + settledOrders + ", unsettledOrders="
				+ unsettledOrders + ", raxRate=" + raxRate + ", totalRequirements=" + totalRequirements + "]";
	}
}
