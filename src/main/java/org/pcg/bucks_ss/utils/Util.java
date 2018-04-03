package org.pcg.bucks_ss.utils;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocument;
import org.pcg.bucks_ss.beans.ResultBean;

public class Util 
{

	public static String specialCharacterHandler(String character)
	{
		String[] arr = {"+","-", "&&", "||", "!", "(", ")", "{", "}", "[", "]", "^", "\"", "~","*", ":", "\\" ,"'"};
		String[] arrs = {"\\+", "\\-", "\\&&", "\\||", "\\!", "\\(", "\\)", "\\{", "\\}", "\\[", "\\]", "\\^", "\\\"", "\\~","\\*", "\\:", "\\\\","\\'" };
				
		character = StringUtils.replaceEach(character, arr, arrs);
		return character;		
	}
	
	/**
	 * 
	 * @param ff
	 * @return map
	 */
	public static Map<String, Long> processFacets(FacetField ff)
	{
		Map<String, Long> map = new LinkedHashMap<String, Long>();
		if(ff!=null)
		{
			String ffname = ff.getName();
			
			int ffcount = ff.getValueCount();
			
			List<Count> counts = ff.getValues();
			
			for(Count c:counts)
			{
				String facetLabel = c.getName();
				Long facetCount = c.getCount();
				map.put(facetLabel, facetCount);
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @param solrDoc
	 * @return ResultBean
	 */
	public static ResultBean doSearchResultBean(SolrDocument solrDoc)//, Map<String, Map<String, List<String>>> highlights)
	{
		ResultBean rbean = new ResultBean();
		if(solrDoc != null)
		{
			String id = (String) solrDoc.getFieldValue("id");
			rbean.setDocId(id);
			//String id = (String) resultDoc.getFieldValue("id");
			
			String docUrl = (String) solrDoc.getFieldValue("documentUrl");
			if(StringUtils.isNotBlank(docUrl))
			{
				rbean.setDocumentUrl(docUrl);
			}
			
			Object authObject = solrDoc.getFieldValue("author");
			Set<String> aset = checkObjAsSet(authObject);
			if(CollectionUtils.isNotEmpty(aset))
			{
				rbean.setAuthor(aset);
			}
			
			String version = (String) solrDoc.getFieldValue("docVersion");
			if(StringUtils.isNotBlank(version))
			{
				rbean.setVersion(version);
			}
			
			String dateOfPublication = (String) solrDoc.getFieldValue("dateOfPublicationString");
			if(StringUtils.isNotBlank(dateOfPublication))
			{
				rbean.setDateOfPublication(dateOfPublication);
			}
			
			String owner = (String) solrDoc.getFieldValue("owner");
			if(StringUtils.isNotBlank(owner))
			{
				rbean.setOwner(owner);
			}
			
			String docType = (String) solrDoc.getFieldValue("docType");
			if(StringUtils.isNotBlank(docType))
			{
				rbean.setDocType(docType);
			}
			
			String docType2 = (String) solrDoc.getFieldValue("docType2");
			if(StringUtils.isNotBlank(docType2))
			{
				rbean.setDocType2(docType2);
			}
			
			Object docGroupT = solrDoc.getFieldValue("docGroup");
			Set<String> stDocGroup = checkObjAsSet(docGroupT);
			
			if(CollectionUtils.isNotEmpty(stDocGroup))
			{
				rbean.setDocGroup(stDocGroup);
			}
			
			String body = (String) solrDoc.getFieldValue("body");
			if(StringUtils.isNotBlank(body))
			{
				rbean.setBody(body);
			}
			
			Object title = solrDoc.getFieldValue("title");
			Set<String> t = checkObjAsSet(title);
			if(CollectionUtils.isNotEmpty(t))
			{
				rbean.setTitle(t);
			}
			
			Object keyword = solrDoc.getFieldValue("keyword");
			Set<String> k = checkObjAsSet(keyword);
			if(CollectionUtils.isNotEmpty(k))
			{
				rbean.setKeyword(k);
			}
			
			Object listOfTitles = solrDoc.getFieldValue("listOfTitles");
			Set<String> lt= checkObjAsSet(listOfTitles);
			if(CollectionUtils.isNotEmpty(lt))
			{
				rbean.setListOfTitles(lt);
			}
			
			Object other_title= solrDoc.getFieldValue("other_title");
			Set<String> ot= checkObjAsSet(other_title);
			if(CollectionUtils.isNotEmpty(ot))
			{
				rbean.setOther_title(ot);
			}
			
			Object chapterTitle= solrDoc.getFieldValue("chapterTitle");
			Set<String> ct= checkObjAsSet(chapterTitle);
			if(CollectionUtils.isNotEmpty(ct))
			{
				rbean.setChapterTitle(ct);
			}
			
			Object partTitle= solrDoc.getFieldValue("partTitle");
			Set<String> pt= checkObjAsSet(partTitle);
			if(CollectionUtils.isNotEmpty(pt))
			{
				rbean.setPartTitle(pt);
			}
			
			Object partOfDocTitle= solrDoc.getFieldValue("partOfDocTitle");
			Set<String> pdt= checkObjAsSet(partOfDocTitle);
			if(CollectionUtils.isNotEmpty(pdt))
			{
				rbean.setPartOfDocTitle(pdt);
			}
			
			String partOfDocBody = (String) solrDoc.getFieldValue("partOfDocBody");
			if(StringUtils.isNotBlank(partOfDocBody))
			{
				rbean.setPartOfDocBody(partOfDocBody);
			}
			 
			//Object associatedEmails= solrDoc.getFieldValue("associatedEmails");
			//Set<String> pEM= checkObjAsSet(associatedEmails);
			//if(CollectionUtils.isNotEmpty(pEM))
			//{
			//	rbean.setAssociatedEmails(pEM);
			//}
			
			Object associatedUrls= solrDoc.getFieldValue("associatedUrls");
			Set<String> purl= checkObjAsSet(associatedUrls);
			if(CollectionUtils.isNotEmpty(purl))
			{
				rbean.setAssociatedUrls(purl);
			}
			
			//Object imageUrl= solrDoc.getFieldValue("imageUrl");
			//Set<String> iurl= checkObjAsSet(imageUrl);
			//if(CollectionUtils.isNotEmpty(iurl))
			//{
			//	rbean.setAssociatedUrls(iurl);
			//}
			
		}
		return rbean;
	}
	
	
	
	public  static Set<String> checkObjAsSet(Object obj)
	{
		Set<String> val = null;
		if(obj!=null)
		{
			if(obj instanceof List)
			{
				val = new LinkedHashSet<String>((List<String>)obj);
			}
			else if(obj instanceof String)
			{
				val = new LinkedHashSet<String>();
				val.add((String)obj);
			}
		}
		return val;
	}
	
	public static List<String> checkObjAsList(Object obj)
	{
		List<String> val = null;
		if(obj!=null)
		{
			if(obj instanceof List)
			{
				val = (LinkedList<String>)obj;
			}
			else if(obj instanceof String)
			{
				val = new LinkedList<String>();
				val.add((String)obj);
			}
		}
		return val;
	}

}
