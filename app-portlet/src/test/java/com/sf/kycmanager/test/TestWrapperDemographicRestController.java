//package com.sf.kycmanager.test;
//
//import com.sf.biocapture.entity.Demographic;
//import com.sf.kycmanager.core.base.service.RenderService;
//import com.sf.kycmanager.portlet.controller.biodata.DemographicsRestController;
//import com.sf.kycmanager.portlet.service.biodata.DemographicsService;
//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@RequestMapping("/test")
//public class TestWrapperDemographicRestController extends DemographicsRestController {
//
//
//    public TestWrapperDemographicRestController(DemographicsService demographicsService, RenderService renderService) {
//        super(demographicsService, renderService);
//    }
//
//    @RequestMapping(value = "/" + DemographicsRestController.REST_RESOURCE)
//    @ResponseBody
//    @Override
//    public Page<Demographic> listDemographics(Long page,Long size,String sort) {
//        return super.listDemographics(page,size,null);
//    }
//
//    @RequestMapping(value = "/" + DemographicsRestController.REST_SEARCH, method = RequestMethod.GET)
//    @Override
//    public Page<Demographic> searchDemographics(Long page, Long size, String search) {
//        return super.searchDemographics(page,size, search);
//    }
//}