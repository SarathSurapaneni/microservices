package com.cts.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * Item row mapper class
 *
 */

public class ItemRowMapper implements RowMapper<Item> {

	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item item = new Item();
		item.setItemId(rs.getLong("id"));
		item.setItemName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getLong("price"));
		return item;
	}

}
