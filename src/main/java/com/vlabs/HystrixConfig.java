package com.vlabs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.vlabs.utility.MockStreamServlet;
//@Configuration//tells that this class contains configuration and can be configured using @import in WebAppConfig.java
public class HystrixConfig {
	@Autowired
	HystrixProperties hystrixProperties;

	@Bean
	public ServletRegistrationBean mockStreamServlet() {
		return new ServletRegistrationBean(new MockStreamServlet(),
				"/mock.stream");
	}

	@Bean
	@ConditionalOnClass(HystrixCommandAspect.class)
	public HystrixCommandAspect hystrixCommandAspect() {
		return new HystrixCommandAspect();
	}

	@Bean
	@ConditionalOnClass(HystrixMetricsStreamServlet.class)
	@ConditionalOnExpression("${hystrix.streamEnabled:false}")
	public ServletRegistrationBean hystrixStreamServlet() {
		return new ServletRegistrationBean(new HystrixMetricsStreamServlet(),
				hystrixProperties.streamUrl);
	}
}

@ConfigurationProperties(prefix = "hystrix", ignoreUnknownFields = true)
class HystrixProperties {
	boolean enabled = true;
	boolean streamEnabled = false;
	String streamUrl = "/hystrix.stream";
}
