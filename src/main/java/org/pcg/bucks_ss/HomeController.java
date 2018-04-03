package org.pcg.bucks_ss;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.pcg.bucks_ss.beans.HomePageBean;
import org.pcg.bucks_ss.forms.BasicForm;
import org.pcg.bucks_ss.solr.utils.SolrQueryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	SolrQueryUtils solrQueryUtils;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		List<HomePageBean> hpb = solrQueryUtils.getMostRecentUploads();
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute(new BasicForm());
	
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute( "recentlyUploaded", hpb);
		return "home";
	}
	
}
