package com.mycompany.controller.sitemap;

import org.broadleafcommerce.common.sitemap.controller.BroadleafSiteMapController;
import org.broadleafcommerce.common.sitemap.exception.SiteMapException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Controller to generate and retrieve site map files.
 * 
 * @author Joshua Skorton (jskorton)
 */
@Controller
@RequestMapping("/sitemap")
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
    public String generateSiteMap(HttpServletRequest request, HttpServletResponse response, Model model) throws SiteMapException, IOException {
        return super.generateSiteMap(request, response, model);
    }

    /**
     * Retrieves site map
     * 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/retrieve")
    public String retrieveSiteMap(HttpServletRequest request, HttpServletResponse response, Model model) {
        return super.retrieveSiteMap(request, response, model);
    }

}
