package com.cts.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.dao.CustomerDAO;
import com.cts.model.Customer;
import com.cts.producer.RabbitMQSender;

/**
 * 
 * CustomerService implementation
 * 
 *
 */

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	RabbitMQSender rabbitMQSender;

	@Override
	public long addCustomer(Customer customer) {
		long customerId=customerDAO.addCustomer(customer);
		customer.setCustomerId(customerId);
		
		//publish message to rabbitmq	
		rabbitMQSender.send(customer);
		return customerId;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}

	@Override
	public boolean isCustomerExists(String email) {
		return customerDAO.isCustomerExists(email);
	}

}
