package com.cts.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.dao.ItemDAO;
import com.cts.model.Item;

/**
 * 
 * ItemService implementation
 *
 */
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemDAO itemDAO;

	@Override
	public void addItem(Item item) {
		itemDAO.addItem(item);
		
	}

	@Override
	public Item findItemByName(String itemName) {
		return itemDAO.findItemByName(itemName);
	}

	@Override
	public List<Item> getAllItems() {
		return itemDAO.getAllItems();
	}

	@Override
	public boolean isItemExists(String itemName) {
		return itemDAO.isItemExists(itemName);
	}

}
