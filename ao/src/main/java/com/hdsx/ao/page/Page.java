package com.hdsx.ao.page;


/**
 * 分页工具类
 * @author wusq
 *
 */
public class Page {

	/**
	 * 当前页数
	 */
	private int currentPage = 1;
	
	/**
	 * 每页显示数目
	 */
	private int pageSize = 10;
	
	/**
	 * 总页数
	 */
	private int totalPages;
	
	/**
	 * 总记录数
	 */
	private int totalRows;
	
	/**
	 * 当前页在数据库中的起始行数
	 */
	private int startRow = 1;
	
	
	public Page(){
		
	}
	
	public Page(int currentPage, int pageSize){
		this.currentPage = currentPage > 0 ? currentPage:1;
		this.pageSize = pageSize > 0 ? pageSize:this.pageSize;
		this.startRow = this.currentPage > 1 ? ((this.currentPage-1)*this.pageSize+1):1;
	}
	
	public int getStartRow() {
		this.startRow = this.currentPage > 1 ? ((this.currentPage-1)*this.pageSize+1):1;
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

}
