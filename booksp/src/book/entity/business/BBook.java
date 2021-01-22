package book.entity.business;

import book.entity.Book;

public class BBook extends Book {

	private static final long serialVersionUID = 1L;
	private int bcount;
	public int getBcount() {
		return bcount;
	}
	public void setBcount(int bcount) {
		this.bcount = bcount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + bcount;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BBook other = (BBook) obj;
		if (bcount != other.bcount)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return super.toString()+"BBook [bcount=" + bcount + "]";
	}
}
