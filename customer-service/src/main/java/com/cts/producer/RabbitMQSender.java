package com.cts.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.cts.model.Customer;


/**
 * 
 * RabbitMQ Producer
 * 
 *
 */
@Component
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${customer.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${customer.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(Customer customer) {
		amqpTemplate.convertAndSend(exchange, routingkey, customer);
		System.out.println("Send customer = " + customer);
		System.out.println("Send msg = " + customer.getEmail());
	    
	}
}
