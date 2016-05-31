package com.ufinity.ott.domain.RightsLocker;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class RLRetryRowMapper implements RowMapper<RLRetry> {

	public RLRetry mapRow(ResultSet rs, int rowNum) throws SQLException {
		RLRetry rlr = new RLRetry();
		rlr.setDatetime(rs.getLong("DATETIME"));
		rlr.setHub_id(rs.getString("HUB_ID"));
		rlr.setMethod(rs.getString("METHOD"));
		rlr.setPayload(rs.getString("PAYLOAD"));
		rlr.setTransaction_report(rs.getString("TRANSACTION_REPORT"));
		rlr.setRetry_count(rs.getInt("RETRY_COUNT"));
		rlr.setFailed(rs.getInt("FAILED"));
		rlr.setFailed_msg(rs.getString("FAILED_MSG"));
		rlr.setSource(rs.getString("SOURCE"));
		rlr.setSource(rs.getString("IP"));
		return rlr;
	}
}
