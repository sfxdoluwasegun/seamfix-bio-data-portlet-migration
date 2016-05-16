package com.sf.kycmanager.portlet.config;

import com.sf.kycmanager.core.portlet.config.PortletCoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PortletCoreConfig.class)
@ComponentScan(basePackages = {"com.sf.kycmanager.portlet.service", "com.sf.kycmanager.portlet.controller.biodata"})
public class PortletConfig {

}