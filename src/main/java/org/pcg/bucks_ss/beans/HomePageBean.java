package org.pcg.bucks_ss.beans;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author oonyimadu
 *
 */
public class HomePageBean
{
	String id;
	Date dateOfUpload;
	String docGroup;
	String title;
	Object author;
	String docType;
	String documentUrl;
	String dateOfPublicationString;
	String docVersion;
	
	public String getDocumentUrl() {
		return documentUrl;
	}
	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}
	
	
	public String getDateOfPublicationString() {
		return dateOfPublicationString;
	}
	public void setDateOfPublicationString(String dateOfPublicationString) {
		this.dateOfPublicationString = dateOfPublicationString;
	}
	public String getDocVersion() {
		return docVersion;
	}
	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Object getAuthor() {
		return author;
	}
	public void setAuthor(Object author) {
		this.author = author;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDateOfUpload() {
		return dateOfUpload;
	}
	public void setDateOfUpload(Date dateOfUpload) {
		this.dateOfUpload = dateOfUpload;
	}
	public String getDocGroup() {
		return docGroup;
	}
	public void setDocGroup(String docGroup) {
		this.docGroup = docGroup;
	}
	
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	
	
}
