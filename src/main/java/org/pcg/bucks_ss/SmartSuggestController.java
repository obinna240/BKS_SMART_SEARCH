package org.pcg.bucks_ss;

import org.pcg.bucks_ss.solr.utils.SolrQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/smartSuggest")
public class SmartSuggestController 
{
	@Autowired
	SolrQueryUtils solrQueryUtils;
	
	public SolrQueryUtils getSolrQueryUtils() {
		return solrQueryUtils;
	}

	public void setSolrQueryUtils(SolrQueryUtils solrQueryUtils) {
		this.solrQueryUtils = solrQueryUtils;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getSmartSuggestions(@RequestParam String q)
	{
		
		String smartSuggetResponse = solrQueryUtils.getSmartSuggest(q);
			
		return smartSuggetResponse;
	} 

}
