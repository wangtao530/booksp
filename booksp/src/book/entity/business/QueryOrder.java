package book.entity.business;

public class QueryOrder implements Cloneable {
	private int iid;
	private String username;
	private String createdate;
	private double totalprice;
	private String image;
	private String name;//图书名称
	private double price;
	private int count;
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public QueryOrder clone() throws CloneNotSupportedException {
		return (QueryOrder) super.clone();
	}
	@Override
	public String toString() {
		return "QueryOrder [iid=" + iid + ", username=" + username + ", createdate=" + createdate + ", totalprice="
				+ totalprice + ", image=" + image + ", name=" + name + ", price=" + price + ", count=" + count + "]";
	}
}
