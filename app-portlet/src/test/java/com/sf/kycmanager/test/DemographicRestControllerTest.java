//package com.sf.kycmanager.test;
//
//import com.sf.biocapture.entity.Demographic;
//import com.sf.kycmanager.core.base.service.PortletService;
//import com.sf.kycmanager.core.base.service.RenderService;
//import com.sf.kycmanager.core.base.util.Integration;
//import com.sf.kycmanager.portlet.controller.biodata.DemographicsRestController;
//import com.sf.kycmanager.portlet.service.biodata.DemographicsService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.experimental.categories.Category;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.mock.web.portlet.MockRenderResponse;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.doAnswer;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@Category(Integration.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = DemographicRestControllerTest.Config.class)
//public class DemographicRestControllerTest {
//
//    @Autowired
//    protected PortletService portletService;
//    @Autowired
//    private DemographicsService demographicsService;
//    @Autowired
//    private WebApplicationContext ctx;
//
//    private MockMvc mockMvc;
//
//    private Page<Demographic> demographics;
//
//    @Before
//    public void setUp() {
//        mockMvc = webAppContextSetup(ctx).build();
//        Locale.setDefault(Config.LOCALE);
//
//        when(demographicsService.getAllDemographics(new PageRequest(0,10)))
//                .thenReturn(demographics);
//    }
//
//    @Test
//    public void restGet() throws Exception {
//
//        ResultActions result = mockMvc.perform(get("/test/" + DemographicsRestController.REST_RESOURCE));
//        result.andExpect(status().isOk())
//                .andExpect(content().contentType(Integration.JSON_UTF_8_CONTENT_TYPE))
//                .andExpect(jsonPath("$", hasSize(demographicsService.getAllDemographics(new PageRequest(0,10)).getSize())))
//                .andReturn();
//
//        assertThat(result.andReturn().getResponse().getContentAsString()).isNotEmpty();
//    }
//
////    @Test
////    public void restPost() throws Exception {
////
////        final Demographic demographic = new Demographic();
////        demographic.setFirstName("Cyprian");
////        demographic.setSurname("Omenuko");
////
////        doAnswer(new Answer<Void>() {
////            @Override
////            public Void answer(InvocationOnMock invocation) throws Throwable {
////                demographics.add((Demographic) invocation.getArguments()[0]);
////                return null;
////            }
////        }).when(demographicsService).addPerson((Person) anyObject());
////
////
////        ResultActions result = mockMvc.perform(post("/test/" + DemographicsRestController.REST_RESOURCE)
////                .contentType(Integration.JSON_UTF_8_CONTENT_TYPE)
////                .content(Integration.convertObjectToJsonBytes(demographic)));
////        result.andExpect(status().isOk())
////                .andExpect(status().is(200));
////
////        restGet();
////    }
//
//    @Test
//    public void restSearch() throws Exception {
//
//        final Demographic demographic = new Demographic();
////        demographic.setFirstName("Cyprian");
////        demographic.setSurname("Omenuko");
//        demographic.setFirstName("stanley");
//
//        doAnswer(new Answer<Void>() {
//            @Override
//            public Void answer(InvocationOnMock invocation) throws Throwable {
//                demographicsService.search("stanley",new PageRequest(0,2));
//                return null;
//            }
//        }).when(demographicsService).search(("stanley"),new PageRequest(0,2));
//
//
//        ResultActions result = mockMvc.perform(get("/test/" + DemographicsRestController.REST_SEARCH)
//                .contentType(Integration.JSON_UTF_8_CONTENT_TYPE)
//                .content(Integration.convertObjectToJsonBytes(demographic.getFirstName())));
//        result.andExpect(status().isOk())
//                .andExpect(status().is(200));
//
//        restGet();
//    }
//
//
//    @EnableWebMvc
//    @Configuration
//    public static class Config {
//
//        public static Locale LOCALE = Locale.GERMAN;
//
//        @Bean
//        public DemographicsService demographicsService() {
//
//            DemographicsService mock = Mockito.mock(DemographicsService.class);
//            return mock;
//        }
//
//        @Bean
//        public PortletService portletService() {
//            PortletService mock = Mockito.mock(PortletService.class);
//            when(mock.getRenderResponse()).thenReturn(new MockRenderResponse());
//            when(mock.getLocale()).thenReturn(LOCALE);
//            return mock;
//        }
//
//        @Bean
//        public RenderService RenderService() {
//            RenderService mock = Mockito.mock(RenderService.class);
//            return mock;
//        }
//
//        @Bean
//        public TestWrapperDemographicRestController testPersonRestController(DemographicsService demographicsService, RenderService renderService) {
//            return new TestWrapperDemographicRestController(demographicsService, renderService);
//        }
//    }
//}