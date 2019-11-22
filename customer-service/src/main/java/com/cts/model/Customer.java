package com.cts.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * Customer Model
 *
 */

@ApiModel(description = "All details about the Customer. ")
public class Customer {
	
    @ApiModelProperty(notes = "The database generated Customer ID")
	private long customerId;
	
    @ApiModelProperty(notes = "The customer email ID")
	@NotEmpty(message = "email must not be empty")
	@Email(message = "email should be a valid email")
	private String email;
	
    @ApiModelProperty(notes = "The customer first name")
    @NotEmpty(message = "first name must not be empty")
	private String  firstName;
	
    @ApiModelProperty(notes = "The customer last name")
    @NotEmpty(message = "last name must not be empty")
	private String lastName;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
