package org.pcg.bucks_ss.beans;

import java.util.List;

/**
 * 
 * @author oonyimadu
 *
 */
public class ImageObjects 
{
	private String imageLocation;
	private String imageBinary;
	private String image_doc_id;
	private String docTitle;
	private String dateOfPub;
	private String docCategory;
	private String owner;
	private List<String> authors;
	
	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	public String getImageBinary() {
		return imageBinary;
	}
	public void setImageBinary(String imageBinary) {
		this.imageBinary = imageBinary;
	}
	public String getImage_doc_id() {
		return image_doc_id;
	}
	public void setImage_doc_id(String image_doc_id) {
		this.image_doc_id = image_doc_id;
	}
	public String getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
	public String getDateOfPub() {
		return dateOfPub;
	}
	public void setDateOfPub(String dateOfPub) {
		this.dateOfPub = dateOfPub;
	}
	public String getDocCategory() {
		return docCategory;
	}
	public void setDocCategory(String docCategory) {
		this.docCategory = docCategory;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	
	
	
	

}
