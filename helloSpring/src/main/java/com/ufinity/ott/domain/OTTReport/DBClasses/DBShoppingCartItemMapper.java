package com.ufinity.ott.domain.OTTReport.DBClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ufinity.ott.domain.Shopping_Cart;

public class DBShoppingCartItemMapper implements RowMapper<Shopping_Cart> {

	public Shopping_Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		Shopping_Cart cart = new Shopping_Cart();
        
		cart.setHub_id(rs.getString("HUB_ID"));
		cart.setTxcode(rs.getString("TXCODE"));
		cart.setProduct_id(rs.getString("PRODUCT_ID"));
		cart.setAsset_name(rs.getString("ASSET_NAME"));
		cart.setAsset_url(rs.getString("ASSET_URL"));
		cart.setAsset_desc(rs.getString("ASSET_DESC"));
		cart.setAsset_id(rs.getString("ASSET_ID"));
		cart.setOffer_id(rs.getString("OFFER_ID"));
		cart.setType(rs.getString("TYPE"));
        return cart;
    }
}
