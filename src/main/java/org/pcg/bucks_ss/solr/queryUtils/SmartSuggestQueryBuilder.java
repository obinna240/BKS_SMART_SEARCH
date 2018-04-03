package org.pcg.bucks_ss.solr.queryUtils;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;

/**
 * 
 * @author oonyimadu
 *
 */
public class SmartSuggestQueryBuilder 
{
	/**
	 * 
	 * @param queryString
	 * @param objectType
	 * @param listVal
	 * @param resultFields
	 * @param queryFields
	 * @return SolrQuery 
	 */
	public static SolrQuery constructQuery(String queryString, String objectType, List<String> listVal, String[] resultFields,List<String> queryFields, String[] facetParams)
	{
		SolrQuery query = new SolrQuery();
		

		String author = listVal.get(0);
		String organization = listVal.get(1);
		String place = listVal.get(2);
		String person = listVal.get(3);
		String document = listVal.get(4);
		String careact_directive = listVal.get(5);
		String careact_summary = listVal.get(6);
		String keyword = listVal.get(7);
		String hints = listVal.get(8);
		String topic = listVal.get(9);
		
		query.setParam("q", "*:*");
		
		String docType = queryFields.get(5);
		if(objectType.equals(author))
		{
			query.setParam("fq", queryFields.get(0)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(organization))
		{
			query.setParam("fq", queryFields.get(1)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(place))
		{
			query.setParam("fq", queryFields.get(2)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(person))
		{
			query.setParam("fq", queryFields.get(3)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(document))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(careact_directive))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"CareAct_Directive\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(careact_summary))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"CareAct_Summary\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(keyword))
		{
			query.setParam("fq", queryFields.get(6)+":"+"\""+queryString+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(hints))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"Hints\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(topic))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"Topic\"");
			query.setParam("fl", resultFields);
		}
		
		query = appendFacetQuery(query, facetParams);
		
		return query;
	}
	
	public static String getFilterField(String filter)
	{
		String filterField = "";
		if(filter.equals("Author"))
		{
			filterField = "author:";
		}
		else if(filter.equals("Owner"))
		{
			filterField = "owner:";
		}
		else if(filter.equals("Category"))
		{
			filterField = "docGroup:";
		}
		else if(filter.equals("Availability"))
		{
			filterField = "docType:";
		}
		else if(filter.equals("AAvailability"))
		{
			filterField = "docType2:";
		}
		return filterField;
	}
	
	public static SolrQuery constructQuery(String queryString, String objectType, List<String> listVal, String[] resultFields,List<String> queryFields, String[] facetParams,
			String filter, String filterValue, String availabilityFilterType, String availabilityFilterValue)
	{
		SolrQuery query = new SolrQuery();
		
		
		String author = listVal.get(0);
		String organization = listVal.get(1);
		String place = listVal.get(2);
		String person = listVal.get(3);
		String document = listVal.get(4);
		String careact_directive = listVal.get(5);
		String careact_summary = listVal.get(6);
		String keyword = listVal.get(7);
		String hints = listVal.get(8);
		String topic = listVal.get(9);
		
		String filterField =getFilterField(filter);
		String afilterField =getFilterField(availabilityFilterType);
		
		query.setParam("q", "*:*");
		
		String docType = queryFields.get(5);
		if(objectType.equals(author))
		{
			query.setParam("fq", queryFields.get(0)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\""+ " AND "+filterField+"\""+filterValue+"\""+ " AND "+afilterField+"\""+availabilityFilterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(organization))
		{
			query.setParam("fq", queryFields.get(1)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\""+ " AND "+filterField+"\""+filterValue+"\"" + " AND "+afilterField+"\""+availabilityFilterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(place))
		{
			query.setParam("fq", queryFields.get(2)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\""+ " AND "+filterField+"\""+filterValue+"\"" +" AND "+ afilterField+"\""+availabilityFilterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(person))
		{
			query.setParam("fq", queryFields.get(3)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\""+ " AND "+filterField+"\""+filterValue+"\"" +" AND "+ afilterField+"\""+availabilityFilterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(document))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\""+ " AND "+filterField+"\""+filterValue+"\"" +" AND "+ afilterField+"\""+availabilityFilterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(careact_directive))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"CareAct_Directive\""+ " AND "+filterField+"\""+filterValue+"\"" +" AND "+ afilterField+"\""+availabilityFilterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(careact_summary))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"CareAct_Summary\""+ " AND "+filterField+"\""+filterValue+"\"" +" AND "+ afilterField+"\""+availabilityFilterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(keyword))
		{
			query.setParam("fq", queryFields.get(6)+":"+"\""+queryString+"\""+ " AND "+filterField+"\""+filterValue+"\"" +" AND "+ afilterField+"\""+availabilityFilterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(hints))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"Hints\""+ " AND "+filterField+"\""+filterValue+"\"" +" AND "+ afilterField+"\""+availabilityFilterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(topic))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"Topic\""+ " AND "+filterField+"\""+filterValue+"\"" +" AND "+ afilterField+"\""+availabilityFilterValue+"\"");
			query.setParam("fl", resultFields);
		}
		
		query = appendFacetQuery(query, facetParams);
		
		return query;
	}
	
	
	public static SolrQuery constructQuery(String queryString, String objectType, List<String> listVal, String[] resultFields,List<String> queryFields, String[] facetParams,
			String filter, String filterValue)
	{
		SolrQuery query = new SolrQuery();
		
		
		String author = listVal.get(0);
		String organization = listVal.get(1);
		String place = listVal.get(2);
		String person = listVal.get(3);
		String document = listVal.get(4);
		String careact_directive = listVal.get(5);
		String careact_summary = listVal.get(6);
		String keyword = listVal.get(7);
		String hints = listVal.get(8);
		String topic = listVal.get(9);
		
		String filterField =getFilterField(filter);
		
		query.setParam("q", "*:*");
		
		String docType = queryFields.get(5);
		if(objectType.equals(author))
		{
			query.setParam("fq", queryFields.get(0)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\""+ " AND "+filterField+"\""+filterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(organization))
		{
			query.setParam("fq", queryFields.get(1)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\""+ " AND "+filterField+"\""+filterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(place))
		{
			query.setParam("fq", queryFields.get(2)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\""+ " AND "+filterField+"\""+filterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(person))
		{
			query.setParam("fq", queryFields.get(3)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\""+ " AND "+filterField+"\""+filterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(document))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"FullBody\""+ " AND "+filterField+"\""+filterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(careact_directive))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"CareAct_Directive\""+ " AND "+filterField+"\""+filterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(careact_summary))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"CareAct_Summary\""+ " AND "+filterField+"\""+filterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(keyword))
		{
			query.setParam("fq", queryFields.get(6)+":"+"\""+queryString+"\""+ " AND "+filterField+"\""+filterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(hints))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"Hints\""+ " AND "+filterField+"\""+filterValue+"\"");
			query.setParam("fl", resultFields);
		}
		else if(objectType.equals(topic))
		{
			query.setParam("fq", queryFields.get(4)+":"+"\""+queryString+"\""+ " AND "+docType+":"+"\"Topic\""+ " AND "+filterField+"\""+filterValue+"\"");
			query.setParam("fl", resultFields);
		}
		
		query = appendFacetQuery(query, facetParams);
		
		return query;
	}
	
	public static SolrQuery appendFacetQuery(SolrQuery query, String[] facetParams)
	{
		
		if(query!=null)
		{
			query.setFacet(true);
			query.setFacetMinCount(1);
			query.addFacetField(facetParams);
		}
		return query;
	}
}
