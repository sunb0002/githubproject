package com.ufinity.ott.domain.MediaPlatform;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ESMRowMapper implements RowMapper<EntitlementSynchronizerMapping> {
	
	public EntitlementSynchronizerMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
		EntitlementSynchronizerMapping esm = new EntitlementSynchronizerMapping();
		esm.setContentid(rs.getString("CONTENT_ID"));
		esm.setProductid(rs.getString("PRODUCT_ID"));
		esm.setName(rs.getString("NAME"));
		esm.setDisplay_name(rs.getString("DISPLAY_NAME"));
		return esm;
	}
}
