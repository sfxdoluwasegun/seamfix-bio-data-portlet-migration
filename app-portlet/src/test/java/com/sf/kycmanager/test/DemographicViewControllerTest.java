//package com.sf.kycmanager.test;
//
//import com.sf.biocapture.entity.Demographic;
//import com.sf.kycmanager.core.base.config.ThymeleafConfig;
//import com.sf.kycmanager.core.base.service.I18nMessageConstants;
//import com.sf.kycmanager.core.base.service.MessageService;
//import com.sf.kycmanager.core.base.service.PortletService;
//import com.sf.kycmanager.core.base.util.Integration;
//import com.sf.kycmanager.portlet.service.biodata.DemographicsService;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.experimental.categories.Category;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.context.support.ResourceBundleMessageSource;
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
//import javax.portlet.WindowState;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@Category(Integration.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = DemographicViewControllerTest.Config.class)
//public class DemographicViewControllerTest {
//
//    @Autowired
//    private DemographicsService demographicsService;
//
//    @Autowired
//    private PortletService portletService;
//
//    @Autowired
//    private WebApplicationContext ctx;
//
//    private MockMvc mockMvc;
//
//    private List<Demographic> demographics;
//
//    @Before
//    public void setUp() {
//        mockMvc = webAppContextSetup(ctx).build();
//        Locale.setDefault(Config.LOCALE);
//    }
//
//    @Test
//    public void viewIndex() throws Exception {
//
//        when(portletService.getWindowStateExclusive()).thenReturn(WindowState.NORMAL);
//
//        demographics = new ArrayList<Demographic>();
//        Demographic d1 = new Demographic();
//        d1.setId(1l);
//        d1.setFirstName("Sample1");
//        d1.setSurname("sampe sur2");
//        Demographic d2 = new Demographic();
//        d2.setId(2l);
//        d2.setFirstName("Sample2");
//        d2.setSurname("sampe sur2");
//        demographics.add(d1);
//        demographics.add(d2);
//        when(demographicsService.getAllDemographics(new PageRequest(0,2))).thenReturn(demographicsService.getAllDemographics(new PageRequest(0,2)));
//
//        ResultActions result = mockMvc.perform(get("/test/view").locale(Locale.getDefault()));
//        result.andExpect(status().isOk())
//                .andExpect(view().name("index/index"))
//                .andExpect(model().attributeExists("demographics"))
//                .andExpect(model().attributeExists("resourceURL"));
//
//        String contentAsString = result.andReturn().getResponse().getContentAsString();
//        assertThat(contentAsString).isNotEmpty();
//        assertFalse(contentAsString.contains(I18nMessageConstants.MISSING_PROPERTY_INDICATOR));
//
//        Document parse = Jsoup.parse(contentAsString);
//        Elements elements = parse.select("table#persons > tbody > tr");
//        Page<Demographic> serviceDemographics = demographicsService.getAllDemographics(new PageRequest(0,2));
//        assertEquals(elements.size(), serviceDemographics.getSize());
//        for (int i = 0; i < elements.size() || i < serviceDemographics.getSize(); i++) {
//            Element tr = elements.get(i);
//            Demographic demographic = serviceDemographics.getContent().get(i);
//            assertEquals(tr.children().get(0).text(), String.valueOf(demographic.getId()));
//            assertEquals(tr.children().get(1).text(), demographic.getFirstName());
//            assertEquals(tr.children().get(2).text(), demographic.getSurname());
//        }
//    }
//
//    @Import(ThymeleafConfig.class)
//    @EnableWebMvc
//    @Configuration
//    public static class Config {
//
//        private static final String STATIC_URL = "/test/img.png";
//        public static Locale LOCALE = Locale.GERMAN;
//
//        @Bean
//        public DemographicsService demographicsService() {
//            DemographicsService mock = Mockito.mock(DemographicsService.class);
//            return mock;
//        }
//
//        @Bean
//        public PortletService portletService() {
//            PortletService mock = Mockito.mock(PortletService.class);
//            when(mock.getRenderResponse()).thenReturn(new MockRenderResponse());
//            when(mock.getStaticContentUrl(any(String.class))).thenReturn(STATIC_URL);
//            when(mock.getLocale()).thenReturn(LOCALE);
//            return mock;
//        }
//
//        @Bean
//        public MessageService messageService() {
//            MessageService mock = Mockito.mock(MessageService.class);
//            return mock;
//        }
//
//        @Bean
//        public MessageSource messageSource(final PortletService portletService) {
//
//            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//            messageSource.setBasename(I18nMessageConstants.TEMPLATE_RESOURCE_BUNDLE_BASENAME);
//            messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
//            return messageSource;
//        }
//
//        @Bean
//        public TestWrapperDemographicViewController testPersonViewController(DemographicsService demographicsService, PortletService portletService, MessageService messageService) {
//            return new TestWrapperDemographicViewController(demographicsService, portletService, messageService);
//        }
//    }
//}