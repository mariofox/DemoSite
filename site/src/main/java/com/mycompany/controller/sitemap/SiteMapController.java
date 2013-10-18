package com.mycompany.controller.sitemap;

import org.broadleafcommerce.common.sitemap.controller.BroadleafSiteMapController;
import org.broadleafcommerce.common.sitemap.exception.SiteMapException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Controller to generate and retrieve site map files.
 * 
 * @author Joshua Skorton (jskorton)
 */
@Controller
public class SiteMapController extends BroadleafSiteMapController {

    /**
     * Generates site map
     * 
     * @param request
     * @param response
     * @param model
     * @return
     * @throws SiteMapException
     * @throws IOException
     */
    @RequestMapping("/generate")
    @ResponseBody
    public String generateSiteMap(HttpServletRequest request, HttpServletResponse response, Model model) throws SiteMapException, IOException {
        return super.generateSiteMap(request, response, model);
    }

    /**
     * Retrieves a site map index file in XML format
     * 
     * @param request
     * @param response
     * @param model
     * @param fileName
     * @return
     */
    @RequestMapping("/retrieve/{file_name:.+\\.xml}")
    @ResponseBody
    public FileSystemResource retrieveSiteMapIndex(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable(value = "file_name") String fileName) {
        return super.retrieveSiteMapIndex(request, response, model, fileName);
    }

    /**
     * Retrieves a site map file in gzip format
     * 
     * @param request
     * @param response
     * @param model
     * @param fileName
     * @return
     */
    @RequestMapping("/retrieve/{file_name:.+\\.gz}")
    public void retrieveSiteMap(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable(value = "file_name") String fileName) {
        super.retrieveSiteMap(request, response, model, fileName);
    }

}
