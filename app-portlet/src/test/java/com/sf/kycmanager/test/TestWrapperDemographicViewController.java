//package com.sf.kycmanager.test;
//
//import com.sf.kycmanager.core.base.service.MessageService;
//import com.sf.kycmanager.core.base.service.PortletService;
//import com.sf.kycmanager.portlet.controller.biodata.DemographicViewController;
//import com.sf.kycmanager.portlet.service.biodata.DemographicsService;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.portlet.WindowStateException;
//
//@RequestMapping("/test")
//public class TestWrapperDemographicViewController extends DemographicViewController {
//
//    public TestWrapperDemographicViewController(DemographicsService demographicsService, PortletService portletService, MessageService messageService) {
//        super(demographicsService, portletService, messageService);
//    }
//
//    @RequestMapping(value = "/view")
//    @Override
//    public String view(ModelMap model) throws WindowStateException {
//        return super.view(model);
//    }
//
//    @RequestMapping(value = "/render")
//    @Override
//    public String render(ModelMap model) {
//        return super.render(model);
//    }
//
//    @RequestMapping(value = "/action")
//    @Override
//    public void action() {
//        super.action();
//    }
//}