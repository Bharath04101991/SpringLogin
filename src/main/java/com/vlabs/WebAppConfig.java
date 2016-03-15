package com.vlabs;




import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.netflix.hystrix.HystrixCommandProperties;
/**
 * @author btodupunoori
 *
 */
@Configuration//tells that has configuration
@ComponentScan(basePackageClasses={WebAppConfig.class})//scans all the classes existed in package defined and also sub packages
//@EnableWebMvc
@Import({ControllerConfig.class,DbConfig.class,/*RabbitMqConfig.class,*/HandlerConfig.class/*,HystrixConfig.class*/})
@EnableAutoConfiguration//adding auto configuration for ex:AnnotationConfigWebApplicationContext no need to configure
@ConditionalOnExpression("${hystrix.enabled:true}")//it will check whether the expression is true or not before starting the application.
//@EnableConfigurationProperties(HystrixProperties.class)
@EnableHystrixDashboard
@EnableHystrix//by enabling hystrix and dashboard no need to configure hystrix config manually because of springboot
public class WebAppConfig /*extends SpringBootServletInitializer*/ {
     
	 @Bean
	  public ViewResolver getViewResolver() {
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setViewClass(JstlView.class);
	    resolver.setPrefix("/WEB-INF/pages/");
	    resolver.setSuffix(".jsp");
        
	    return resolver;
	  }
	 
	 /*@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	 return application.sources(WebAppConfig.class);
	 }
	 */

/*	 @Override
	  public void configureMessageConverters(
	      List<HttpMessageConverter<?>> converters) {
	    converters.add(new StringHttpMessageConverter());
	    converters.add(new Jaxb2RootElementHttpMessageConverter());
	    //converters.add(new MappingJackson2HttpMessageConverter());
	    
	    }*/
	 
	 
	 public static void main(String[] args) throws Exception {
		    HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(2000);
		    HystrixCommandProperties.Setter().withFallbackIsolationSemaphoreMaxConcurrentRequests(2000);
		    HystrixCommandProperties.Setter().withFallbackEnabled(true);
		    new SpringApplicationBuilder(WebAppConfig.class).web(true).run(args);
	 }
}
