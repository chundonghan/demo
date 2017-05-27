package com.demo.dto;

public class PageDto {
	public final static int DEFAULT_PAGE_SIZE = 5;
	private int pagesize = 0;
	private int page = 1;
	private int offset = 0;

	public int getOffset() {
		offset = (this.page - 1) * this.pagesize;
		offset = offset > 0 ? offset : 0;
		return offset;
	}

	public int getPagesize() {
		return this.pagesize > 0 ? this.pagesize : 5;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
public static void main(String[] args) {
	PageDto pd = new PageDto();
	pd.setPagesize(5); 
	pd.setPage(2); 
	
	System.out.println(pd.getOffset());
}
}
