package book.util;

public class PageSupport {
	private int recordCount = 1; // 记录总数
	private int pageSize = 0;    // 每页显示记录数
	private int curPageNo = 1;   // 当前页码

	private int pageCount;       // 总页数 （便于el和jstl取值）

	/**
	 * 参数分别为：总记录数、每页条数、当前页码
	 * */
	public PageSupport(int recordCount, int pageSize, int curPageNo) {
		this.pageSize = pageSize;
		if(recordCount>0) {
			this.recordCount = recordCount;
		}else {
			this.recordCount = 1;//默认最低有一条记录
		}
		getTotalPageCount();
		if (curPageNo < 1) {
			this.curPageNo = 1;
		} else if (curPageNo > this.pageCount) {
			this.curPageNo = this.pageCount;
		} else {
			this.curPageNo = curPageNo;
		}

	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		if (recordCount > 0) {
			this.recordCount = recordCount;
		}

	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0)
			this.pageSize = pageSize;
	}

	// get当前页码
	public int getCurPageNo() {
		return this.curPageNo;
	}

	// set当前页码
	public void setCurPageNo(int currPageNo) {
		if (this.curPageNo > 0)
			this.curPageNo = currPageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	// 获得总页数
	private void getTotalPageCount() {
		if (this.recordCount % this.pageSize == 0) {
			this.pageCount = this.recordCount / this.pageSize;
		} else if (this.recordCount % this.pageSize > 0) {
			this.pageCount = this.recordCount / this.pageSize + 1;
		} else {
			this.pageCount = 1;
		}
	}

	// 根据当前页数和每页记录数获取当前页的起始行
	public int getStartRow() {
		return (curPageNo - 1) * pageSize + 1;
	}

	// 根据当前页数和每页记录数获取当前页的终止行
	public int getEndRow() {
		return curPageNo * pageSize;
	}

	// Mysql分页查询的起始行
	public int getStartRowForMysql() {
		return (curPageNo - 1) * pageSize;
	}

}
