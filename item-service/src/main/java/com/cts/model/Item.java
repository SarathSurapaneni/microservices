package com.cts.model;

import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * Item Model
 *
 */

@ApiModel(description = "All details about the Item. ")
public class Item {

    @ApiModelProperty(notes = "The database generated Item ID")
	private long itemId;
    
    @ApiModelProperty(notes = "The item name")
    @NotEmpty(message = "item name must not be empty")
	private String itemName;
    
    @ApiModelProperty(notes = "The item description")
    @NotEmpty(message = "description must not be empty")
	private String description;
    
    @ApiModelProperty(notes = "The item price")
    @NotEmpty(message = "price must not be empty")
	private long price;
    
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
}
