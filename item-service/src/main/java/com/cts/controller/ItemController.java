package com.cts.controller;

import static com.cts.controller.APIConstants.ADD_ITEM;
import static com.cts.controller.APIConstants.GET_ITEMS;
import static com.cts.controller.APIConstants.GET_ITEM_BY_NAME;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cts.exception.ItemFoundException;
import com.cts.model.Item;
import com.cts.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;;


@Api(value = "ItemController", description = "REST APIs related to Item Service!!!!")
@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@ApiOperation(value = "Create new Item")
	@PostMapping(value = ADD_ITEM,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> addItem(@RequestBody Item item)
	{
		if(itemService.isItemExists(item.getItemName())){
			throw new ItemFoundException("Item with name:"+item.getItemName() +" already exists!");
		}
		itemService.addItem(item);
	    return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get list of Items", response = Iterable.class, tags = GET_ITEMS)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	 
	@GetMapping(GET_ITEMS)
	public ResponseEntity<List<Item>> getAllItems() {
		List<Item> list = itemService.getAllItems();
		return new ResponseEntity<List<Item>>(list, HttpStatus.OK);
	}
	
	//Get url: http://port/service2/items/{itemname}
	@ApiOperation(value = "get Item by Item name")
	@GetMapping(GET_ITEM_BY_NAME)
	public ResponseEntity<Item> getItemByItemName(@PathVariable("itemname") String itemName) {
		Item item = itemService.findItemByName(itemName);
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}

}