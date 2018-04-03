package org.pcg.bucks_ss.beans;

import java.util.List;
import java.util.Map;

public class SearchResultBean 
{
	private Integer numFound;
	private PaginatorBean paginatorBean;
	private Long alternativeResults;
	
	private Map<String, Long> docGroup;
	private Map<String, Long> authors;
	private Map<String, Long> asscUrls;
	
	private Map<String, String> tempUrlsString;
	
	private Map<String, Long> owner;
	private Map<String, Long> docType;
	
	private List<ResultBean> listOfResultBean;
	private List<ResultBean> altlistOfResultBean; //applies only to smart suggest results
	
	private boolean containsResult;
	
	private String gTheCareAct;
	private String gGuidanceNote;
	private String gFactSheet;
	private String gPolicy;
	
	private String gConferences;
	private String gNewsletters;
	private String gPresentations;
	private String gStrategy;
	private String gForm;
	private String gOthers;
	
	private List<ImageObjects> listOfImageObjects;
	
	public List<ImageObjects> getListOfImageObjects() {
		return listOfImageObjects;
	}
	public void setListOfImageObjects(List<ImageObjects> listOfImageObjects) {
		this.listOfImageObjects = listOfImageObjects;
	}
	public String getgConferences() {
		return gConferences;
	}
	public void setgConferences(String gConferences) {
		this.gConferences = gConferences;
	}
	public String getgNewsletters() {
		return gNewsletters;
	}
	public void setgNewsletters(String gNewsletters) {
		this.gNewsletters = gNewsletters;
	}
	public String getgPresentations() {
		return gPresentations;
	}
	public void setgPresentations(String gPresentations) {
		this.gPresentations = gPresentations;
	}
	public String getgStrategy() {
		return gStrategy;
	}
	public void setgStrategy(String gStrategy) {
		this.gStrategy = gStrategy;
	}
	public String getgForm() {
		return gForm;
	}
	public void setgForm(String gForm) {
		this.gForm = gForm;
	}
	public String getgOthers() {
		return gOthers;
	}
	public void setgOthers(String gOthers) {
		this.gOthers = gOthers;
	}

	
	public Integer getNumFound() {
		return numFound;
	}
	public void setNumFound(Integer numFound) {
		this.numFound = numFound;
	}
	public PaginatorBean getPaginatorBean() {
		return paginatorBean;
	}
	public void setPaginatorBean(PaginatorBean paginatorBean) {
		this.paginatorBean = paginatorBean;
	}
	public Long getAlternativeResults() {
		return alternativeResults;
	}
	public void setAlternativeResults(Long alternativeResults) {
		this.alternativeResults = alternativeResults;
	}
	public Map<String, Long> getDocGroup() {
		return docGroup;
	}
	public void setDocGroup(Map<String, Long> docGroup) {
		this.docGroup = docGroup;
	}
	public Map<String, Long> getAuthors() {
		return authors;
	}
	public void setAuthors(Map<String, Long> authors) {
		this.authors = authors;
	}
	public Map<String, Long> getAsscUrls() {
		return asscUrls;
	}
	public void setAsscUrls(Map<String, Long> asscUrls) {
		this.asscUrls = asscUrls;
	}
	public Map<String, Long> getOwner() {
		return owner;
	}
	public void setOwner(Map<String, Long> owner) {
		this.owner = owner;
	}
	public Map<String, Long> getDocType() {
		return docType;
	}
	public void setDocType(Map<String, Long> docType) {
		this.docType = docType;
	}
	public List<ResultBean> getListOfResultBean() {
		return listOfResultBean;
	}
	public void setListOfResultBean(List<ResultBean> listOfResultBean) {
		this.listOfResultBean = listOfResultBean;
	}
	public boolean getContainsResult() {
		return containsResult;
	}
	public void setContainsResult(boolean containsResult) {
		this.containsResult = containsResult;
	}
	public List<ResultBean> getAltlistOfResultBean() {
		return altlistOfResultBean;
	}
	public void setAltlistOfResultBean(List<ResultBean> altlistOfResultBean) {
		this.altlistOfResultBean = altlistOfResultBean;
	}
	public Map<String, String> getTempUrlsString() {
		return tempUrlsString;
	}
	public void setTempUrlsString(Map<String, String> tempUrlsString) {
		this.tempUrlsString = tempUrlsString;
	}
	
	public String getgFactSheet() {
		return gFactSheet;
	}
	public void setgFactSheet(String gFactSheet) {
		this.gFactSheet = gFactSheet;
	}
	public String getgTheCareAct() {
		return gTheCareAct;
	}
	public void setgTheCareAct(String gTheCareAct) {
		this.gTheCareAct = gTheCareAct;
	}
	public String getgGuidanceNote() {
		return gGuidanceNote;
	}
	public void setgGuidanceNote(String gGuidanceNote) {
		this.gGuidanceNote = gGuidanceNote;
	}
	public String getgPolicy() {
		return gPolicy;
	}
	public void setgPolicy(String gPolicy) {
		this.gPolicy = gPolicy;
	}
	

	
	
	
}
