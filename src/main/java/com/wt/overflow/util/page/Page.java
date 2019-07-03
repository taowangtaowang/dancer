package com.wt.overflow.util.page;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4076251710795431210L;
	
	/**
	 * 获取查询数来的总数目,默认10
	 */
	private Integer showCount = 10;//获取查询数来的总数目
    /**
     * 总的页数
     */
    private int totalPage;// 总的页数
    /**
     * 总的数目
     */
    private int totalResult;// 总的数目
    /**
     * 当前页 
     */
    private int currentPage=1;// 当前页 
    /**
     * 当前记录数 
     */
    private int currentResult;// 当前记录数 
    /**
     * 结果集列表
     */
    private List list;
    /**
     * listJson字符串
     */
    private String listJson;	//listJson字符串
    
    //private int page=1;

    public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	private String sortField;
    private String order;
    
    //private Map<String,Object> params= new HashMap<String,Object>();//其他的参数我们把它分装成一个Map对象  
   /* public PageInfo(){
    	this(1);
	}
    public PageInfo(int currentPage){
    	//默认每页显示10条记录
    	this(currentPage, 10);
    }*/
    
	public int getShowCount() {
		return showCount;
	}
	public void setShowCount(Integer showCount) {
		if(showCount!=null){
			this.showCount = showCount;
		}
	}
	public int getTotalPage() {
		totalPage = (totalResult+showCount-1)/showCount;
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage!=null){
			if(currentPage<=0){
				this.currentPage = 1;
			}else{
				this.currentPage = currentPage;
			}
		}
	}
	public int getCurrentResult() {
		return (currentPage-1)*showCount;
	}
	public void setCurrentResult(Integer currentResult) {
		this.currentResult = currentResult;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	/*public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}*/
	/*public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}*/
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Page [currentPage=")
				.append(currentPage).append(", showCount=")
				.append(showCount).append(",currentResult=")
				.append(currentResult).append(", totalPage=")
				.append(totalPage).append(", totalResult=")
				.append(totalResult).append("]");
		return builder.toString();
	}
	public String getListJson() {
		return listJson;
	}
	public void setListJson(String listJson) {
		this.listJson = listJson;
	}
	
}
