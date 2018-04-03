package org.pcg.bucks_ss.beans;

import java.util.Set;

/**
 * 
 * @author oonyimadu
 *
 */
public class ResultBean 
{
	private String documentUrl;
	private String docId;
	
	private Set<String> author;
	private String owner;
	
	private String docType;
	
	private Set<String> docGroup;
	private String docType2;
	private String body;
	
	private Set<String> title;
	private Set<String> keyword;
	private Set<String> listOfTitles;
	private Set<String> other_title;
	
	private Set<String> chapterTitle; 
	private Set<String> partTitle; 
	private Set<String> partOfDocTitle; 
	private String partOfDocBody; 
	
	private String version;
	private String dateOfPublication;
		
	private Set<String> associatedEmails; 
	private Set<String> associatedUrls; 
	private Set<String> imageUrl; 
	
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public Set<String> getDocGroup() {
		return docGroup;
	}
	public void setDocGroup(Set<String> docGroup) {
		this.docGroup = docGroup;
	}
	public String getDocType2() {
		return docType2;
	}
	public void setDocType2(String docType2) {
		this.docType2 = docType2;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Set<String> getTitle() {
		return title;
	}
	public void setTitle(Set<String> title) {
		this.title = title;
	}
	public Set<String> getKeyword() {
		return keyword;
	}
	public void setKeyword(Set<String> keyword) {
		this.keyword = keyword;
	}
	public Set<String> getListOfTitles() {
		return listOfTitles;
	}
	public void setListOfTitles(Set<String> listOfTitles) {
		this.listOfTitles = listOfTitles;
	}
	public Set<String> getOther_title() {
		return other_title;
	}
	public void setOther_title(Set<String> other_title) {
		this.other_title = other_title;
	}
	public Set<String> getChapterTitle() {
		return chapterTitle;
	}
	public void setChapterTitle(Set<String> chapterTitle) {
		this.chapterTitle = chapterTitle;
	}
	public Set<String> getPartTitle() {
		return partTitle;
	}
	public void setPartTitle(Set<String> partTitle) {
		this.partTitle = partTitle;
	}
	public Set<String> getPartOfDocTitle() {
		return partOfDocTitle;
	}
	public void setPartOfDocTitle(Set<String> partOfDocTitle) {
		this.partOfDocTitle = partOfDocTitle;
	}
	public String getPartOfDocBody() {
		return partOfDocBody;
	}
	public void setPartOfDocBody(String partOfDocBody) {
		this.partOfDocBody = partOfDocBody;
	}
	public Set<String> getAssociatedEmails() {
		return associatedEmails;
	}
	public void setAssociatedEmails(Set<String> associatedEmails) {
		this.associatedEmails = associatedEmails;
	}
	public Set<String> getAssociatedUrls() {
		return associatedUrls;
	}
	public void setAssociatedUrls(Set<String> associatedUrls) {
		this.associatedUrls = associatedUrls;
	}
	public Set<String> getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(Set<String> imageUrl) {
		this.imageUrl = imageUrl;
	}

	
	public String getDocumentUrl() {
		return documentUrl;
	}
	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}
	
	public Set<String> getAuthor() {
		return author;
	}
	public void setAuthor(Set<String> author) {
		this.author = author;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDateOfPublication() {
		return dateOfPublication;
	}
	public void setDateOfPublication(String dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

}
