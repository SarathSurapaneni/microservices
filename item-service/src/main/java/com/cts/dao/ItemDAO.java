package com.cts.dao;

import java.util.List;
import com.cts.model.Item;

/**
 * 
 * Item DAO
 *
 */
public interface ItemDAO {
	public void addItem(Item item);
    public Item findItemByName(String itemName);
    public List<Item> getAllItems();
    public boolean isItemExists(String itemName);


}
