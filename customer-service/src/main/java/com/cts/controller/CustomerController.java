package com.cts.controller;

import static com.cts.controller.APIConstants.ADD_CUSTOMER;
import static com.cts.controller.APIConstants.GET_CUSTOMERS;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cts.exception.DuplicateEmailException;
import com.cts.model.Customer;
import com.cts.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;; 

@RestController
@Api(value = "CustomerController", description = "REST APIs related to Customer Service!!!!")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value = "Create new Customer")
	@PostMapping(value = ADD_CUSTOMER,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		
		if(customerService.isCustomerExists(customer.getEmail())){
			throw new DuplicateEmailException("Customer with email:"+customer.getEmail() +" already exists!");
		}
		customerService.addCustomer(customer);
	    return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get list of Customers", response = Iterable.class, tags = GET_CUSTOMERS)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	 
	@GetMapping(GET_CUSTOMERS)
	public ResponseEntity<List<Customer>> getAllCustomer() {
		List<Customer> list = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}
		
}
