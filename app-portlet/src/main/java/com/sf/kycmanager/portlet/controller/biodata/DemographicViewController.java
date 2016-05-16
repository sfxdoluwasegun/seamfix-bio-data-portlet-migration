package com.sf.kycmanager.portlet.controller.biodata;//package com.sf.kycmanager.portlet.portlet.controller.biodata;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.sf.kycmanager.core.base.annotation.ViewController;
import com.sf.kycmanager.core.base.model.MessageType;
import com.sf.kycmanager.core.base.service.MessageService;
import com.sf.kycmanager.core.base.service.PortletService;
import com.sf.kycmanager.portlet.service.biodata.DemographicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;
import javax.portlet.WindowStateException;

/**
 * Created by Cyprian Omenuko on 4/15/2016.
 */
@ViewController
public class DemographicViewController {

    private static Log _log = LogFactoryUtil.getLog(DemographicViewController.class);

    private final DemographicsService demographicsService;
    private final PortletService portletService;
    private final MessageService messageService;

    @Autowired
    public DemographicViewController(DemographicsService demographicsService, PortletService portletService, MessageService messageService) {
        this.demographicsService = demographicsService;
        this.portletService = portletService;
        this.messageService = messageService;
    }

    @RenderMapping
    @RequestMapping
    public String view(ModelMap model) throws WindowStateException {
//
        _log.debug("handle view");
////
        RenderResponse response = portletService.getRenderResponse();

        ResourceURL resourceURL = response.createResourceURL();
        resourceURL.setResourceID(DemographicsRestController.REST_RESOURCE);
        model.addAttribute("resourceURL", resourceURL.toString());

        ResourceURL searchURL = response.createResourceURL();
        resourceURL.setResourceID(DemographicsRestController.REST_SEARCH);
        model.addAttribute("searchURL", searchURL.toString());

        ResourceURL detailURL = response.createResourceURL();
        detailURL.setResourceID(DemographicsRestController.REST_DETAILS);
        model.addAttribute("detailURL", detailURL.toString());

        ResourceURL imageUrl = response.createResourceURL();
        imageUrl.setResourceID(DemographicsRestController.REST_IMAGE);
        model.addAttribute("imageURL", imageUrl.toString());

        ResourceURL printsUrl = response.createResourceURL();
        printsUrl.setResourceID(DemographicsRestController.REST_PRINTS);
        model.addAttribute("printsURL", printsUrl.toString());

        messageService.addRequestMessage("index.info", MessageType.INFO);

        return "index/index";
    }


}