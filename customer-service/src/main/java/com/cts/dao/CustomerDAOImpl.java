package com.cts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cts.model.Customer;
import com.cts.model.CustomerRowMapper;

/**
 * 
 * CustomerDAO Implementation
 *
 */

@Transactional
@Repository
public class CustomerDAOImpl implements CustomerDAO{

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public long addCustomer(Customer customer) {
		String sql = "INSERT INTO customer ( email, first_name,last_name) values (?, ?, ?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, customer.getEmail());
				ps.setString(2, customer.getFirstName());	
				ps.setString(3,customer.getLastName());
				return ps;
			}
		}, holder);
		return holder.getKey().longValue();
	}

	@Override
	public List<Customer> getAllCustomers() {
		String sql = "SELECT id, email, first_name,last_name FROM customer";
		RowMapper<Customer> rowMapper = new CustomerRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public boolean isCustomerExists(String email) {
		boolean result = false;
		String sql = "SELECT count(1) FROM customer where email=?";
		int count = jdbcTemplate.queryForObject(
	                        sql, new Object[] { email }, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}
}
