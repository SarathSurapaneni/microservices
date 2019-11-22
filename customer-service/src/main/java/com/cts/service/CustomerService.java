package com.cts.service;

import java.util.List;
import com.cts.model.Customer;

/**
 * 
 * CustomerService
 * 
 *
 */
public interface CustomerService {
	public long addCustomer(Customer customer);
    public List<Customer> getAllCustomers();
    public boolean isCustomerExists(String email);

}
