package com.vlabs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration//tells that this class contains configuration and can be configured using @import in WebAppConfig.java
public class RabbitMqConfig {

	final static String queueName="springboot";
	
	@Bean
	Queue queue(){
		return new Queue(queueName, true);
	}
	
	@Bean
	Binding binding(Queue queue,TopicExchange exchange){
		return new BindingBuilder().bind(queue).to(exchange).with(queueName);
	}
	
	@Bean
	  TopicExchange exchange() {
	    return new TopicExchange("spring-boot-exchange");
	  }
	
	 @Bean
	  public ConnectionFactory getConnectionFactory() {
	    ConnectionFactory connectionFactory = new CachingConnectionFactory("localhost", 5672);
	   
	    return connectionFactory;
	  }
	 
	/* @Bean
	  public RabbitTransactionManager getRabbitTransactionManager() {
	    RabbitTransactionManager transactionManager = new RabbitTransactionManager();
	    transactionManager.setConnectionFactory(getConnectionFactory());
	    return transactionManager;
	  }*/
	 
	 @Bean
	  public RabbitTemplate getRabbitTemplate() {
	    RabbitTemplate template = new RabbitTemplate();
	    
	    template.setConnectionFactory(getConnectionFactory());
	    template.setRoutingKey(queueName);
	    template.setQueue(queueName);
	    return template;
	  }
	 
	 @Bean
	  public RabbitAdmin getRabbitAdmin() {
	    RabbitAdmin admin = new RabbitAdmin(getConnectionFactory());
	    return admin;
	  }
  
}
