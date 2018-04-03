package org.pcg.bucks_ss;

import org.pcg.bucks_ss.solr.utils.SolrQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/advanced")
public class AdvancedSearchController 
{
	@Autowired
	SolrQueryUtils solrQueryUtils;
	
	/**
	 * 
	 * @return SolrQueryUtils
	 */
	public SolrQueryUtils getSolrQueryUtils()
	{
		return solrQueryUtils;
	}
	
	/**
	 * 
	 * @param solrQueryUtils
	 */
	public void setSolrQueryUtils(SolrQueryUtils solrQueryUtils)
	{
		this.solrQueryUtils = solrQueryUtils;
	}
	
	
}
