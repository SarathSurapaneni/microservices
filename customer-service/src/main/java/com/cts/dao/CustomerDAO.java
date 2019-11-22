package com.cts.dao;

import java.util.List;
import com.cts.model.Customer;

/**
 * 
 * Customer DAO
 *
 */
public interface CustomerDAO {
	public long addCustomer(Customer customer);
    public List<Customer> getAllCustomers();
    public boolean isCustomerExists(String email);
    
}
