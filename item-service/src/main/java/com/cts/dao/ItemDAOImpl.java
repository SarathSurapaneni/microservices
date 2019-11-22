package com.cts.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cts.model.Item;
import com.cts.model.ItemRowMapper;

/**
 * 
 * ItemDAOImpl Implementation
 *
 */

@Transactional
@Repository
public class ItemDAOImpl implements ItemDAO{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public void addItem(Item item) {
		String sql = "INSERT INTO item ( name, description, price) values (?, ?, ?)";
		jdbcTemplate.update(sql,item.getItemName(),item.getDescription(),item.getPrice());
	}

	@Override
	public Item findItemByName(String itemName) {
		String sql = "SELECT id, name, description, price FROM item WHERE name = ?";
		RowMapper<Item> rowMapper = new ItemRowMapper();
		Item item=null;
		try{
			item= jdbcTemplate.queryForObject(sql, rowMapper, itemName);
		}catch(EmptyResultDataAccessException  e){
			return null;
		}
		return item;
	}

	@Override
	public List<Item> getAllItems() {
		String sql = "SELECT id, name, description, price FROM item";
		RowMapper<Item> rowMapper = new ItemRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public boolean isItemExists(String itemName) {
		boolean result = false;
		String sql = "SELECT count(1) FROM item where name=?";
		int count = jdbcTemplate.queryForObject(
	                        sql, new Object[] { itemName }, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}

}
