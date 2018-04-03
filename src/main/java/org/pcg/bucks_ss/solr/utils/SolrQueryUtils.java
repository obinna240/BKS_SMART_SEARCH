package org.pcg.bucks_ss.solr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.pcg.bucks_ss.beans.HomePageBean;
import org.pcg.bucks_ss.beans.ImageObjects;
import org.pcg.bucks_ss.beans.PaginatorBean;
import org.pcg.bucks_ss.beans.ResultBean;
import org.pcg.bucks_ss.beans.SearchResultBean;
import org.pcg.bucks_ss.solr.queryUtils.SmartSuggestQuery;
import org.pcg.bucks_ss.solr.queryUtils.SmartSuggestQueryBuilder;
import org.pcg.bucks_ss.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import org.springframework.data.mongodb.core.query.Query.*;


import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

/**
 * 
 * @author oonyimadu
 *
 */
public class SolrQueryUtils 
{
	private static HttpSolrServer server;
	
	@Autowired
	GridFsOperations gridOperations;
	
	@Autowired
	private QueryParameters queryParameters;
	
	public QueryParameters getQueryParameters() {
		return queryParameters;
	}

	public void setQueryParameters(QueryParameters queryParameters) {
		this.queryParameters = queryParameters;
	}

	/**
	 * @return the server
	 */
	public static HttpSolrServer getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public static void setServer(HttpSolrServer server) {
		SolrQueryUtils.server = server;
	}
	
	/**
	 * Get smart suggest
	 * @param queryText
	 * @return
	 */
	public String getSmartSuggest(String queryText)
	{
		QueryResponse response = null;
		SmartSuggestQuery smartSuggestQuery = new SmartSuggestQuery();
		String url = queryParameters.getSolrSmartSuggestUrl();
		
		try
		{
			server= new HttpSolrServer(url);
			server.setParser(new XMLResponseParser());
			SolrQuery query = new SolrQuery();
			HashMap<String,List<String>> qPara= queryParameters.getQuery();
			List<String> dRep = qPara.get("smartSuggest");
			String q = dRep.get(0);
			String qt = dRep.get(1);
			
			
			String smartSuggestUrl  = dRep.get(2);
		
			query.setParam(q, queryText);
			query.setParam(qt, smartSuggestUrl);
			response = server.query(query);
		}
		catch(SolrServerException exc)
		{
				exc.printStackTrace();
		}
		
		Object obj = smartSuggestQuery.getSmartSuggestions(response, 0);//use zero if doing normal search
		Gson gson = new Gson();
		String sJson = gson.toJson(obj);
		return sJson;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<HomePageBean> getMostRecentUploads()
	{
		QueryResponse response = null;
		
		HashMap<String, List<String>> mapStr = queryParameters.getQuery();
		List<String> listVal =  mapStr.get("recentUploads");
		
		String q = listVal.get(0);
		String qquery = listVal.get(1);
		String fl = listVal.get(2); 
		String sort = listVal.get(3); 
		Integer numResults  = Integer.parseInt(listVal.get(4)); 
		String filterField = listVal.get(5);
		
		String url = queryParameters.getSolrQueryUrl();
		server= new HttpSolrServer(url);
		server.setParser(new XMLResponseParser());
		SolrQuery query = new SolrQuery();
		
		query.setParam(q, qquery);
		query.setSort(sort, ORDER.desc);
		query.setParam("fl", fl);
		query.setStart(0);
		query.setRows(numResults);
		query.setParam("fq", filterField);
		
		List<HomePageBean> homePageBeans = new LinkedList<HomePageBean>();
		try
		{
		response = server.query(query);
		
		long resSize = response.getResults().getNumFound();
		
		
		
		if(resSize>0)
		{
			
			homePageBeans = getHomePageBeans(response, homePageBeans);
			
			
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return homePageBeans;
		
	}
	
	private List<HomePageBean> getHomePageBeans(QueryResponse  response, List<HomePageBean> homePageBeans)
	{
		SolrDocumentList resList = response.getResults();
		for(SolrDocument sdoc : resList)
		{
			HomePageBean hpb = new HomePageBean();
			
			String id = (String) sdoc.get("id");
			hpb.setId(id);
			
			Date date = (Date) sdoc.get("dateOfUpload");
			hpb.setDateOfUpload(date);
			
			String docGroup = (String) sdoc.get("docGroup");
			hpb.setDocGroup(docGroup);
			
			String docTitle = (String) sdoc.get("dTitle");
			hpb.setTitle(docTitle);
			
			Object author = (Object) sdoc.get("author");
			hpb.setAuthor(author);
			
			String docType = (String) sdoc.get("docType");
			hpb.setDocType(docType);
			
			String dateOfPublicationString = (String) sdoc.get("dateOfPublicationString");
			hpb.setDateOfPublicationString(dateOfPublicationString);
			
			String docVersion  = (String) sdoc.get("docVersion");
			hpb.setDocVersion(docVersion);
			
			String documentUrl  = (String) sdoc.get("documentUrl");
			hpb.setDocumentUrl(documentUrl);
			
			homePageBeans.add(hpb);
		}
		return homePageBeans;
	}
	
	/**
	 * 
	 * @param searchTerm
	 * @param sortBy
	 * @param qParamStartRow
	 * @param isSmartSuggest
	 * @return SearchResultBean
	 */
	public  SearchResultBean normalSearch(String searchTerm,  Boolean sortBy, Map<String,Object> qParamStartRow, Boolean isSmartSuggest,String filter,String filterValue )
	{
		
		QueryResponse response = null;
		
		HashMap<String, List<String>> mapStr = queryParameters.getQuery();
		List<String> listVal =  mapStr.get("normalSearch");
		
		String normalSearchHandler = listVal.get(2);
		String qt = listVal.get(1);
		String q = listVal.get(0);
		List<String> facetFields = mapStr.get("facetFields");
		PaginatorBean originalPaginatorBean = null;
		SearchResultBean searchResultBean = null;
		try
		{
			String url = queryParameters.getSolrQueryUrl();
			server= new HttpSolrServer(url);
			server.setParser(new XMLResponseParser());
			SolrQuery query = new SolrQuery();
			
			Integer stpWrdCheck = checkNumberOfStopWords(searchTerm);
			HashMap<String, Object> qmap = setQueryParameters(stpWrdCheck, searchTerm);
			
			String queryParamQue = (String) qmap.get("query");
			Integer qs = (Integer) qmap.get("qs");
			Integer ps = (Integer) qmap.get("ps");
			Integer mm = (Integer) qmap.get("mm");
			
			query.setParam(q, queryParamQue);
			if(qs!=null)
			{
				query.setParam("qs", qs.toString());
			}
			if(ps!=null)
			{
				query.setParam("ps", ps.toString());
			}
			if(mm!=null)
			{
				query.setParam("mm", mm.toString());
			}
			
			query.setParam(qt, normalSearchHandler);
			
			if(StringUtils.isNotBlank(filter) && StringUtils.isNotBlank(filterValue))
			{
				String filterField = SmartSuggestQueryBuilder.getFilterField(filter);
				query.setParam("fq",filterField+"\""+filterValue+"\"");
			}
			
			if(BooleanUtils.isFalse(isSmartSuggest))
			{
				if(BooleanUtils.isTrue(sortBy))
				{
					List<String> basicSearchFields = mapStr.get("basicSearch");
					String date = basicSearchFields.get(0);
					query.setSort(date, ORDER.desc);
					
				}
				
				if(MapUtils.isNotEmpty(qParamStartRow))
				{
					
					Integer qStart = (Integer)qParamStartRow.get("queryStart");
					Integer qRows = (Integer)qParamStartRow.get("queryRows");
					originalPaginatorBean = (PaginatorBean)qParamStartRow.get("originalPaginatorBean");
					query.setStart(qStart);
					query.setRows(qRows);
				}
				
				
				String[] facetParams = new String[facetFields.size()];
				facetParams = facetFields.toArray(facetParams);
				query=SmartSuggestQueryBuilder.appendFacetQuery(query,facetParams);
				
				
			}
			else
			{
				query.setStart(0);
				query.setRows(5);
			}
			
			//append highlighter
			query = appendHighlighter(query);
			
			response = server.query(query);
			long resSize = response.getResults().getNumFound();
			
			if(resSize>0)
			{
				Map<String, Map<String, List<String>>> highlights = response.getHighlighting();
				int ms = highlights.size();
				
				searchResultBean = new SearchResultBean();
				searchResultBean.setContainsResult(true);
				searchResultBean = getSearchResultBean (response, searchResultBean, facetFields,originalPaginatorBean);
			}
			else
			{
				searchResultBean = new SearchResultBean();
				searchResultBean.setNumFound(0);
				searchResultBean.setContainsResult(false);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return searchResultBean;
	}
	
	public  SearchResultBean normalSearch(String searchTerm,  Boolean sortBy, Map<String,Object> qParamStartRow, Boolean isSmartSuggest,String filter,String filterValue, String availabilityFilterValue, String availabilityFilterType )
	{
		
		QueryResponse response = null;
		
		HashMap<String, List<String>> mapStr = queryParameters.getQuery();
		List<String> listVal =  mapStr.get("normalSearch");
		
		String normalSearchHandler = listVal.get(2);
		String qt = listVal.get(1);
		String q = listVal.get(0);
		List<String> facetFields = mapStr.get("facetFields");
		PaginatorBean originalPaginatorBean = null;
		SearchResultBean searchResultBean = null;
		try
		{
			String url = queryParameters.getSolrQueryUrl();
			server= new HttpSolrServer(url);
			server.setParser(new XMLResponseParser());
			SolrQuery query = new SolrQuery();
			
			Integer stpWrdCheck = checkNumberOfStopWords(searchTerm);
			HashMap<String, Object> qmap = setQueryParameters(stpWrdCheck, searchTerm);
			
			String queryParamQue = (String) qmap.get("query");
			Integer qs = (Integer) qmap.get("qs");
			Integer ps = (Integer) qmap.get("ps");
			Integer mm = (Integer) qmap.get("mm");
			
			query.setParam(q, queryParamQue);
			if(qs!=null)
			{
				query.setParam("qs", qs.toString());
			}
			if(ps!=null)
			{
				query.setParam("ps", ps.toString());
			}
			if(mm!=null)
			{
				query.setParam("mm", mm.toString());
			}
			
			query.setParam(qt, normalSearchHandler);
			
			String fquery1 = "";
			String fquery2 = "";
			if(StringUtils.isNotBlank(filter) && StringUtils.isNotBlank(filterValue))
			{
				String filterField = SmartSuggestQueryBuilder.getFilterField(filter);
				fquery1 = filterField+"\""+filterValue+"\"";
				
				//query.setParam("fq",filterField+"\""+filterValue+"\"");
				
			}
			
			if(StringUtils.isNotBlank(availabilityFilterType) && StringUtils.isNotBlank(availabilityFilterValue))
			{
				String filterField = SmartSuggestQueryBuilder.getFilterField(availabilityFilterType);
				fquery2 = filterField+"\""+availabilityFilterValue+"\"";
				
				//query.setParam("fq",filterField+"\""+filterValue+"\"");
				
			}
			
			if(StringUtils.isBlank(fquery1) && StringUtils.isNotBlank(fquery2))
			{
				query.setParam("fq",fquery2);
			}
			else if(StringUtils.isNotBlank(fquery1) && StringUtils.isBlank(fquery2))
			{
				query.setParam("fq",fquery1);
			}
			else if(StringUtils.isNotBlank(fquery1) && StringUtils.isNotBlank(fquery2))
			{
				query.setParam("fq",fquery1+" AND "+fquery2);
			}
			
			if(BooleanUtils.isFalse(isSmartSuggest))
			{
				if(BooleanUtils.isTrue(sortBy))
				{
					List<String> basicSearchFields = mapStr.get("basicSearch");
					String date = basicSearchFields.get(0);
					query.setSort(date, ORDER.desc);
					
				}
				
				if(MapUtils.isNotEmpty(qParamStartRow))
				{
					
					Integer qStart = (Integer)qParamStartRow.get("queryStart");
					Integer qRows = (Integer)qParamStartRow.get("queryRows");
					originalPaginatorBean = (PaginatorBean)qParamStartRow.get("originalPaginatorBean");
					query.setStart(qStart);
					query.setRows(qRows);
				}
				
				
				String[] facetParams = new String[facetFields.size()];
				facetParams = facetFields.toArray(facetParams);
				query=SmartSuggestQueryBuilder.appendFacetQuery(query,facetParams);
				
				
			}
			else
			{
				query.setStart(0);
				query.setRows(5);
			}
			response = server.query(query);
			long resSize = response.getResults().getNumFound();
			
			if(resSize>0)
			{
				searchResultBean = new SearchResultBean();
				searchResultBean.setContainsResult(true);
				searchResultBean = getSearchResultBean (response, searchResultBean, facetFields,originalPaginatorBean);
			}
			else
			{
				searchResultBean = new SearchResultBean();
				searchResultBean.setNumFound(0);
				searchResultBean.setContainsResult(false);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return searchResultBean;
	}
	/**
	 * 
	 * @param searchTerm
	 * @param objectType
	 * @param sortBy
	 * @param qParamStartRow
	 * @param filter
	 * @param filterValue
	 * @param availabilityFilterValue
	 * @param availabilityFilterType
	 * @return SearchResultBean
	 */
	public  SearchResultBean search(String searchTerm, String objectType, Boolean sortBy, Map<String,Object> qParamStartRow, String filter, String filterValue, String availabilityFilterValue, String availabilityFilterType)
	{
		QueryResponse response = null;
		searchTerm = Util.specialCharacterHandler(searchTerm);
		PaginatorBean originalPaginatorBean = null;
		SearchResultBean searchResultBean  = null;
		
		Long alternativeSearchValue = 0l;
		
		try
		{
			String url = queryParameters.getSolrQueryUrl();
			HashMap<String, List<String>> mapStr = queryParameters.getQuery();
			
			List<String> listVal =  mapStr.get("smartSuggestFields");
			List<String> resultFields = mapStr.get("resultFields");
			List<String> queryFields = mapStr.get("queryFields");
			List<String> basicSearchFields = mapStr.get("basicSearch");
			List<String> facetFields = mapStr.get("facetFields");
			
			String[] params = new String[resultFields.size()];
			params = resultFields.toArray(params);
			
			String[] facetParams = new String[facetFields.size()];
			facetParams = facetFields.toArray(facetParams);
			
			server= new HttpSolrServer(url);
			server.setParser(new XMLResponseParser());
			SolrQuery query = new SolrQuery();
			
			
			
			if(StringUtils.isNotBlank(objectType))
			{
				//if(objectType.equals(anObject))
				query = SmartSuggestQueryBuilder.constructQuery(searchTerm, objectType, listVal, params, queryFields, facetParams, filter, filterValue, availabilityFilterType, availabilityFilterValue);
				
			}
			else
			{
				SearchResultBean resultBean2 = normalSearch( searchTerm, sortBy, qParamStartRow, false, filter, filterValue, availabilityFilterValue, availabilityFilterType);
				return resultBean2;
			}
			
			if(BooleanUtils.isTrue(sortBy))
			{
				String date = basicSearchFields.get(0);
				query.setSort(date, ORDER.desc);
			}
			
			
			
			if(MapUtils.isNotEmpty(qParamStartRow))
			{
				Integer qStart = (Integer)qParamStartRow.get("queryStart");
				Integer qRows = (Integer)qParamStartRow.get("queryRows");
				originalPaginatorBean = (PaginatorBean)qParamStartRow.get("originalPaginatorBean");
				query.setStart(qStart);
				query.setRows(qRows);
			}
			
			response = server.query(query);
			Long resSize = response.getResults().getNumFound();
			
			if(resSize>0)
			{
				//List<FacetField> ls = response.getFacetFields();
				
				searchResultBean = new SearchResultBean();
				//removed
				
				searchResultBean.setContainsResult(true);
				searchResultBean = getSearchResultBean (response, searchResultBean, facetFields,originalPaginatorBean);
				
				if(objectType.equalsIgnoreCase("Hints")||objectType.equalsIgnoreCase("Topic")||objectType.equalsIgnoreCase("Keyword")||
						objectType.equalsIgnoreCase("CareAct_Summary")||objectType.equalsIgnoreCase("CareAct_Directive"))
				
				{
					SearchResultBean searchNormal = normalSearch(searchTerm, null, null, true, null, null);
					
					
					
					Integer numfound = searchNormal.getNumFound();
					if(numfound>0)
					{
						
						alternativeSearchValue = new Long(numfound);
						searchResultBean.setAlternativeResults(alternativeSearchValue);
						
						List<ResultBean> resBean2 = searchNormal.getListOfResultBean();
						if(CollectionUtils.isNotEmpty(resBean2))
						{
							searchResultBean.setAltlistOfResultBean(resBean2);
						}
						
					}
					else
					{
						searchResultBean.setAlternativeResults(alternativeSearchValue);
					}
				}
				else if(objectType.equalsIgnoreCase("Organization")||objectType.equalsIgnoreCase("Place")||objectType.equalsIgnoreCase("Person")||
						objectType.equalsIgnoreCase("Document"))
				{
					searchResultBean.setAlternativeResults(alternativeSearchValue);
				}
			}
			else
			{
				searchResultBean = new SearchResultBean();
				searchResultBean.setContainsResult(false);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return searchResultBean;
	}
	
	public  SearchResultBean search(String searchTerm, String objectType, Boolean sortBy, Map<String,Object> qParamStartRow, String filter, String filterValue)
	{
		QueryResponse response = null;
		searchTerm = Util.specialCharacterHandler(searchTerm);
		PaginatorBean originalPaginatorBean = null;
		SearchResultBean searchResultBean  = null;
		
		Long alternativeSearchValue = 0l;
		
		try
		{
			String url = queryParameters.getSolrQueryUrl();
			HashMap<String, List<String>> mapStr = queryParameters.getQuery();
			
			List<String> listVal =  mapStr.get("smartSuggestFields");
			List<String> resultFields = mapStr.get("resultFields");
			List<String> queryFields = mapStr.get("queryFields");
			List<String> basicSearchFields = mapStr.get("basicSearch");
			List<String> facetFields = mapStr.get("facetFields");
			
			String[] params = new String[resultFields.size()];
			params = resultFields.toArray(params);
			
			String[] facetParams = new String[facetFields.size()];
			facetParams = facetFields.toArray(facetParams);
			
			server= new HttpSolrServer(url);
			server.setParser(new XMLResponseParser());
			SolrQuery query = new SolrQuery();
			
			
			
			if(StringUtils.isNotBlank(objectType))
			{
				//if(objectType.equals(anObject))
				query = SmartSuggestQueryBuilder.constructQuery(searchTerm, objectType, listVal, params, queryFields, facetParams, filter, filterValue);
				
			}
			else
			{
				SearchResultBean resultBean2 = normalSearch( searchTerm, sortBy, qParamStartRow, false, filter, filterValue);
				return resultBean2;
			}
			
			if(BooleanUtils.isTrue(sortBy))
			{
				String date = basicSearchFields.get(0);
				query.setSort(date, ORDER.desc);
			}
			
			
			
			if(MapUtils.isNotEmpty(qParamStartRow))
			{
				Integer qStart = (Integer)qParamStartRow.get("queryStart");
				Integer qRows = (Integer)qParamStartRow.get("queryRows");
				originalPaginatorBean = (PaginatorBean)qParamStartRow.get("originalPaginatorBean");
				query.setStart(qStart);
				query.setRows(qRows);
			}
			
			response = server.query(query);
			Long resSize = response.getResults().getNumFound();
			
			if(resSize>0)
			{
				//List<FacetField> ls = response.getFacetFields();
				
				searchResultBean = new SearchResultBean();
				//removed
				
				searchResultBean.setContainsResult(true);
				searchResultBean = getSearchResultBean (response, searchResultBean, facetFields,originalPaginatorBean);
				
				if(objectType.equalsIgnoreCase("Hints")||objectType.equalsIgnoreCase("Topic")||objectType.equalsIgnoreCase("Keyword")||
						objectType.equalsIgnoreCase("CareAct_Summary")||objectType.equalsIgnoreCase("CareAct_Directive"))
				
				{
					SearchResultBean searchNormal = normalSearch(searchTerm, null, null, true, null, null);
					
					
					
					Integer numfound = searchNormal.getNumFound();
					if(numfound>0)
					{
						
						alternativeSearchValue = new Long(numfound);
						searchResultBean.setAlternativeResults(alternativeSearchValue);
						
						List<ResultBean> resBean2 = searchNormal.getListOfResultBean();
						if(CollectionUtils.isNotEmpty(resBean2))
						{
							searchResultBean.setAltlistOfResultBean(resBean2);
						}
						
					}
					else
					{
						searchResultBean.setAlternativeResults(alternativeSearchValue);
					}
				}
				else if(objectType.equalsIgnoreCase("Organization")||objectType.equalsIgnoreCase("Place")||objectType.equalsIgnoreCase("Person")||
						objectType.equalsIgnoreCase("Document"))
				{
					searchResultBean.setAlternativeResults(alternativeSearchValue);
				}
			}
			else
			{
				searchResultBean = new SearchResultBean();
				searchResultBean.setContainsResult(false);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return searchResultBean;
	}
	/**
	 * 
	 * @param searchTerm
	 * @param objectType
	 * @param sortBy
	 * @param qParamStartRow
	 * @return SearchResultBean
	 */
	public  SearchResultBean search(String searchTerm, String objectType, Boolean sortBy, Map<String,Object> qParamStartRow)
	{
		
		QueryResponse response = null;
		searchTerm = Util.specialCharacterHandler(searchTerm);
		PaginatorBean originalPaginatorBean = null;
		SearchResultBean searchResultBean  = null;
		
		Long alternativeSearchValue = 0l;
		
		try
		{
			String url = queryParameters.getSolrQueryUrl();
			HashMap<String, List<String>> mapStr = queryParameters.getQuery();
			
			List<String> listVal =  mapStr.get("smartSuggestFields");
			List<String> resultFields = mapStr.get("resultFields");
			List<String> queryFields = mapStr.get("queryFields");
			List<String> basicSearchFields = mapStr.get("basicSearch");
			List<String> facetFields = mapStr.get("facetFields");
			
			String[] params = new String[resultFields.size()];
			params = resultFields.toArray(params);
			
			String[] facetParams = new String[facetFields.size()];
			facetParams = facetFields.toArray(facetParams);
			
			server= new HttpSolrServer(url);
			server.setParser(new XMLResponseParser());
			SolrQuery query = new SolrQuery();
			
			if(StringUtils.isNotBlank(objectType))
			{
				//if(objectType.equals(anObject))
				query = SmartSuggestQueryBuilder.constructQuery(searchTerm, objectType, listVal, params, queryFields, facetParams);
				
			}
			else
			{
				SearchResultBean resultBean2 = normalSearch( searchTerm, sortBy, qParamStartRow, false, null, null);
				return resultBean2;
			}
			
			if(BooleanUtils.isTrue(sortBy))
			{
				String date = basicSearchFields.get(0);
				query.setSort(date, ORDER.desc);
			}
			
			if(MapUtils.isNotEmpty(qParamStartRow))
			{
				Integer qStart = (Integer)qParamStartRow.get("queryStart");
				Integer qRows = (Integer)qParamStartRow.get("queryRows");
				originalPaginatorBean = (PaginatorBean)qParamStartRow.get("originalPaginatorBean");
				query.setStart(qStart);
				query.setRows(qRows);
			}
			
			response = server.query(query);
			Long resSize = response.getResults().getNumFound();
			
			if(resSize>0)
			{
				//List<FacetField> ls = response.getFacetFields();
				
				searchResultBean = new SearchResultBean();
				//removed
				
				searchResultBean.setContainsResult(true);
				searchResultBean = getSearchResultBean (response, searchResultBean, facetFields,originalPaginatorBean);
				
				if(objectType.equalsIgnoreCase("Hints")||objectType.equalsIgnoreCase("Topic")||objectType.equalsIgnoreCase("Keyword")||
						objectType.equalsIgnoreCase("CareAct_Summary")||objectType.equalsIgnoreCase("CareAct_Directive"))
				
				{
					SearchResultBean searchNormal = normalSearch(searchTerm, null, null, true, null, null);
					
					
					
					Integer numfound = searchNormal.getNumFound();
					if(numfound>0)
					{
						
						alternativeSearchValue = new Long(numfound);
						searchResultBean.setAlternativeResults(alternativeSearchValue);
						
						List<ResultBean> resBean2 = searchNormal.getListOfResultBean();
						if(CollectionUtils.isNotEmpty(resBean2))
						{
							searchResultBean.setAltlistOfResultBean(resBean2);
						}
						
					}
					else
					{
						searchResultBean.setAlternativeResults(alternativeSearchValue);
					}
				}
				else if(objectType.equalsIgnoreCase("Organization")||objectType.equalsIgnoreCase("Place")||objectType.equalsIgnoreCase("Person")||
						objectType.equalsIgnoreCase("Document"))
				{
					searchResultBean.setAlternativeResults(alternativeSearchValue);
				}
			}
			else
			{
				searchResultBean = new SearchResultBean();
				searchResultBean.setContainsResult(false);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return searchResultBean;
	}
	
	
	/**
	 * 
	 * @param response
	 * @param searchResultBean
	 * @param facetFields
	 * @param originalPaginatorBean
	 * @return SearchResultBean
	 * 
	 */
	private SearchResultBean getSearchResultBean (QueryResponse response, SearchResultBean searchResultBean, List<String> facetFields, PaginatorBean originalPaginatorBean) 
	{
		
		if(searchResultBean!=null)
		{
			Long resSize = response.getResults().getNumFound();
									
			FacetField docGroup = response.getFacetField(facetFields.get(0));
			FacetField docType = response.getFacetField(facetFields.get(1));
			FacetField author = response.getFacetField(facetFields.get(2));
			FacetField associatedUrls = response.getFacetField(facetFields.get(3));
			FacetField owner = response.getFacetField(facetFields.get(4));
			
			
			Map<String, Long> dGroup = Util.processFacets(docGroup);
			Map<String, Long> dType = Util.processFacets(docType);
			Map<String, Long> dAuthor = Util.processFacets(author);
			Map<String, Long> dasscUrl = Util.processFacets(associatedUrls);
			
			Map<String, Long> downer = Util.processFacets(owner);
			
			searchResultBean.setDocGroup(dGroup);
			
			if(MapUtils.isNotEmpty(dGroup))
			{
				if(dGroup.containsKey("Policy"))
				{
					String gPolicy = "Policy";
					searchResultBean.setgPolicy(gPolicy);
				}
				if(dGroup.containsKey("The Care Act"))
				{
					String gTheCareAct = "The Care Act";
					searchResultBean.setgTheCareAct(gTheCareAct);
				}
				if(dGroup.containsKey("Guidance Note"))
				{
					String gGuidanceNote = "Guidance Note";
					searchResultBean.setgGuidanceNote(gGuidanceNote);
				}
				if(dGroup.containsKey("Factsheet"))
				{
					String gFactSheet = "Factsheet";
					searchResultBean.setgFactSheet(gFactSheet);
				}
				if(dGroup.containsKey("Conferences"))
				{
					String gConferences = "Conferences";
					searchResultBean.setgConferences(gConferences);
				}
				if(dGroup.containsKey("Newsletters"))
				{
					String gNewsletters = "Newsletters";
					searchResultBean.setgNewsletters(gNewsletters);
				}
				if(dGroup.containsKey("Presentations"))
				{
					String gPresentations = "Presentations";
					searchResultBean.setgPresentations(gPresentations);
				}
				if(dGroup.containsKey("Strategy"))
				{
					String gStrategy = "Strategy";
					searchResultBean.setgStrategy(gStrategy);
				}
				if(dGroup.containsKey("Form"))
				{
					String gForm = "Form";
					searchResultBean.setgForm(gForm);
				}
				if(dGroup.containsKey("Others"))
				{
					String gOthers = "Others";
					searchResultBean.setgOthers(gOthers);
				}
			
			}
						
			searchResultBean.setDocType(dType);
			searchResultBean.setAuthors(dAuthor);
			searchResultBean.setAsscUrls(dasscUrl);
			searchResultBean.setOwner(downer);
			
			if(MapUtils.isNotEmpty(dasscUrl))
			{
				Map<String, String> aurl = new HashMap<String, String>();
				for(Map.Entry<String, Long> entry:dasscUrl.entrySet())
				{
					String key = entry.getKey();
					String val = StringUtils.substringAfterLast(key, "/");
					val = StringUtils.substringBeforeLast(val, ".");
					aurl.put(key, val);
				}
				searchResultBean.setTempUrlsString(aurl);
			}
			
			Integer resInt = resSize.intValue();
			searchResultBean.setNumFound(resInt);
			
			PaginatorBean resultPBean = null;
			
			if(originalPaginatorBean!=null)
			{
				resultPBean = PaginationUtils.doPaginationTest(resInt, originalPaginatorBean);
				searchResultBean.setPaginatorBean(resultPBean);
			}
			
			SolrDocumentList solrList = response.getResults();
			List<ResultBean> listOfResultBean = new LinkedList<ResultBean>();
			
			Map<String, Map<String, List<String>>> highlights = response.getHighlighting();
			
			//list of images
			List<ImageObjects> listOfImageObjects = new LinkedList<ImageObjects>();
			
			if(CollectionUtils.isNotEmpty(solrList))
			{
				for(SolrDocument solrDoc:solrList)
				{
					ResultBean resbean = Util.doSearchResultBean(solrDoc);
					String diD = resbean.getDocId();
					
					
					GridFsOperations gs = getGridOperations();
					Query q = new Query().addCriteria(Criteria.where("metadata.image_doc_id").is(diD));//"FullBody_"+
					List<GridFSDBFile> result = gs.find(q);
					if(CollectionUtils.isNotEmpty(result))
					{
						for (GridFSDBFile file : result) 
						{
							InputStream fis = file.getInputStream();
							try
							{
								ImageObjects iObjects = new ImageObjects();
								byte[] img = Base64.encodeBase64(IOUtils.toByteArray(fis));
								
								DBObject metaObj = file.getMetaData();
								String imgs = new String(img);
								
								DBObject metadata = (DBObject) file.get("metadata");
								String idocTitle = (String) metadata.get("docTitle");
								String idocCategory = (String) metadata.get("docCategory");
								String imageLocation = (String) metadata.get("imageLocation");
								String image_doc_id = (String) metadata.get("image_doc_id");
								String dateOfPub = (String) metadata.get("dateOfPub");
								String iowner = (String) metadata.get("owner");
								List ilist = (BasicDBList) metadata.get("authors");
								
								iObjects.setImageBinary(imgs);
								
								
								iObjects.setAuthors(ilist);
								iObjects.setOwner(iowner);
								iObjects.setDateOfPub(dateOfPub);
								iObjects.setImage_doc_id(image_doc_id);
								iObjects.setImageLocation(imageLocation);
								iObjects.setDocCategory(idocCategory);
								iObjects.setDocTitle(idocTitle);
								
								
								listOfImageObjects.add(iObjects);
							}
							catch(IOException io)
							{
								io.printStackTrace();
							}
							finally
							{
								if (fis != null) 
								{
									try 
									{
										fis.close();
									} 
									catch (IOException e) 
									{
										e.printStackTrace();
									}
								}
							}
							
						}
					}
					
					/* End images from Db */
					
					if (MapUtils.isNotEmpty(highlights))
					{
						Map<String, List<String>> mdid = highlights.get(diD);
						if (MapUtils.isNotEmpty(mdid))
						{
							 List<String> bodyListHigh= mdid.get("body_analysed");
							 if(CollectionUtils.isNotEmpty(bodyListHigh))
							 {
								 String hbody = bodyListHigh.get(0);
								 resbean.setBody(hbody);
							 }
							 
							 List<String> titleListHigh= mdid.get("title_analysed");
							 if(CollectionUtils.isNotEmpty(titleListHigh))
							 {
								
								 resbean.setTitle(new HashSet(titleListHigh));
							 }
						}
					}
					listOfResultBean.add(resbean);
				}
			}
			if(CollectionUtils.isNotEmpty(listOfImageObjects))
			{
				searchResultBean.setListOfImageObjects(listOfImageObjects);
			}
			
			searchResultBean.setListOfResultBean(listOfResultBean);
		}
		return searchResultBean;
	}
	
	
	
	
	public GridFsOperations getGridOperations() {
		return gridOperations;
	}

	public void setGridOperations(GridFsOperations gridOperations) {
		this.gridOperations = gridOperations;
	}

	private Integer checkNumberOfStopWords(String query)
	{
		Integer numElements = 0;
		if(StringUtils.isNotBlank(query))
		{
			List<String> stpWordList = queryParameters.getStopwords();
			StringTokenizer strTokenizer = new StringTokenizer(query);
			Integer nTokens = strTokenizer.countTokens();
			String[] punctuation = {".",",","!",":","?","-","+"};
			if(nTokens >3)
			{
			while (strTokenizer.hasMoreElements()) 
			{
			String ret = (String) strTokenizer.nextElement();
			                                                                                                                                                                                                                                                                      
			if(StringUtils.containsAny(ret,punctuation))
			{
				for(String p:punctuation)
				{
					ret = StringUtils.remove(ret, p);
					ret = StringUtils.normalizeSpace(ret);
				}
			}
					if(!stpWordList.contains(ret))
					{
					numElements = numElements+1;
					}
				}
			}
			else
			{
				
			}
		}
		return numElements;
	}
	
	private String replaceAndOr(String string)
	{
		string = StringUtils.replacePattern(string, "(\\bor\\b)|(\\band\\b)", "");
		string = StringUtils.normalizeSpace(string);
		return string;
	}
	
	private HashMap<String, Object> setQueryParameters(Integer numElements, String query)
	{
		HashMap<String, Object> qParameters = new HashMap<String, Object>();
		query = query.toLowerCase();
		if(numElements!= 0)
		{
			if(numElements ==1)
			{
				query = "("+query+")";
				qParameters.put("query", query);
			}
			else if(numElements == 2 || numElements == 3)
			{
				Integer qs = 3; //2 or 3 is optimum - precision difference between 1 and 5%
				qParameters.put("qs", qs);
				Integer ps = 1;
				qParameters.put("ps", ps);
				//query = "\""+query+"\"";
				query = "("+query+")";
				qParameters.put("query", query);
			}
			else if(numElements > 3)
			{
				query =replaceAndOr(query);
				Integer qs = 3; //2 or 3 is optimum - precision difference between 1 and 5%
				qParameters.put("qs", qs);
				Integer ps = 1;
				qParameters.put("ps", ps);
				query = "("+query+")";
				qParameters.put("query", query);
				if(numElements == 4 || numElements ==5 )
				{
					Integer mm = 3;
					qParameters.put("mm", mm);
				}
				else if(numElements==6)
				{
					Integer mm = 4;
					qParameters.put("mm", mm);
				}
				else if(numElements==7)
				{
					Integer mm = 5;
					qParameters.put("mm", mm);
				}
				else if(numElements>8)
				{
					Integer mm = 6;
					qParameters.put("mm", mm);
				}
			}
		}
		else
		{
			if(numElements ==0)
			{
				query = "("+query+")";
				qParameters.put("query", query);
			}
			else if(numElements ==1)
			{
				query = "("+query+")";
				qParameters.put("query", query);
			}
			else if(numElements == 2 || numElements == 3)
			{
				Integer qs = 3; //2 or 3 is optimum - precision difference between 1 and 5%
				qParameters.put("qs", qs);
				Integer ps = 1;
				qParameters.put("ps", ps);
				//query = "\""+query+"\"";
				query = "("+query+")";
				qParameters.put("query", query);
			}
			else if(numElements > 3)
			{
				query =replaceAndOr(query);
				Integer qs = 3; //2 or 3 is optimum - precision difference between 1 and 5%
				qParameters.put("qs", qs);
				Integer ps = 1;
				qParameters.put("ps", ps);
				query = "("+query+")";
				qParameters.put("query", query);
				if(numElements == 4 || numElements ==5 )
				{
					Integer mm = 3;
					qParameters.put("mm", mm);
				}
				else if(numElements==6)
				{
					Integer mm = 4;
					qParameters.put("mm", mm);
				}
				else if(numElements==7)
				{
					Integer mm = 5;
					qParameters.put("mm", mm);
				}
				else if(numElements>8)
				{
					Integer mm = 6;
					qParameters.put("mm", mm);
				}
			}
		}
		return qParameters;
	}
	
	
	private SolrQuery appendHighlighter(SolrQuery query)
	{
		if (query!=null)
		{
			query.setHighlight(true);
			query.setHighlightSimplePost("</span>");
			query.setHighlightSimplePre("<span style=\"background-color:#00FFFF\">");
			query.setHighlightSnippets(90);
			query.setParam("hl.mergeContiguous", true);
			query.setParam("hl.highlightMultiTerm", true);
			query.setParam("hl.usePhraseHighlighter", true);
			query.setHighlightFragsize(0);
			query.setParam("hl.fl", "body_analysed, title_analysed");
			
		}
		return query;
	}
	/**
	public static void main(String[] args)
	{
		String x = "drugs and alcohol";
	
		x = StringUtils.replacePattern(x, "(\\bor\\b)|(\\band\\b)", "");
		x = StringUtils.normalizeSpace(x);
		
		System.out.println(x);
	}
	*/

}


