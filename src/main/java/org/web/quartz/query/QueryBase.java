package org.web.quartz.query;


/**
 * 类QueryBase.java的实现描述：所有查询DO的父类.一般封闭通用查询属性信息.
 * 
 * @author Administrator 2015年9月29日 下午5:24:41
 */
public class QueryBase {

	public static final Integer FIRST_PAGE = 1;
	public static final Integer ONE_PAGE_SIZE = 1;
	public static final Integer DEFAULT_PAGE_SIZE = 10;
	public static final Integer MAX_PAGE_SIZE = 100;
	
	private Integer page = null;

	private Integer pageSize = null;

	private String orderByClause = null;

	private Integer startNum = null;

	public Integer getStartNum() {
		if ((startNum == null)&&(page != null && pageSize != null) ) {
			if(page<0){
				page=0;
			}
			if(pageSize<0){
				pageSize=10;
			}
			return (page - 1) * pageSize;
		}
		return startNum;
	}

	@Deprecated
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public boolean checkPagingInfo(){
		if (getStartNum() != null && getPageSize() == null) {
			return true; 
		}
		return false;
	}

	public void buildPagineInfo() {
		if (this.getPage() == null) {
			this.setPage(FIRST_PAGE);
		}
		if (this.getPageSize() == null) {
			this.setPageSize(DEFAULT_PAGE_SIZE);
		}
		if (this.getPageSize() > MAX_PAGE_SIZE) {
			this.setPageSize(MAX_PAGE_SIZE);
		}
	}
	
	public void setFirstRecord() {
		this.setPage(FIRST_PAGE);
		this.setPageSize(ONE_PAGE_SIZE);
	}
	
	public void setOneRecord() {
		this.page = 1;
		this.pageSize = 1;
	}
}

