package com.finalwork.android.e_commerce.model.entity;

@SuppressWarnings("serial")
public class Trolley implements java.io.Serializable {

	private Long id;
	
	private User user;
	
	private SpecItem specItem;
	
	private Integer count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SpecItem getSpecItem() {
		return specItem;
	}

	public void setSpecItem(SpecItem specItem) {
		this.specItem = specItem;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((specItem == null) ? 0 : specItem.hashCode());
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
		Trolley other = (Trolley) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (specItem == null) {
			if (other.specItem != null)
				return false;
		} else if (!specItem.equals(other.specItem))
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
		return "Trolley [id=" + id + ", user=" + user + ", specItem=" + specItem + ", count=" + count + "]";
	}
}
