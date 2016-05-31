package com.ufinity.ott.domain.OTTReport.DBClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufinity.ott.domain.ActionLog;
import com.ufinity.ott.domain.ActionLogMSG;

/**
 * @CopyRight Ufinity Pte Ltd - [2000-2015] All Rights Reserved
 */

public class DBActionLogRowMapper implements RowMapper<ActionLog> {
	public ActionLog mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		ActionLog actLog = new ActionLog();
		try {
			ActionLogMSG almsg = new ObjectMapper().readValue(rs.getString("MSG"),
					ActionLogMSG.class);

			actLog.setDatatime(rs.getLong("DATETIME"));
			actLog.setHub_id(rs.getString("HUB_ID")); 
			actLog.setAdmin_id(rs.getString("ADMIN_ID")); 
			actLog.setAction(rs.getString("ACTION"));
			actLog.setMsg(almsg); 
			actLog.setIp(rs.getString("IP"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actLog;
	}
}
