package org.pcg.bucks_ss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.pcg.bucks_ss.solr.utils.QueryParameters;
import org.pcg.bucks_ss.solr.utils.SolrQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

@Controller
@RequestMapping("/fileDownload")
public class DocumentLinkController implements ServletContextAware
{
	@Autowired
	SolrQueryUtils solrQueryUtils;
	
	private ServletContext servletContext;
	
	private static final int BUFFER_SIZE = 4096;
	
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
	 * @param documentName
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(method=RequestMethod.GET) //@RequestMapping(method=RequestMethod.GET, value="/{file}")
	public @ResponseBody void getDocument(@RequestParam String documentName, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		QueryParameters qp = solrQueryUtils.getQueryParameters();
		
		String filePath = qp.getQuery().get("serverFile").get(0);
		
		if(StringUtils.isNotBlank(documentName))
		{
			filePath = filePath+documentName;
		}
		
       // String appPath = servletContext.getRealPath("");
                     
       // String fullPath = appPath + filePath;      
        
		String appath = qp.getQuery().get("serverFile").get(1);
       // String appath = "C:/Users/Administrator/Desktop";
        String fullPath = appath + filePath; 
                
        File downloadFile = new File(fullPath);
        
        FileInputStream inputStream = new FileInputStream(downloadFile);
                
        String mimeType = servletContext.getMimeType(fullPath);

        if (mimeType == null) 
        {
           
            mimeType = "application/octet-stream";
        }
                  
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
         
        while ((bytesRead = inputStream.read(buffer)) != -1) 
        {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
	}
	
	
	@Override
	public void setServletContext(ServletContext servletContext) 
	{
	
		this.servletContext = servletContext;
	}
	
	
}
