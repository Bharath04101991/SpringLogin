package com.vlabs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration //tells that this class contains configuration and can be configured using @import in WebAppConfig.java
public class HandlerConfig extends WebMvcConfigurerAdapter {

	@Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    // Add any Spring MVC Interceptors here that you desire. These are
	    // like servlet filters but apply only to Spring MVC handled resources.
	    registry.addInterceptor(getRequestFilter());
	  }
	
	  @Bean
	  public RequestFilter getRequestFilter() {
	    return new RequestFilter();
	  }
	  
	 
}
