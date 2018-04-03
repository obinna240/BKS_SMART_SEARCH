package org.pcg.bucks_ss.forms;

public class AdvancedForm 
{
	private String a_basicquery; //query
	private String a_basicqueryType; //query type
	private Boolean a_basicsortBy; //sort by date or relevance
	
	private String Author;
	private String dateFrom;
	private String dateTo;
	private String owners;
	private String categories;
	private Integer pValue; //tentative to be used for pagination
	
	public String getA_basicquery() {
		return a_basicquery;
	}
	public void setA_basicquery(String a_basicquery) {
		this.a_basicquery = a_basicquery;
	}
	public String getA_basicqueryType() {
		return a_basicqueryType;
	}
	public void setA_basicqueryType(String a_basicqueryType) {
		this.a_basicqueryType = a_basicqueryType;
	}
	public Boolean getA_basicsortBy() {
		return a_basicsortBy;
	}
	public void setA_basicsortBy(Boolean a_basicsortBy) {
		this.a_basicsortBy = a_basicsortBy;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getOwners() {
		return owners;
	}
	public void setOwners(String owners) {
		this.owners = owners;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public Integer getpValue() {
		return pValue;
	}
	public void setpValue(Integer pValue) {
		this.pValue = pValue;
	}
	
	
	
}
