package book.entity;

import java.io.Serializable;

public class Item implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5319158562310494625L;
	private int iid;
	private int oid;
	private int bid;
	private String createDate;
	private int count;
	private String price;
	private String totalPrice;

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bid;
		result = prime * result + count;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + iid;
		result = prime * result + oid;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
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
		Item other = (Item) obj;
		if (bid != other.bid)
			return false;
		if (count != other.count)
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (iid != other.iid)
			return false;
		if (oid != other.oid)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [bid=" + bid + ", count=" + count + "]";
	}

	@Override
	public Item clone() throws CloneNotSupportedException {
		return (Item) super.clone();
	}
}
