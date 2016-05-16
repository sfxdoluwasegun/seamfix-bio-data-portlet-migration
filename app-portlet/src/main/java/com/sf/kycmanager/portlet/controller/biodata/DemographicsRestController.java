package com.sf.kycmanager.portlet.controller.biodata;//package com.sf.kycmanager.portlet.portlet.controller.biodata;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.sf.biocapture.entity.Demographic;
import com.sf.kycmanager.core.base.annotation.PortletResponseBody;
import com.sf.kycmanager.core.base.annotation.ViewController;
import com.sf.kycmanager.core.base.service.RenderService;
import com.sf.kycmanager.portlet.service.biodata.DemographicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by Cyprian Omenuko on 4/15/2016.
 */
@ViewController
public class DemographicsRestController {
    //
    public static final String REST_RESOURCE = "demographics";
    public static final String REST_SEARCH = REST_RESOURCE + "/" + "search";
    public static final String REST_DETAILS = REST_RESOURCE + "/" + "details";
    public static final String REST_IMAGE = REST_RESOURCE + "/" + "image";
    public static final String REST_PRINTS = REST_RESOURCE + "/" + "prints";
    public static final String HTML_JSON_RESOURCE = "htmljson";
    private static Log _log = LogFactoryUtil.getLog(DemographicsRestController.class);
    private final RenderService renderService;
    private final DemographicsService demographicsService;

    @Autowired
    public DemographicsRestController(DemographicsService demographicsService, RenderService renderService) {
        this.demographicsService = demographicsService;
        this.renderService = renderService;
    }

    @ResourceMapping(value = REST_RESOURCE)
    @RequestMapping(method = RequestMethod.GET)
    @PortletResponseBody
    public Page<Demographic> listDemographics(@RequestParam(value = "page", defaultValue = "0") Long page,
                                              @RequestParam(value = "size", defaultValue = "10") Long size,
                                              @RequestParam(value = "search", required = false) String search) {
        Pageable pageable = new PageRequest(page.intValue(), size.intValue());
        Page<Demographic> pageList;
        if (search != null) {
            pageList = demographicsService.search(search, pageable);
        } else {
            pageList = demographicsService.getAllDemographics(pageable);
//        _log.debug(new Gson().toJson(pageList));
        }
        return pageList;
    }

    @Deprecated
    @ResourceMapping(value = REST_SEARCH)
    @RequestMapping(method = RequestMethod.GET)
    @PortletResponseBody
    public Page<Demographic> searchDemographics(@RequestParam(value = "page", defaultValue = "0") Long page,
                                                @RequestParam(value = "size", defaultValue = "10") Long size,
                                                @RequestParam(value = "search") String search) {
        Pageable searchedPageable = new PageRequest(page.intValue(), size.intValue());
        Page<Demographic> demographicPage = demographicsService.search(search, searchedPageable);
        _log.debug(demographicPage);
        return demographicPage;
    }

    @ResourceMapping(value = REST_DETAILS)
    @RequestMapping(method = RequestMethod.GET)
    @PortletResponseBody
    public Demographic getDemographicInfoById(@RequestParam("id") Long id) {
        return demographicsService.getDemographicById(id).get();
    }

    @ResourceMapping(value = REST_IMAGE)
    @RequestMapping(method = RequestMethod.GET)
    @PortletResponseBody
    public String getDemographicImage(@RequestParam("id") Long id) {
        return demographicsService.getDemographicImage(id).get();
    }

    @ResourceMapping(value = REST_PRINTS)
    @RequestMapping(method = RequestMethod.GET)
    @PortletResponseBody
    public List<Map<String, String>> getDemographicPrints(@RequestParam("id") Long id) {
        return demographicsService.getDemographicPrints(id);
    }
}