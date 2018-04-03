package org.pcg.bucks_ss;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
//import org.apache.solr.client.solrj.response.QueryResponse;
import org.pcg.bucks_ss.beans.PaginatorBean;
import org.pcg.bucks_ss.beans.SearchResultBean;
import org.pcg.bucks_ss.forms.BasicForm;
import org.pcg.bucks_ss.solr.utils.PaginationUtils;
import org.pcg.bucks_ss.solr.utils.SolrQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController 
{
	@Autowired
	SolrQueryUtils solrQueryUtils;
	
	public SolrQueryUtils getSolrQueryUtils()
	{
		return solrQueryUtils;
	}

	public void setSolrQueryUtils(SolrQueryUtils solrQueryUtils)
	{
		this.solrQueryUtils = solrQueryUtils;
	}
	
	/**
	 * 
	 * @param basicForm
	 * @param pValue
	 * @param model
	 * @return String
	 */
	@RequestMapping(value="/basic", method=RequestMethod.GET)
	public String searchAnything(@ModelAttribute BasicForm basicForm, Integer pValue, Model model)
	{
		String basicquery = basicForm.getBasicquery();
		
		String basicqueryType = basicForm.getBasicqueryType();
		
		Boolean basicsortby = basicForm.getBasicsortBy();
		
		
		basicForm = new BasicForm();
		basicForm.setBasicsortBy(basicsortby);
		basicForm.setBasicquery(basicquery);
		model.addAttribute("basicForm",basicForm);
		
		PaginatorBean paginatorBean = null;
		if(pValue==null)
		{
			paginatorBean = PaginationUtils.checkPagination(null);
		}
		else
		{
			paginatorBean = PaginationUtils.checkPagination(pValue);
			//model.addAttribute("paginatorType",0);
		}
		
		model.addAttribute("paginatorType",new Integer(0));
		
		Map<String,Object> qParamStartRow = PaginationUtils.getQueryParams(paginatorBean);
		
		SearchResultBean searchResultBean = solrQueryUtils.search(basicquery, basicqueryType, basicsortby, qParamStartRow);
		
		model.addAttribute("searchResultBean", searchResultBean);
		
		return "result";
	}
	
	/**
	 * 
	 * @param basicForm
	 * @param pValue
	 * @param filter
	 * @param model
	 * @return String
	 */
	public String searchAnything(BasicForm basicForm, Integer pValue, String filter,String filterValue, Integer paginatorType, Model model)
	{
		String basicquery = basicForm.getBasicquery();
		
		String basicqueryType = basicForm.getBasicqueryType();
		
		Boolean basicsortby = basicForm.getBasicsortBy();
		
		
		basicForm = new BasicForm();
		basicForm.setBasicsortBy(basicsortby);
		basicForm.setBasicquery(basicquery);
		model.addAttribute("basicForm",basicForm);
		
		if(filter.equals("Availability"))
		{
			model.addAttribute("availabilityFilterValue", filterValue);
			model.addAttribute("availabilityFilterType", filter);
			
		}
		else
		{
			model.addAttribute("filterValue", filterValue);
			model.addAttribute("filterType", filter);
		
		}
		
		PaginatorBean paginatorBean = null;
		if(pValue==null)
		{
			paginatorBean = PaginationUtils.checkPagination(null);
		}
		else
		{
			paginatorBean = PaginationUtils.checkPagination(pValue);
			
			
		}
		model.addAttribute("paginatorType", paginatorType);
		Map<String,Object> qParamStartRow = PaginationUtils.getQueryParams(paginatorBean);
		
		SearchResultBean searchResultBean = solrQueryUtils.search(basicquery, basicqueryType, basicsortby, qParamStartRow, filter, filterValue);
		
		model.addAttribute("searchResultBean", searchResultBean);
		
		return "result";
	}
	
	/**
	 * 
	 * @param basicquery
	 * @param basicqueryType
	 * @param basicsortby
	 * @param pValue
	 * @param model
	 * @return String
	 */
	@RequestMapping( method=RequestMethod.GET)
	public String searchAnything(@RequestParam String basicquery, @RequestParam(required=false) String basicqueryType, @RequestParam(required=false) Boolean basicsortBy, @RequestParam(required=false) Integer pValue, Model model)
	{
		BasicForm bf = new BasicForm();
		bf.setBasicquery(basicquery);
		bf.setBasicqueryType(basicqueryType);
		bf.setBasicsortBy(basicsortBy);
		model.addAttribute("qt",basicqueryType);
		String result = searchAnything(bf, pValue,model);
		return result;
	}
	
	
	
	/**
	 * 
	 * @param basicquery
	 * @param basicqueryType
	 * @param basicsortBy
	 * @param pValue
	 * @param substitute
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{substitute}/{pp}", method=RequestMethod.GET)
	public String search(@RequestParam String basicquery, @RequestParam(required=false) String basicqueryType, 
			@RequestParam(required=false) Boolean basicsortBy, 
			@RequestParam(required=false) Integer pValue, @RequestParam(required=false) String f,
			@PathVariable String substitute, @PathVariable String pp, Model model)
	{
		String result = null;
		BasicForm bf = new BasicForm();
		bf.setBasicquery(basicquery);
		bf.setBasicqueryType(basicqueryType);
		bf.setBasicsortBy(basicsortBy);
		model.addAttribute("qt",basicqueryType);
		
		model.addAttribute("f",f );
		model.addAttribute("substitute",substitute );
		
		if(pp.equals("0")||pp.equals("1"))
		{
			Integer pptype = Integer.parseInt(pp);
			result = searchAnything(bf, pValue,substitute,f,pptype,model);
		}
		else if(pp.equals("3"))
		{
			result = searchAnything(bf, pValue,substitute,f,0,model);
		}
		return result;
	}
	
	/**
	 * 
	 * @param basicquery
	 * @param basicqueryType
	 * @param basicsortBy
	 * @param pValue
	 * @param availabilityFilter
	 * @param filterType
	 * @param filterValue
	 * @param pp
	 * @param model
	 * @return String result
	 */
	@RequestMapping(value="/Availability/ex/{pp}", method=RequestMethod.GET)
	public String search(@RequestParam String basicquery, 
			@RequestParam(required=false) String basicqueryType, 
			@RequestParam(required=false) Boolean basicsortBy, 
			@RequestParam(required=false) Integer pValue, 
			@RequestParam(required=true) String availabilityFilter,
			@RequestParam(required=false) String filterType,
			@RequestParam(required=false) String filterValue,
			@PathVariable String pp, 
			Model model)
	{
		String result = null;
		BasicForm bf = new BasicForm();
		bf.setBasicquery(basicquery);
		bf.setBasicqueryType(basicqueryType);
		bf.setBasicsortBy(basicsortBy);
		
		model.addAttribute("qt",basicqueryType);
		model.addAttribute("availabilityFilter",availabilityFilter);
		model.addAttribute("substitute","Availability");
		
		if(pp.equals("0")||pp.equals("1"))
		{
			Integer pptype = Integer.parseInt(pp);
			if(StringUtils.isBlank(filterType))
			{
				result = searchAnything(bf, pValue,"Availability",availabilityFilter,pptype,model);
			}
			else 
			{
				result = searchAnything(bf, pValue,"Availability",availabilityFilter,pptype,filterType, filterValue,model);
			}
		}
		else if(pp.equals("3"))
		{
			if(StringUtils.isBlank(filterType))
			{
				result = searchAnything(bf, pValue,"Availability",availabilityFilter,0,model);
			}
			else
			{
				result = searchAnything(bf, pValue,"Availability",availabilityFilter,0,filterType, filterValue,model);
			}
		}
		else if(pp.equals("2")) //pp equals 2 when text availability is selected
		{
			if(StringUtils.isBlank(filterType))
			{
				//result = searchAnything(bf, pValue,"Availability",availabilityFilter,1,model); //reverts to single filter search using pagination type 1
				result = searchAnything(bf, pValue,"Availability",availabilityFilter,2,model); //reverts to single filter search using pagination type 1
			}
			else
			{
				result = searchAnything(bf, pValue,"Availability",availabilityFilter,2,filterType, filterValue,model);
			}
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @param basicForm
	 * @param pValue
	 * @param availabilityFilterType
	 * @param availabilityFilter
	 * @param paginatorType
	 * @param filter
	 * @param filterValue
	 * @param model
	 * @return String
	 */
	public String searchAnything(BasicForm basicForm, Integer pValue, String availabilityFilterType, String availabilityFilter, Integer paginatorType, String filter,String filterValue,  Model model)
	{
		String basicquery = basicForm.getBasicquery();
		
		String basicqueryType = basicForm.getBasicqueryType();
		
		Boolean basicsortby = basicForm.getBasicsortBy();
		
		
		basicForm = new BasicForm();
		basicForm.setBasicsortBy(basicsortby);
		basicForm.setBasicquery(basicquery);
		model.addAttribute("basicForm",basicForm);
				
		model.addAttribute("availabilityFilterValue", availabilityFilter);
		model.addAttribute("availabilityFilterType", availabilityFilterType);
		
		model.addAttribute("filterValue", filterValue);
		model.addAttribute("filterType", filter);
		
		PaginatorBean paginatorBean = null;
		if(pValue==null)
		{
			paginatorBean = PaginationUtils.checkPagination(null);
		}
		else
		{
			paginatorBean = PaginationUtils.checkPagination(pValue);
			
			
		}
		model.addAttribute("paginatorType", paginatorType);
		Map<String,Object> qParamStartRow = PaginationUtils.getQueryParams(paginatorBean);
		
		SearchResultBean searchResultBean = solrQueryUtils.search(basicquery, basicqueryType, basicsortby, qParamStartRow, filter, filterValue, availabilityFilter, availabilityFilterType);
		
		model.addAttribute("searchResultBean", searchResultBean);
		
		return "result";
	}
	
	@RequestMapping(value="/removeFilter", method=RequestMethod.GET)
	public String removeFilter(@RequestParam String basicquery, 
			@RequestParam(required=false) String basicqueryType, 
			@RequestParam(required=false) Boolean basicsortBy, 
						Model model)
	{
		String result = null;
		BasicForm bf = new BasicForm();
		bf.setBasicquery(basicquery);
		bf.setBasicqueryType(basicqueryType);
		bf.setBasicsortBy(basicsortBy);
		result = searchAnything(bf, null, model);
		
		
		return result;
	}
	
	

}
