package com.cts.service;

import java.util.List;
import com.cts.model.Item;

/**
 * 
 * ItemService
 *
 */
public interface ItemService {
	public void addItem(Item item);
    public Item findItemByName(String itemName);
    public List<Item> getAllItems();
    public boolean isItemExists(String itemName);

}
