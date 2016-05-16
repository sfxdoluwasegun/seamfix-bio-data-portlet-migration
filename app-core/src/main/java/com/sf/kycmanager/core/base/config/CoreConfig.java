package com.sf.kycmanager.core.base.config;

import com.sf.kycmanager.core.base.adapter.PortletRequestBodyImpl;
import com.sf.kycmanager.core.base.adapter.PortletRequestBodyWebArgumentResolver;
import com.sf.kycmanager.core.base.adapter.PortletResponseBodyImpl;
import com.sf.kycmanager.core.base.component.request.RequestBodyCache;
import com.sf.kycmanager.core.base.handler.MappingExceptionResolver;
import com.sf.kycmanager.core.base.service.PortletService;
import com.sf.kycmanager.core.base.service.impl.I18nMessageSourceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.portlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@Import(ThymeleafConfig.class)
//"com.sf.kycmanager.core.base.controller"
@ComponentScan(basePackages = {"com.sf.kycmanager.core.base.component",
        "com.sf.kycmanager.core.base.service"})
@EnableWebMvc
public class CoreConfig {

    private static final String ERROR_VIEW = "error/error";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MappingExceptionResolver mappingExceptionResolver(PortletService portletService) {

        MappingExceptionResolver mappingExceptionResolver = new MappingExceptionResolver(portletService);
        mappingExceptionResolver.setDefaultErrorView(ERROR_VIEW);
        return mappingExceptionResolver;
    }

    @Bean
    public AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter(PortletRequestBodyWebArgumentResolver portletRequestBody) {

        AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter = new AnnotationMethodHandlerAdapter();
        annotationMethodHandlerAdapter.setCustomArgumentResolver(portletRequestBody);
        annotationMethodHandlerAdapter.setCustomModelAndViewResolver(new PortletResponseBodyImpl());
        annotationMethodHandlerAdapter.setOrder(0);

        return annotationMethodHandlerAdapter;
    }

    @Bean
    public PortletRequestBodyWebArgumentResolver portletRequestBody(RequestBodyCache requestBodyCache) {
        return new PortletRequestBodyImpl(requestBodyCache);
    }

    @Bean
    public MessageSource messageSource(final PortletService portletService) {
        return new I18nMessageSourceImpl(portletService);
    }
}