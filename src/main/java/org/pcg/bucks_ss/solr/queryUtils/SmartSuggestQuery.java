package org.pcg.bucks_ss.solr.queryUtils;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.pcg.bucks_ss.beans.AutoSearchResultBean;
import org.pcg.bucks_ss.beans.Header;


/**
 * 
 * @author obiPC
 *
 */
public class SmartSuggestQuery
{
	public Object getSmartSuggestions(QueryResponse response, int i)
	{
		
		List<Map<String, Object>> object = new LinkedList<Map<String, Object>>();
		//if(response.getResults().getNumFound()>0)
		//{
			
			GroupResponse gResponse = response.getGroupResponse();
					
			List<GroupCommand> gComm = gResponse.getValues();
				
			for(GroupCommand gCo:gComm)
			{
				String groupType = gCo.getName();
				List<Group> g = gCo.getValues();
				
				for(Group grp:g)
				{
					long nFound = grp.getResult().getNumFound(); //number of results found for this group
					Long num = nFound;
					if(nFound >0)
					{
						Map<String, Object> category = new HashMap<String, Object>();
						String grpVal = grp.getGroupValue(); //name of the group 
						SolrDocumentList solrList = grp.getResult();
						Header header = new Header();
					
						header.setLimit("15");
						header.setNum(num.toString());
						header.setTitle(grpVal);
						category.put("header", header);
					
						if(CollectionUtils.isNotEmpty(solrList))
						{
							List<AutoSearchResultBean> listAuto = new LinkedList<AutoSearchResultBean>();
							for(SolrDocument solrD:solrList)
							{
								AutoSearchResultBean smBeans = new AutoSearchResultBean();
								
								String value = (String) solrD.getFieldValue("entity");
								String stored = (String) solrD.getFieldValue("entity_stored");
								
							
								
								stored = StringEscapeUtils.escapeJavaScript(stored);
								
								
								String entityType = (String) solrD.getFieldValue("entityType");
								
								smBeans.setImage("");
								
								if(i==0)
								{
									
									smBeans.setOnclick("window.document.form.basicquery.value='"+stored+"';window.document.form.basicqueryType.value='"+entityType+"';return false;");
									
									
								}
								else if(i==1)
								{
									smBeans.setOnclick("window.document.advancedform.query.value='"+stored+"';window.document.advancedform.queryType.value='"+entityType+"';return false;");
									
								}
								smBeans.setPrimary(stored);
								
								smBeans.setSecondary(value);
							
								
								listAuto.add(smBeans);							
							}
							category.put("data", listAuto);
						}
						object.add(category);
					}
											
				}

			}
			
		//}
		
		return object;
	}
}


