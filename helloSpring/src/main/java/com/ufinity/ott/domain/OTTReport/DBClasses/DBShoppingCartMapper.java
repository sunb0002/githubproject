package com.ufinity.ott.domain.OTTReport.DBClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ufinity.ott.domain.Shopping_Cart;

public class DBShoppingCartMapper implements RowMapper<Shopping_Cart> {
	
	public Shopping_Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		Shopping_Cart cart = new Shopping_Cart();
	    
		cart.setHub_id(rs.getString("HUB_ID"));
		cart.setTxcode(rs.getString("TXCODE"));
		cart.setMsisdn(rs.getString("MSISDN"));
		cart.setFfurl(rs.getString("FFURL"));
		cart.setDevice(rs.getString("DEVICE"));
		
	    return cart;
	}
}
