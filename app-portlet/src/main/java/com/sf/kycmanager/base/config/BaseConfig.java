package com.sf.kycmanager.base.config;

import com.sf.kycmanager.base.dbconfig.DataBaseConfig;
import com.sf.kycmanager.core.base.config.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {CoreConfig.class, DataBaseConfig.class})
public class BaseConfig {
}
