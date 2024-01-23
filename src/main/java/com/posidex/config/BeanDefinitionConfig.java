package com.posidex.config;

import java.util.logging.Logger;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.posidex.util.StringEncrypter;
import com.posidex.util.StringEncrypter.EncryptionException;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class BeanDefinitionConfig {

	@Autowired
	private Environment environment;

	private static Logger logger = Logger.getLogger(BeanDefinitionConfig.class.getName());

	@Bean
	public BasicDataSource getDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		basicDataSource.setUrl(environment.getProperty("spring.datasource.url"));
		basicDataSource.setUsername(environment.getProperty("spring.datasource.username"));
		basicDataSource.setPassword(environment.getProperty("spring.datasource.password"));
		try {
			basicDataSource.setPassword(StringEncrypter.decrypt(environment.getProperty("spring.datasource.password")));
		} catch (EncryptionException e) {
			logger.info("Password already decrypted");
		}
		if (environment.getProperty("enableAutocommit") == null) {
			basicDataSource.setDefaultAutoCommit(false);
		} else {
			basicDataSource.setDefaultAutoCommit(Boolean.parseBoolean(environment.getProperty("enableAutocommit")));
		}
//		basicDataSource.setMaxTotal(Integer.parseInt(environment.getProperty("maxTotalTime")));
//		basicDataSource.setMaxIdle(environment.getProperty("maxIdleTime"));
		return basicDataSource;
	}

}
