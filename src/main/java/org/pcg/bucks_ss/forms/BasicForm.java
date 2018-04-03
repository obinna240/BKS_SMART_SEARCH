package org.pcg.bucks_ss.forms;

/**
 * BasicForm is used for the basic form header
 * @author obiPC
 *
 */
public class BasicForm 
{
	private String basicquery; //query
	private String basicqueryType; //query type
	
	private Boolean basicsortBy; //sort by date or relevance
	private Integer pValue; //tentative to be used for pagination
	
	
	/**
	 * 
	 * @return
	 */
	public String getBasicquery() {
		return basicquery;
	}
	public void setBasicquery(String basicquery) {
		this.basicquery = basicquery;
	}
	public String getBasicqueryType() {
		return basicqueryType;
	}
	public void setBasicqueryType(String basicqueryType) {
		this.basicqueryType = basicqueryType;
	}
	public Boolean getBasicsortBy() {
		return basicsortBy;
	}
	public void setBasicsortBy(Boolean basicsortBy) {
		this.basicsortBy = basicsortBy;
	}
	
	/**
	 * @return the pValue
	 */
	public Integer getpValue() {
		return pValue;
	}
	/**
	 * @param pValue the pValue to set
	 */
	public void setpValue(Integer pValue) {
		this.pValue = pValue;
	}
	
	
	
	
	
}
