package com.ufinity.ott.dao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.sql.DataSource;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.ufinity.ott.common.config.ConfigManager;
import com.ufinity.ott.common.cons.ConstCode;
import com.ufinity.ott.common.logging.LogHelper;
import com.ufinity.ott.common.utils.Validators;
import com.ufinity.ott.domain.ActionLog;
import com.ufinity.ott.domain.ActionLogMSG;
import com.ufinity.ott.domain.CDR;
import com.ufinity.ott.domain.ChangeHubIdHistory;
import com.ufinity.ott.domain.DBQueryInfo;
import com.ufinity.ott.domain.Product;
import com.ufinity.ott.domain.Shopping_Cart;
import com.ufinity.ott.domain.MediaPlatform.ESMRowMapper;
import com.ufinity.ott.domain.MediaPlatform.EntitlementSynchronizerMapping;
import com.ufinity.ott.domain.OTTReport.DBClasses.DBActionLogRowMapper;
import com.ufinity.ott.domain.OTTReport.DBClasses.DBShoppingCartItemMapper;
import com.ufinity.ott.domain.OTTReport.DBClasses.DBShoppingCartMapper;
import com.ufinity.ott.domain.RightsLocker.RLRetry;
import com.ufinity.ott.domain.RightsLocker.RLRetryRowMapper;
import com.ufinity.ott.common.utils.BusinessLogicUtils;

/**
 * @CopyRight Ufinity - [2000-2015] All Rights Reserved
 */
@Repository
public class DBManager extends JdbcDaoSupport {
    	
	protected JdbcTemplate jdbcTemplate;
	private static long MAX_LIMIT =  9999999999L;
	public DBManager() {}	
	@Autowired
	public DBManager(DataSource dataSource) {
		super();
	    setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
	
	/**
	 * @author SunBo
	 * @param product_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DBQueryInfo getProductbyProductID(String product_id) {

		if (Validators.isEmpty(product_id)) {
			return new DBQueryInfo(ConstCode.ERR_GENERAL,
					"Empty input ProductID", null);
		}

		Product prod = null;
		List<String> list = new ArrayList<String>();
		list.add(product_id);

		DBQueryInfo dbi = this.getProductListbyProductIDList(list);
		if (dbi.isOk()) {
			List<Product> pl = (List<Product>) dbi.getObject();
			if (pl.size() > 0) {
				prod = pl.get(0);
				String productType = BusinessLogicUtils.getProductType(prod);
            	double finalPrice = BusinessLogicUtils.calculateFinalPrice(prod, getGSTConfig());
            	prod.setFinal_price(finalPrice);
            	prod.setProduct_type(productType);
			} else {
				LogHelper.debug("No product found with product_id: "
						+ product_id);
			}
		}

		dbi.setObject(prod);
		return dbi;
	}
	
	/**
	 * @author SunBo
	 * @param ProductIDList
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public DBQueryInfo getProductListbyProductIDList(List<String> ProductIDList) {

		LogHelper.info("Query by ProductIDList: " + ProductIDList);
		List<Product> pl = new ArrayList<Product>(); // default: an empty list

		if (Validators.isEmpty(ProductIDList)) {
			return new DBQueryInfo(ConstCode.ERR_GENERAL,
					"Empty input ProductIDList", pl);
		}

		try {
			String sql = ConfigManager
					.getSqlStatement("getProductListbyProductIDList");

			StringBuffer strb = new StringBuffer();
			for (String pd : ProductIDList) {
				strb.append("?,");
			}
			strb.setLength(strb.length() - 1);
			sql = sql.replaceAll("@LIST@", strb.toString());
			strb.setLength(0);

			// DBQuery profiling
			long time1 = System.currentTimeMillis();
			LogHelper.debug("------ DBQuery START ------");
			LogHelper.debug("SQL statement: " + sql + " with params: "
					+ ProductIDList);

			// BeanPropertyRowMapper is very powerful, you won't need to get and
			// set for each column
			// Condition is that your entity class fields' name are same as DB
			// columns
			@SuppressWarnings("rawtypes")
			BeanPropertyRowMapper bprm = new BeanPropertyRowMapper (Product.class);

			// Use this property to escape those NULL values in DB (set to Java
			// defult value)
			bprm.setPrimitivesDefaultedForNullValue(true);

			pl = jdbcTemplate.query(sql, ProductIDList.toArray(), bprm);
			if (Validators.isEmpty(pl)) {
				pl = new ArrayList<Product>();
			}else{
				int count = 0;
	            String gst_config = getGSTConfig();
	            for(Product p : pl){
	            	String productType = BusinessLogicUtils.getProductType(p);
	            	double finalPrice = BusinessLogicUtils.calculateFinalPrice(p, gst_config);
	            	p.setFinal_price(finalPrice);
	            	p.setProduct_type(productType);
	            	pl.set(count, p);
	            	count++;
	            }
			}

			long timeSpent = System.currentTimeMillis() - time1;
			LogHelper.debug("------ DBQuery END, timeSpent="
					+ timeSpent + " ------");

			LogHelper.debug("Query result: " + pl);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.toString(), pl);
		} catch (Exception e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_GENERAL, e.toString(), pl);
		}

		if (Validators.isEmpty(pl)) {
			LogHelper.debug("No product found with ProductIDList: "
					+ ProductIDList);
		} else {
			int count = 0;

			for (Product p : pl) {
				String productType = BusinessLogicUtils.getProductType(p);
				p.setProduct_type(productType);
				pl.set(count, p);
				count++;
			}
		}
		return new DBQueryInfo(ConstCode.ERR_OK, null, pl);
	}

	public DBQueryInfo actionLog(String HUB_ID, String ACTION, String SimpleMsg) {
		ActionLogMSG amsg = new ActionLogMSG(SimpleMsg);
		return this.actionLog(HUB_ID, null, ACTION, amsg, null);
	}
	
	/**
	 * @author SunBo
	 * @param HUB_ID
	 * @param ADMIN_ID
	 * @param ACTION
	 * @param MSGObj
	 * @param IP
	 * @return
	 * 
	 * Enforced JSON format on MSG field with ActionLog Entity.
	 * ActionLogMSG will handle LoginReport and TransactionReport.
	 * 
	 */
	public DBQueryInfo actionLog(String HUB_ID, String ADMIN_ID, String ACTION,
			ActionLogMSG MSGObj, String IP) {

		if (Validators.isEmpty(HUB_ID) || Validators.isEmpty(ACTION)) {
			return new DBQueryInfo(ConstCode.ERR_GENERAL,
					"Some fields cannot be null", null);
		}

		ActionLog alog = new ActionLog(HUB_ID, ADMIN_ID, ACTION, MSGObj, IP);
		int affected = 0;
		String sql = "";
		try {
			sql = ConfigManager.getSqlStatement("insertActionLog");
			affected = jdbcTemplate.update(sql, alog.getArgumentArray(),
					alog.getTypeArray());
		} catch (Exception e) {
			e.printStackTrace();
			// If any Exception happens here, record the requests to a temporary file.
			// Another Cronjob will pick up from the file and retry the insertion.
			// Set a maxlimit for the Cronjob, if retry is accumulating, send alert.
			
			LogHelper.info("<-- ERROR WRITING TO DB! Starting to write to file. -->");
			
			String filepath = ConfigManager.getConfig("ottgw.db.recovery.directory")
					+ ConfigManager.getConfig("ottgw.db.recovery.filename");
			
			try {
				LogHelper.info("<-- Writing ActionLog for " + HUB_ID + " to file " + filepath + "-->");
				FileWriter fw = new FileWriter (filepath, true);
				
				//parse ActionLogMSG to string
				ActionLogMSG amsg = alog.getMsg();
				JSONObject amsgjb = new JSONObject(amsg);
				String aMSG = amsgjb.toString();
				
				String sqlstm = "INSERT INTO OTT_GW_ACTIONLOGS (datatime, hub_id, admin_id, action, msg, ip) VALUES (";
				sqlstm += alog.getDatatime() + ",'";
				sqlstm += alog.getHub_id() + "','";
				sqlstm += alog.getAdmin_id() + "','";
				sqlstm += alog.getAction() + "','";
				sqlstm += StringEscapeUtils.escapeSql(aMSG) + "','";
				sqlstm += alog.getIp() + "');";
				
				fw.write(sqlstm);
				fw.write(System.lineSeparator());
				fw.close();
				
				LogHelper.info("<-- Written ActionLog for " + HUB_ID + " to file " + filepath + " -->");
				affected = 1;
			}
			catch (Exception exceptionwithinexception) {
				exceptionwithinexception.printStackTrace();
				LogHelper.info("<-- Error writing ActionLog for " + HUB_ID + " to file " + filepath + " -->");
				affected = 0;
			}
		}

		if (affected == 1) {
			LogHelper.info("Successfully inserted action log: "
					+ alog.toString());
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to insert action log: " + alog.toString()
					+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,",
					null);
		}
	}

	/*
	 * @author Jason
	 * @date 16 Dec 2015
	 * @process Change Hub ID
	*/
	public DBQueryInfo changeHubIdHistory(String oldHubId, String newHubId) {
		ChangeHubIdHistory changeHist = new ChangeHubIdHistory(oldHubId, newHubId);
		int affected = 0;
		String sql = "";
		
		try {
			sql = ConfigManager.getSqlStatement("insertChangeHubIdHistory");
			
			affected = jdbcTemplate.update(sql, changeHist.getArgumentArray(),
					changeHist.getTypeArray());
		} catch (Exception e) {
			e.printStackTrace();
			
			// If any Exception happens here, record the requests to a temporary file.
			// Another Cronjob will pick up from the file and retry the insertion.
			// Set a maxlimit for the Cronjob, if retry is accumulating, send alert.
			
			LogHelper.info("<-- ERROR WRITING TO DB! Starting to write to file. -->");
			
			String filepath = ConfigManager.getConfig("ottgw.db.recovery.directory")
					+ ConfigManager.getConfig("ottgw.db.recovery.filename");
			
			try {
				LogHelper.info("<-- Start Writing Change Hub ID History for " + oldHubId + " to file " + filepath + "-->");
				FileWriter fw = new FileWriter (filepath, true);
				
				String sqlstm = "INSERT INTO OTT_GW_HISTORY_CHANGEHUBID (DATETIME, HUB_ID, NEW_HUB_ID) VALUES (";
				sqlstm += changeHist.getDatatime() + ",'";
				sqlstm += changeHist.getHub_id() + "','";
				sqlstm += changeHist.getHub_id_new() + "');";
				
				fw.write(sqlstm);
				fw.write(System.lineSeparator());
				fw.close();
				
				LogHelper.info("<-- Finish Writing Change Hub ID History for " + oldHubId + " to file " + filepath + " -->");
				affected = 1;
			} catch (Exception exceptionwithinexception) {
				exceptionwithinexception.printStackTrace();
				LogHelper.info("<-- Error Writing Change Hub ID History for " + oldHubId + " to file " + filepath + " -->");
				affected = 0;
			}
		}
		
		if (affected == 1) {
			LogHelper.info("Successfully inserted Change Hub ID History for: "
				+ changeHist.toString());
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to insert Change Hub ID History for: "
				+ changeHist.toString());
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,", null);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public DBQueryInfo getAllProducts(){
		 List<Product> productSearchList = new ArrayList<Product>();
		 
			try{
				String sql = ConfigManager.getSqlStatement("selectAllProducts");
				LogHelper.debug("API (GET ALL Products)");
				LogHelper.debug("SQL Statement: " + sql); //remove later
				
				long time1 = System.currentTimeMillis();
				LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
				productSearchList = jdbcTemplate.query(sql, new PMP_DBRowMapper());
				
				long time2 = System.currentTimeMillis();
				LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
						+ "||t=" + (time2 - time1));
				
				if(productSearchList.size() == 0){
					LogHelper.debug("Product List NOT FOUND / NOT EXISTS!");
					return new DBQueryInfo(ConstCode.ERR_GENERAL, "No product found!", null);
				}
				else if(productSearchList.size() >= 1){
					LogHelper.debug("Product List generated...");
					
					int count = 0;
		            String gst_config = getGSTConfig();
		            for(Product p : productSearchList){
		            	String productType = BusinessLogicUtils.getProductType(p);
		            	double finalPrice = BusinessLogicUtils.calculateFinalPrice(p, gst_config);
		            	p.setFinal_price(finalPrice);
		            	p.setProduct_type(productType);
		            	productSearchList.set(count, p);
		            	count++;
		            }
				}
			}
			catch (DataAccessException e) {
				e.printStackTrace();
				return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(), null);
			} catch (Exception e) {
				e.printStackTrace();
				return new DBQueryInfo(ConstCode.ERR_GENERAL, e.getMessage(), null);
			}
			return new DBQueryInfo(ConstCode.ERR_OK, null, productSearchList);
	 }
	
	public long get_CDR_Session_ID(){
		long count = 0;
		
		try{
			String sql = ConfigManager.getSqlStatement("getCDRsessionID");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			//count = jdbcTemplate.queryForObject(sql, Integer.class);
			count = jdbcTemplate.queryForObject(sql, Long.class);
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			if(count >= MAX_LIMIT){
	        	reset_CDR_Session_ID();
	        	count = 1;
	        }
	        
			increment_CDR_Session_ID(count);
		}catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR GET Session ID Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR GET Session ID Error! : " + e.toString());
        	
			e.printStackTrace();
		}
		return count;
	}
	
	 public void reset_CDR_Session_ID() {
			
		try{
			String sql = ConfigManager.getSqlStatement("resetCDRsessionID");
			LogHelper.debug(sql); //remove later
				
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
				
			jdbcTemplate.update(sql);
				
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
				
		}catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        System.out.println(sw.toString());
	        	
	        LogHelper.cronerror("CDR GET Session ID Reset Error! : " + e.toString());
	        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        System.out.println(sw.toString());
	        	
	        LogHelper.cronerror("CDR GET Session ID Reset Error! : " + e.toString());
	        	
			e.printStackTrace();
		}
	 }
	 
	 public void increment_CDR_Session_ID(long count){
		 count++;
		 
		 try{
			String sql = ConfigManager.getSqlStatement("incrementCDRsessionID");
			LogHelper.debug(sql); //remove later
				
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
				
			jdbcTemplate.update(sql, new Object[] {count});
				
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
				
		}catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        System.out.println(sw.toString());
	        	
	        LogHelper.cronerror("CDR INCREMENT Session ID Error! : " + e.toString());
	        	
			e.printStackTrace();
			//return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.toString(), null);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
	        e.printStackTrace(new PrintWriter(sw));
	        System.out.println(sw.toString());
	        	
	        LogHelper.cronerror("CDR INCREMENT Session ID Error! : " + e.toString());
	        	
			e.printStackTrace();
			//return new DBQueryInfo(ConstCode.ERR_GENERAL, e.toString(), null);
		}
	 }

	/**
	 * author : Prem
	 * @param sql
	 * @return
	 */
	public int getEventRecordCount(long current_timestamp, long last_run){
		
		int count = 0;
		
		try{
			String sql = ConfigManager.getSqlStatement("getEventRecordCount");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			count = jdbcTemplate.queryForObject(sql, new Object[]{last_run, current_timestamp}, Integer.class);
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
		}catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR Cronjob GET Transaction count Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR Cronjob GET Transaction count Error! : " + e.toString());
        	
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * author : Willkie
	 * @param CDR
	 * @return boolean
	 */
	public boolean setEventRecord (CDR cdr) {
		try{
			String sqlsmt = ConfigManager.getSqlStatement("setEventRecord");
			LogHelper.debug(sqlsmt); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			jdbcTemplate.update(sqlsmt, new Object[] {
				cdr.getRecordtype(),
				cdr.getRecordsequence(),
				cdr.getSessionid(),
				cdr.getStarttime(),
				cdr.getEndtime(),
				cdr.getServicecategory(),
				cdr.getServicetype(),
				cdr.getServicecode(),
				cdr.getTypeofoperation(),
				cdr.getServiceparameter(),
				cdr.getSubscribeidentitytype(),
				cdr.getSubscribeidentity(),
				cdr.getUomtype(),
				cdr.getUomvalue(),
				cdr.getOriginaddress(),
				cdr.getDestinationaddress(),
				cdr.getPrerated(),
				cdr.getCurrency(),
				cdr.getChargeableamt(),
				cdr.getTaxableamt(),
				cdr.getNontaxableamt(),
				cdr.getTaxamt(),
				cdr.getDiscountamt(),
				cdr.getDiscountdescription(),
				cdr.getPaymentmode(),
				cdr.getBilldescription(),
				cdr.getPartnertype(),
				cdr.getPartneridentification(),
				cdr.getGroupcalltype(),
				cdr.getGroupid(),
				cdr.getRemark1(),
				cdr.getRemark2(),
				cdr.getRemark3(),
				cdr.getRemark4(),
				cdr.getRemark5(),
				cdr.getAssetid(),
				cdr.getStatus(),
				cdr.getRetrycount(),
				cdr.getErrormsg(),
				cdr.getDatecreated()
			});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBInsertTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * author : Willkie
	 * @Param List<CDR>
	 * @return boolean
	 */
	public boolean setEventRecords (List<CDR> cdrlist) {
		try{
			for (CDR cdr : cdrlist) {
				boolean insertStatus = false;
				LogHelper.debug("Inserting CDR record into DB: " + cdr.getSessionid());
				
				insertStatus = setEventRecord(cdr);
				if (!insertStatus) {
					LogHelper.debug("Failed to insert into DB CDR record " + cdr.getSessionid());
					return false;
				}
			}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * author : Prem
	 * @param sql
	 * @return List<CDR>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CDR> getEventRecords(long current_timestamp, long last_run){

		List<CDR> cdrList = new ArrayList<CDR>();
		try{
			String sql = ConfigManager.getSqlStatement("getEventRecords");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			cdrList = jdbcTemplate.query(sql, new Object[]{last_run, current_timestamp}, new RowMapper() {
						public Object mapRow(ResultSet rs, int rowNum) throws SQLException
						{
							CDR cdr = new CDR();
							cdr.setRecordtype(validateForNullString("RECORD_TYPE", rs));
							cdr.setRecordsequence(validateForNullString("RECORD_SEQUENCE", rs));
							cdr.setSessionid(validateForNullString("SESSION_ID", rs));
							cdr.setStarttime(validateForNullString("START_DATE_TIME", rs));
							cdr.setEndtime(validateForNullString("END_DATE_TIME", rs));
							cdr.setServicecategory(validateForNullString("SERVICE_CATEGORY", rs));
							cdr.setServicetype(validateForNullString("SERVICE_TYPE", rs));
							cdr.setServicecode(validateForNullString("SERVICE_CODE", rs));
							cdr.setTypeofoperation(validateForNullString("TYPE_OF_OPERATION", rs));
							cdr.setServiceparameter(validateForNullString("SERVICE_PARAMETER", rs));
							cdr.setSubscribeidentitytype(validateForNullString("SUBSCRIBER_IDENTIFICATION_TYPE", rs));
							cdr.setSubscribeidentity(validateForNullString("SUBSCRIBER_IDENTIFICATION", rs));
							cdr.setUomtype(validateForNullString("UOM_TYPE", rs));
							cdr.setUomvalue(validateForNullString("UOM_VALUE", rs));
							cdr.setOriginaddress(validateForNullString("ORIGIN_ADDRESS", rs));
							cdr.setDestinationaddress(validateForNullString("DESTINATION_ADDRESS", rs));
							cdr.setPrerated(validateForNullString("PRERATED", rs));
							cdr.setCurrency(validateForNullString("CURRENCY", rs));
							cdr.setChargeableamt(validateForNullString("CHARGEABLE_AMOUNT", rs));
							cdr.setTaxableamt(validateForNullString("TAXABLE_AMOUNT", rs));
							cdr.setNontaxableamt(validateForNullString("NON_TAXABLE_AMOUNT", rs));
							cdr.setTaxamt(validateForNullString("TAX_AMOUNT", rs));
							cdr.setDiscountamt(validateForNullString("DISCOUNT_AMOUNT", rs));
							cdr.setDiscountdescription(validateForNullString("DISCOUNT_DESCRIPTION", rs));
							cdr.setPaymentmode(validateForNullString("PAYMENT_MODE", rs));
							cdr.setBilldescription(validateForNullString("BILL_DESCRIPTION", rs));
							cdr.setPartnertype(validateForNullString("PARTNER_TYPE", rs));
							cdr.setPartneridentification(validateForNullString("PARTNER_IDENTIFICATION", rs));
							cdr.setGroupcalltype(validateForNullString("GROUP_CALL_TYPE", rs));
							cdr.setGroupid(validateForNullString("GROUP_ID", rs));
							cdr.setRemark1(validateForNullString("REMARK1", rs));
							cdr.setRemark2(validateForNullString("REMARK2", rs));
							cdr.setRemark3(validateForNullString("REMARK3", rs));
							cdr.setRemark4(validateForNullString("REMARK4", rs));
							cdr.setRemark5(validateForNullString("REMARK5", rs));
							cdr.setAssetid(validateForNullString("ASSET_ID", rs));
							cdr.setStatus(validateForNullString("STATUS", rs));
							cdr.setRetrycount(validateForNullInteger("RETRY_COUNT", rs));
							cdr.setErrormsg(validateForNullString("ERROR_MSG", rs));
							cdr.setDatecreated(validateForNullString("DATE_CREATED", rs));

							return cdr;
						}
					});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
		}catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR Cronjob SELECT (Retrieve records) Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR Cronjob SELECT (Retrieve records) Error! : " + e.toString());
        	
			e.printStackTrace();
		}
		return cdrList;
	}
	
	/**
	 * author : Prem
	 * @param column : The column name in MySQL DB table.
	 * @return : If not null, returns the column value in MySQL DB. Otherwise, returns blank value. 
	 * @throws Exception
	 * Description : Validates column values for null (for type String).
	 */
	public String validateForNullString(String column, ResultSet rs) throws SQLException{
		String value = rs.getString(column);
		if(rs.wasNull()){
			value = "";
		}
		return value;
	}
	
	/**
	 * author : Prem
	 * @param column : The column name in MySQL DB table.
	 * @return : If not null, returns the column value in MySQL DB. Otherwise, returns "0.0".
	 * @throws Exception
	 * Description : Validates column values for null (for type String).
	 */
	public String validateForNullPrice(String column, ResultSet rs) throws SQLException{
		String value = rs.getString(column);
		if(rs.wasNull()){
			 value = "0.0";
		}
		return value;
	}
	
	/**
	 * author : Prem
	 * @param column : The column name in MySQL DB table.
	 * @return : If not null, returns the column value in MySQL DB. Otherwise, returns "0".
	 * @throws Exception
	 * Description : Validates column values for null (for type Integer).
	 */
	public int validateForNullInteger(String column, ResultSet rs) throws SQLException{
		int value = rs.getInt(column);
		if(rs.wasNull()){
			value = 0;
		}
		return value;
	}
	
	/**
	 * author : Prem
	 * @param sql
	 * @return
	 */
	public long getCurrentTimestamp(){
		String timestamp = "";
		try{
			String sql = ConfigManager.getSqlStatement("getCurrentTimestamp");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			timestamp = jdbcTemplate.queryForObject(sql, String.class);
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR Cronjob GET current timestamp Error! : " + e.toString());
        	
			e.printStackTrace();
			//return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.toString(), null);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR Cronjob GET current timestamp Error! : " + e.toString());
        	
			e.printStackTrace();
			//return new DBQueryInfo(ConstCode.ERR_GENERAL, e.toString(), null);
		}
		return Long.parseLong(timestamp);
	}
	
	/**
	 * author : Prem
	 * @param sql
	 * @return
	 */
	public int getTimeDifference(String last_run){
		int timediff = 0;
		try{
			String sql = ConfigManager.getSqlStatement("getTimeDifference");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			timediff = jdbcTemplate.queryForObject(sql, new Object[]{last_run}, Integer.class);
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR Cronjob GET timestamp difference Error! : " + e.toString());
        	
			e.printStackTrace();
			//return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.toString(), null);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR Cronjob GET timestamp difference Error! : " + e.toString());
        	
			e.printStackTrace();
			//return new DBQueryInfo(ConstCode.ERR_GENERAL, e.toString(), null);
		}
		return timediff;
	}
	
	/**
	 * author : Prem
	 * @param sql
	 */
	public void updateStatus(String timestamp){
		try{
			String sql = ConfigManager.getSqlStatement("updateStatus");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			jdbcTemplate.update(sql, new Object[] {timestamp});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR Cronjob UPDATE (Update Status) Error! : " + e.toString());
        	
			e.printStackTrace();
			//return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.toString(), null);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("CDR Cronjob UPDATE (Update Status) Error! : " + e.toString());
        	
			e.printStackTrace();
			//return new DBQueryInfo(ConstCode.ERR_GENERAL, e.toString(), null);
		}
	}
	
	/**
	 * 
	 * @param product
	 * Add or update Commercial Catalog products read from Commercial Catalog JSON files, to DB.
	 */
	public String AddUpdateCCProducts(Product product){
		List<Product> CCSearchList = new ArrayList<Product>();
		String processType = "nil";
		try{
			String sql = ConfigManager.getSqlStatement("selectCCproducts");
			LogHelper.croninfo("Commercial Catalog Product ID : " + product.getProduct_id());
			LogHelper.debug("Commercial Catalog Product ID : " + product.getProduct_id());
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			CCSearchList = jdbcTemplate.query(sql, new Object[]{product.getProduct_id()}, new PMP_DBRowMapper());
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			if(CCSearchList.size() == 0){
				processType = AddNewCCProduct(product);
				if(processType.equals("add")){
					LogHelper.pmpaudit("(Trigger Point : Commercial_Catalog_Cronjob) New Commercial Catalog Product added with ID : " + product.getProduct_id());
				}else if(processType.equals("error")){
					LogHelper.pmpaudit("(Trigger Point : Commercial_Catalog_Cronjob) Failed adding new Commercial Catalog Product with ID : " + product.getProduct_id() + ". Refer to cronerror / debug logs for more details.");
				}
			}else{
				if(CCSearchList.size() == 1){
					Product existingCC = CCSearchList.get(0);
					if(!product.equals(existingCC)){
						processType = UpdateExistingCCProduct(product);
						if(processType.equals("update")){
							product.setFinal_price(BusinessLogicUtils.calculateFinalPrice(product, getGSTConfig()));
							LogHelper.pmpaudit("(Trigger Point : Commercial_Catalog_Cronjob) Updated existing Commercial Catalog Product with ID : " + product.getProduct_id()
								+ "\n" + "Updated Fields:" + BusinessLogicUtils.GetUpdatedFields(product, existingCC, "Commercial_Catalog"));
						}else if(processType.equals("error")){
							LogHelper.pmpaudit("(Trigger Point : Commercial_Catalog_Cronjob) Failed to update Commercial Catalog product with ID : " + product.getProduct_id() +". Refer to cronerror / debug logs for further details.");
						}
					}else{
						LogHelper.pmpaudit("(Trigger Point : Commercial_Catalog_Cronjob) No update required for Commercial Catalog product with ID : " + product.getProduct_id() +". No change to data.");
					}
				}
			}
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Commercial Catalog Cronjob SELECT Error! : " + e.toString());
        	
			e.printStackTrace();
			processType = "error";
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Commercial Catalog Cronjob SELECT Error! : " + e.toString());
        	
			e.printStackTrace();
			processType = "error";
		}
		
		return processType;
	}
	
	/**
	 * 
	 * @param product
	 * Add new Commercial Catalog product to DB.
	 */
	public String AddNewCCProduct(Product product){
		String processType = "nil";
		try{
			String sql = ConfigManager.getSqlStatement("addCCproduct");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			jdbcTemplate.update(sql, new Object[] {product.getProduct_id(), product.getCategory(), product.getPrice_net(), product.getDiscount(), product.getRental_period(), product.getStart_time(), product.getEnd_time(), product.getAsset_id(), product.getSource(), getCurrentUnixTimestamp(), product.getDate_modified(), product.getDescription(), product.getGst_enabled(), product.getPayment_method()});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			processType = "add";
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	processType = "error";
        	LogHelper.cronerror("Commercial Catalog Cronjob INSERT (Add record) Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	processType = "error";
        	LogHelper.cronerror("Commercial Catalog Cronjob INSERT (Add record) Error! : " + e.toString());
        	
			e.printStackTrace();
		}
		
		return processType;
	}
	
	/**
	 * 
	 * @param product
	 * Update existing commercial catalog product to DB.
	 */
	public String UpdateExistingCCProduct(Product product){
		String processType = "nil";
		try{
			String sql = ConfigManager.getSqlStatement("updateCCproduct");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			jdbcTemplate.update(sql, new Object[] {product.getCategory(), product.getPrice_net(), product.getDiscount(), product.getRental_period(), product.getStart_time(), product.getEnd_time(), product.getAsset_id(), product.getSource(), getCurrentUnixTimestamp(), product.getDescription(), product.getGst_enabled(), product.getProduct_id()});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			processType = "update";
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	processType = "error";
        	LogHelper.cronerror("Commercial Catalog Cronjob UPDATE (Update record) Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	processType = "error";
        	LogHelper.cronerror("Commercial Catalog Cronjob UPDATE (Update record) Error! : " + e.toString());
        	
			e.printStackTrace();
		}
		
		return processType;
	}
	
	/**
	 * 
	 * @return
	 * Return a list of all Commercial Catalog product_ids present in DB.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> GetExistingCCProducts(){
		
		List<String> cc_List = new ArrayList<String>();
		
		try{
			String sql = ConfigManager.getSqlStatement("getExistingCCproduct");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			cc_List = jdbcTemplate.query(sql, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
				{
						return rs.getString(1);
				}
			});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Commercial Catalog Cronjob SELECT (Fetch Existing records) Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Commercial Catalog Cronjob SELECT (Fetch Existing records) Error! : " + e.toString());
        	
			e.printStackTrace();
		}
		
		return cc_List;
	}
	
	/**
	 * 
	 * @param id
	 * Delete Commercial Catalog product that is present in DB but not in latest Commercial Catalog JSON file.
	 */
	public String DeleteCCProduct(String id){
		String processType = "nil";
		try{
			String sql = ConfigManager.getSqlStatement("deleteCCproduct");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			jdbcTemplate.update(sql, new Object[] {id});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			processType = "delete";
			
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Commercial Catalog Cronjob DELETE (Delete record) Error! : " + e.toString());
        	
			e.printStackTrace();
			processType = "error";
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Commercial Catalog Cronjob DELETE (Delete record) Error! : " + e.toString());
        	
			e.printStackTrace();
			processType = "error";
		}
		
		return processType;
	}
	
	/**
	 * 
	 * @param subtype
	 * Add new Commercial Catalog Category if any present.
	 */
	public void AddUpdateCCcategory(String subtype){
		try{
			String sql = ConfigManager.getSqlStatement("selectCCcategoryCount");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			int count = jdbcTemplate.queryForObject(sql, new Object[]{subtype}, Integer.class);
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			if(count == 0){
				AddNewCCcategory(subtype);
			}
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Commercial Catalog Cronjob SELECT (Fetch existing categories) Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Commercial Catalog Cronjob SELECT (Fetch existing categories) Error! : " + e.toString());
        	
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param subtype
	 * Add new Commercial Catalog category.
	 */
	public void AddNewCCcategory(String subtype){
		try{
			String sql = ConfigManager.getSqlStatement("addCCcategory");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			jdbcTemplate.update(sql, new Object[] {subtype});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Commercial Catalog Cronjob INSERT (Add new category) Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Commercial Catalog Cronjob INSERT (Add new category) Error! : " + e.toString());
        	
			e.printStackTrace();
		}
	}
	
	/** 
	* To insert returned txcode and related data to OTT_GW_TRANSACTION_CART
	*
	* @author Benard
	* @param Shopping_Cart
	* @return DBQueryInfo 
	*/
	public DBQueryInfo insertTransactionCart(Shopping_Cart new_cart) {
		
		int affected = 0;
		
		try {
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			String sql = ConfigManager.getSqlStatement("insertTransactionCart");
	
			affected = jdbcTemplate.update(sql, new_cart.getCartInfo());	
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (affected == 1) {
			LogHelper.info("Successfully inserted new cart into DB: "
					+ new_cart.getCartInfo());
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to insert new cart into DB: " + new_cart.getCartInfo()
					+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,",
					null);
		}
		
	}
	
	/** 
	* To insert cart item and related data to OTT_GW_TRANSACTION_CART_ITEM
	*
	* @author Benard
	* @param Shopping_Cart, Product, String
	* @return DBQueryInfo 
	*/
	public DBQueryInfo insertTransactionCartItem(Shopping_Cart add_cart, Product product, String product_type) {
		
		int affected = 0;
		
		try {
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			// execute insert statement to OTT_GW_TRANSACTION_CART_ITEM table
			String sql = ConfigManager.getSqlStatement("insertTransactionCartItem");
			affected = jdbcTemplate.update(sql, 
					add_cart.getCartItemInfo(product_type, add_cart.getOffer_id(), add_cart.getAsset_id(), add_cart.getType()));
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
			
		} catch (DataIntegrityViolationException e) {
			return new DBQueryInfo(HttpStatus.NOT_FOUND.value(), "Shopping cart not found",
					null);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (affected == 1) {
			LogHelper.info("Successfully inserted new cart into DB: "
					+ add_cart.getCartInfo());
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to insert new cart into DB: " + add_cart.getCartInfo()
					+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,",
					null);
		}
	}
	
	/** 
	 * To delete cart item from OTT_GW_TRANSACTION_CART_ITEM table
	 *
	 * @author Benard
	 * @param Shopping_Cart
	 * @return DBQueryInfo 
	 */
	public DBQueryInfo removeTransactionCartItem(Shopping_Cart remove_cart) {
		
		int affected = 0;
		
		try {
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			// Remove cart item from OTT_GW_TRANSACTION_CART_ITEM Table
			String sql = ConfigManager.getSqlStatement("removeTransactionCartItem");
			//affected = jdbcTemplate.update(sql, remove_cart.retrieveCartItem());
			affected = jdbcTemplate.update(sql, remove_cart.retrieveCartItemForRemove());
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (affected > 0) {
			LogHelper.info("Successfully remove cart item from DB: "
					+ remove_cart.retrieveCartItemForRemove()+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to remove cart item from DB: " + remove_cart.retrieveCartItemForRemove()
					+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,",
					null);
		}
	}
	
	/** 
	* To delete cart info from OTT_GW_TRANSACTION_CART table
	*
	* @author Benard
	* @param Shopping_Cart
	* @return DBQueryInfo 
	*/
	public DBQueryInfo removeTransactionCart(Shopping_Cart remove_cart) {
		
		int affected = 0;
		
		try {
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			// Remove cart item from OTT_GW_TRANSACTION_CART Table
			String sql = ConfigManager.getSqlStatement("removeTransactionCart");
			affected = jdbcTemplate.update(sql, remove_cart.getTxcode());
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (affected == 1) {
			LogHelper.info("Successfully remove cart info from DB: "
					+ remove_cart.getTxcode());
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to remove cart item from DB: " + remove_cart.getTxcode()
					+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,",
					null);
		}
	}
	
	/** 
	* To retrieve cart item from OTT_GW_TRANSACTION_CART_ITEM table
	*
	* @author Benard
	* @param Shopping_Cart
	* @return DBQueryInfo 
	*/
	public DBQueryInfo retrieveTransactionCartItem(Shopping_Cart check_cart) {
		
		int affected = 0;
		
		try {
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			// Retrieve cart item from OTT_GW_TRANSACTION_CART_ITEM Table
			String sql = ConfigManager.getSqlStatement("retrieveTransactionCartItem");
			affected = jdbcTemplate.queryForObject(sql, check_cart.retrieveCartItem(), Integer.class);
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (affected > 0) {
			// item found return 200
			LogHelper.info("Cart item found in DB: "
					+ check_cart.retrieveCartItem());
			return new DBQueryInfo(ConstCode.ERR_OK, HttpStatus.OK.value() + "", null);
		} else if (affected == 0) {
			// item not found return 404
			LogHelper.info("Cart item not found in DB: "
					+ check_cart.retrieveCartItem());
			return new DBQueryInfo(ConstCode.ERR_OK, HttpStatus.NOT_FOUND.value() + "", null);
		} else {
			// return error
			LogHelper.info("Failed to retrieve cart item from DB: " + check_cart.retrieveCartItem()
					+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,",
					null);
		}
	}
	
	/** 
	* To retrieve cart item from OTT_GW_TRANSACTION_CART_ITEM table
	*
	* @author Benard
	* @param Shopping_Cart
	* @return List<Shopping_Cart> 
	*/
	public DBQueryInfo retrieveCartItembyTxcode(Shopping_Cart cart) {
		
		List<Shopping_Cart> cart_list = new ArrayList<Shopping_Cart>();
		
		try {
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			String sql = ConfigManager.getSqlStatement("retrieveShoppingCartItem");
			cart_list = jdbcTemplate.query(sql, new Object[] {cart.getTxcode()}, new DBShoppingCartItemMapper());
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));		
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (cart_list != null) {
			return new DBQueryInfo(ConstCode.ERR_OK, "", cart_list);
		} else {
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Data Not Found", null);
		}
	}
	
	/** 
	* To retrieve cart info from OTT_GW_TRANSACTION_CART table
	*
	* @author Benard
	* @param Shopping_Cart
	* @return Shopping_Cart 
	*/
	public DBQueryInfo retrieveCartbyTxcode(Shopping_Cart cart) {
		
		//Shopping_Cart cart_info = new Shopping_Cart();
		List<Shopping_Cart> cart_info = new ArrayList<Shopping_Cart>();
		Shopping_Cart temp_cart = new Shopping_Cart();
		
		try {
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			String sql = ConfigManager.getSqlStatement("retrieveShoppingCart");
			cart_info = jdbcTemplate.query(sql, new Object[] {cart.getTxcode()}, new DBShoppingCartMapper());
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));	
			
			// Loop cart_info and get unique record for shopping cart
			ListIterator<Shopping_Cart> litr = cart_info.listIterator();
			
			while (litr.hasNext()) {
				temp_cart = litr.next();
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (temp_cart != null) {
			return new DBQueryInfo(ConstCode.ERR_OK, "", temp_cart);
		} else {
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Data Not Found", null);
		}
	}
	
	public long getCurrentUnixTimestamp(){
		 Date now = new Date();
		 long unixTimestamp = now.getTime() / 1000L;
		 return unixTimestamp;
	 }
	
	/**
	 * @author Willkie
	 * @param hubid, starttime, endtime
	 * @return List<ActionLog>
	 */
	public List<ActionLog> getLoginReportInfoFromActionLog (String hubid, String starttime, String endtime, String extra) {
		List<ActionLog> aLog = new ArrayList<ActionLog>();
		String transaction_type = "LOGIN";
		try{
			String sqlsmt =  "";
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			if (hubid.isEmpty()) {
				if (extra.isEmpty()) {
					sqlsmt =  ConfigManager.getSqlStatement("getReportDetailsWithoutHubid");
				}
				else {
					sqlsmt =  ConfigManager.getSqlStatement("getReportDetailsWithExtraAndWithoutHubid");
					sqlsmt = sqlsmt.replace("@EXTRA_CONDITIONS@",extra);
				}
				aLog = jdbcTemplate.query(sqlsmt, new Object[] {starttime, endtime, transaction_type}, new DBActionLogRowMapper());
			}
			else {
				if (extra.isEmpty()) {
					sqlsmt =  ConfigManager.getSqlStatement("getReportDetails");
				}
				else {
					sqlsmt =  ConfigManager.getSqlStatement("getReportDetailsWithHubidAndExtra");
					sqlsmt = sqlsmt.replace("@EXTRA_CONDITIONS@",extra);
				}
				aLog = jdbcTemplate.query(sqlsmt, new Object[] {hubid, starttime, endtime, transaction_type}, new DBActionLogRowMapper());
			}
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));

			LogHelper.debug("SQL Statement: " + sqlsmt);
			LogHelper.debug("Query result: " + aLog.size());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return aLog;
	}
	
	/**
	 * @author Willkie
	 * @param hubid, starttime, endtime, extra
	 * @return List<ActionLog>
	 */
	public List<ActionLog> getTransactionReportInfoFromActionLog (String hubid, String starttime, String endtime, String extra) {
		List<ActionLog> aLog = new ArrayList<ActionLog>();
		try {
			String sqlsmt =  "";
			String transaction_type = "TRANSACTION";
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			if (hubid.isEmpty()) {
				if (extra.isEmpty()) {
					sqlsmt =  ConfigManager.getSqlStatement("getReportDetailsWithoutHubid");
				}
				else {
					sqlsmt =  ConfigManager.getSqlStatement("getReportDetailsWithExtraAndWithoutHubid");
					sqlsmt = sqlsmt.replace("@EXTRA_CONDITIONS@",extra);
				}
				aLog = jdbcTemplate.query(sqlsmt, new Object[] {starttime, endtime, transaction_type}, new DBActionLogRowMapper());
			}
			else {
				if (extra.isEmpty()) {
					sqlsmt =  ConfigManager.getSqlStatement("getReportDetails");
				}
				else {
					sqlsmt =  ConfigManager.getSqlStatement("getReportDetailsWithHubidAndExtra");
					sqlsmt = sqlsmt.replace("@EXTRA_CONDITIONS@",extra);
				}
				aLog = jdbcTemplate.query(sqlsmt, new Object[] {hubid, starttime, endtime, transaction_type}, new DBActionLogRowMapper());
			}
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			LogHelper.debug("SQL Statement: " + sqlsmt);
			LogHelper.debug("Query result: " + aLog.size());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return aLog;
	}
	
	/**
	 * @author Willkie
	 * @param Rights Locker Retry
	 * @return DBQueryInfo
	 */
	public DBQueryInfo insertRightsLockerRetry (RLRetry rlr) {
		
		int affected = 0;
		
		try {
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			String sql = ConfigManager.getSqlStatement("insertRightsLockerRetry");
	
			LogHelper.debug("**** RLR entity: " + rlr.toString());
			affected = jdbcTemplate.update(sql, rlr.getRLRetryInfo(), rlr.getTypeArray());	
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
		}
		catch (Exception e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		}
		
		if (affected == 1) {
			LogHelper.info("Successfully inserted rights locker retry info into DB: "
					+ rlr.getRLRetryInfo());
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to insert rights locker retry info into DB: " + rlr.getRLRetryInfo()
					+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,",
					null);
		}
	}
	
	/**
	 * @author Willkie
	 * @return List<RLRetry>
	 */

	public List<RLRetry> getRightsLockerRetry () {
		List<RLRetry> rlr_list = new ArrayList<RLRetry>();
		
		try {
			String sqlsmt = ConfigManager.getSqlStatement("retrieveRightsLockerForRetry");
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			rlr_list = jdbcTemplate.query(sqlsmt, new RLRetryRowMapper());
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			LogHelper.debug("SQL Statement: " + sqlsmt);
			LogHelper.debug("Query result: " + rlr_list.size());
			
			return rlr_list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return rlr_list;
	}

	/**
	 * @author Willkie
	 * @param RLRetry
	 * @return DBQueryInfo
	 */
	public DBQueryInfo removeRightsLockerRetry (RLRetry rlr) {
		int affected = 0;
		
		try {
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			String sql = ConfigManager.getSqlStatement("removeRightsLockerRetry");
	
			affected = jdbcTemplate.update(sql, new Object[] {rlr.getDatetime(), rlr.getHub_id()});	
			
			long time2 = System.currentTimeMillis();
			
			LogHelper.debug("Executed sql statement: " + sql + " with params: " + 
					rlr.getDatetime() + ", " + rlr.getHub_id());
			
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
		}
		catch (Exception e) {
			e.printStackTrace();
			LogHelper.info("<-- ERROR WRITING TO DB! Starting to write to file. -->");
			
			String filepath = ConfigManager.getConfig("ottgw.db.recovery.directory")
					+ ConfigManager.getConfig("ottgw.db.recovery.filename");
			
			try {
				LogHelper.info("<-- Writing remove retry entry for " + rlr.getHub_id() + " to file " + filepath + "-->");
				FileWriter fw = new FileWriter (filepath, true);
				
				String sqlstm = "DELETE FROM OTT_GW_TRANSACTION_RIGHTSLOCKER_RETRY WHERE ";
				sqlstm += "DATETIME=" + rlr.getDatetime();
				sqlstm += "HUB_ID=" + rlr.getHub_id();
				sqlstm += "SOURCE=" + rlr.getSource();
				
				fw.write(sqlstm);
				fw.write(System.lineSeparator());
				fw.close();
				
				LogHelper.info("<-- Written remove retry entry for " + rlr.getHub_id() + " to file " + filepath + " -->");
				affected = 1;
			}
			catch (Exception exceptionwithinexception) {
				exceptionwithinexception.printStackTrace();
				LogHelper.info("<-- Error writing remove retry entry for " + rlr.getHub_id() + " to file " + filepath + " -->");
				affected = 0;
			}
		}
		
		if (affected == 1) {
			LogHelper.info("Successfully removed RightsLocker Retry info from DB: "
					+ rlr.getDatetime() + ", " + rlr.getHub_id() + ", " + rlr.getSource());
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to remove RightsLocker Retry info from DB: " 
					+ rlr.getDatetime() + ", " + rlr.getHub_id() + ", " + rlr.getSource()
					+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,", null);
		}
	}
	
	/**
	 * @author Willkie
	 * @param RLRetry old, RLRetry new
	 * @return DBQueryInfo
	 */
	public DBQueryInfo updateRightsLockerRetry (RLRetry rlr_prev, RLRetry rlr_update) {
		int affected = 0;
		
		try {
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			String sql = ConfigManager.getSqlStatement("updateRightsLockerRetry");
	
			affected = jdbcTemplate.update(sql, new Object[] {
					rlr_update.getDatetime(), rlr_update.getHub_id(),
					rlr_update.getMethod(), rlr_update.getPayLoad(),
					rlr_update.getTransaction_Report(), rlr_update.getRetry_count(),
					rlr_update.getFailed(), rlr_update.getFailed_msg(),
					rlr_update.getSource(), rlr_update.getIp(),
					
					rlr_prev.getDatetime(), rlr_prev.getHub_id(),
					rlr_prev.getMethod(), rlr_prev.getPayLoad(),
					rlr_prev.getTransaction_Report(), rlr_prev.getRetry_count(),
					rlr_prev.getFailed(), rlr_prev.getFailed_msg(),
					rlr_prev.getSource(), rlr_prev.getIp()
			});	
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
		}
		catch (Exception e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(), null);
		}
		
		if (affected == 1) {
			LogHelper.info("Successfully updated row in DB: "
					+ rlr_update.getRLRetryInfo() + " From previous: " 
					+ rlr_prev.getRLRetryInfo());
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to update row in DB: " + rlr_update.getRLRetryInfo() 
					+ " From previous: " + rlr_prev.getRLRetryInfo()
					+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,", null);
		}
	}

	/**
	 * 
	 * @param product
	 * Add or update Residential Cable TV products read from CSV file, to DB.
	 */
	public void AddUpdateRCTVProducts(Product product){
		List<Product> RCTVSearchList = new ArrayList<Product>();
		try{
			String sql = ConfigManager.getSqlStatement("selectRCTVproducts");
			LogHelper.croninfo("Bulk Child Cable TV Residential Product ID : " + product.getProduct_id());
			LogHelper.debug("Bulk Child Cable TV Residential Product ID : " + product.getProduct_id());
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			RCTVSearchList = jdbcTemplate.query(sql, new Object[]{product.getProduct_id()}, new PMP_DBRowMapper());
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			if(RCTVSearchList.size() == 0){
				AddNewRCTVProduct(product);
			}else{
				if(RCTVSearchList.size() == 1){
					Product existingRCTV = RCTVSearchList.get(0);
					if(!product.equals(existingRCTV)){
						UpdateExistingRCTVProduct(product);
					}
				}
			}
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Bulk Child Cable TV Residential SELECT Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Bulk Child Cable TV Residential SELECT Error! : " + e.toString());
        	
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param product
	 * Add new Bulk Child Cable TV Residential product to DB.
	 */
	public void AddNewRCTVProduct(Product product){
		try{
			String sql = ConfigManager.getSqlStatement("addRCTVproduct");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			jdbcTemplate.update(sql, new Object[] {product.getProduct_id(), product.getCategory(), product.getPrice_net(), product.getDiscount(), product.getRental_period(), product.getStart_time(), product.getEnd_time(), product.getAsset_id(), product.getSource(), getCurrentUnixTimestamp(), product.getDate_modified(), product.getDescription(), product.getGst_enabled()});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			LogHelper.pmpaudit("New Bulk Child Cable TV Residential Product added with ID : " + product.getProduct_id());
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Bulk Child Cable TV Residential INSERT (Add record) Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Bulk Child Cable TV Residential INSERT (Add record) Error! : " + e.toString());
        	
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param product
	 * Update existing Bulk Child Cable TV Residential product to DB.
	 */
	public void UpdateExistingRCTVProduct(Product product){
		try{
			String sql = ConfigManager.getSqlStatement("updateRCTVproduct");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			jdbcTemplate.update(sql, new Object[] {product.getCategory(), product.getPrice_net(), product.getDiscount(), product.getRental_period(), product.getStart_time(), product.getEnd_time(), product.getAsset_id(), product.getSource(), getCurrentUnixTimestamp(), product.getDescription(), product.getGst_enabled(), product.getProduct_id()});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			LogHelper.pmpaudit("Updated existing Bulk Child Cable TV Residential Product with ID : " + product.getProduct_id());
			
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Bulk Child Cable TV Residential UPDATE (Update record) Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Bulk Child Cable TV Residential UPDATE (Update record) Error! : " + e.toString());
        	
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 * Return a list of all Bulk Child Cable TV Residential product_ids present in DB.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<String> GetExistingRCTVProducts(){
		
		List<String> rctv_List = new ArrayList<String>();
		
		try{
			String sql = ConfigManager.getSqlStatement("getExistingRCTVproduct");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			rctv_List = jdbcTemplate.query(sql, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
				{
						return rs.getString(1);
				}
			});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Bulk Child Cable TV Residential SELECT (Fetch Existing records) Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Bulk Child Cable TV Residential SELECT (Fetch Existing records) Error! : " + e.toString());
        	
			e.printStackTrace();
		}
		
		return rctv_List;
	}
	
	/**
	 * 
	 * @param id
	 * Delete Bulk Child Cable TV Residential product that is present in DB but not in latest Bulk Child Cable TV Residential CSV file.
	 */
	public void DeleteRCTVProduct(String id){
		try{
			String sql = ConfigManager.getSqlStatement("deleteRCTVproduct");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			jdbcTemplate.update(sql, new Object[] {id});
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
		}
		catch (DataAccessException e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Bulk Child Cable TV Residential DELETE (Delete record) Error! : " + e.toString());
        	
			e.printStackTrace();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.cronerror("Bulk Child Cable TV Residential DELETE (Delete record) Error! : " + e.toString());
        	
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Prem
	 * @return
	 * To get the GST configuration stored in DB.
	 */
	public String getGSTConfig(){
		
		String gst = "";
		try {
			String sql = ConfigManager.getSqlStatement("getGSTConfig");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			gst = jdbcTemplate.queryForObject(sql, String.class);
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));

			
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return gst;
	}
	
	public String CheckIfBulkRCTVProductExistInCC(String product_id){
		String exists_in_CC = "false";
		List<Product> RCTVSearchList = new ArrayList<Product>();
		try{
			String sql = ConfigManager.getSqlStatement("checkRCTVproductExistUnderCC");
			LogHelper.croninfo("Bulk Child Cable TV Residential Product ID : " + product_id);
			LogHelper.debug("Bulk Child Cable TV Residential Product ID : " + product_id);
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			RCTVSearchList = jdbcTemplate.query(sql, new Object[]{product_id}, new PMP_DBRowMapper());
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			if(RCTVSearchList.size() == 0){
				exists_in_CC = "false";
			}else{
				exists_in_CC = "true";
			}
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			exists_in_CC = "error";
		} catch (Exception e) {
			e.printStackTrace();
			exists_in_CC = "error";
		}
		
		return exists_in_CC;
	}

	/**
	 * @author Willkie
	 * 
	 */
	public DBQueryInfo insertIntoEntitlementSnychronizerMapping (String contentid, String productid, String name, String display_name) {
		int affected = 0;
		
		try {
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			String sql = ConfigManager.getSqlStatement("insertIntoEntitlementSnychronizerMapping");

			affected = jdbcTemplate.update(sql, new Object[] {contentid, productid, name, display_name});	
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
		}
		catch (Exception e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		}
		
		if (affected == 1) {
			LogHelper.info("Successfully ESM info into DB: "
					+ contentid + ", " + productid + ", " +
					name + ", " + display_name);
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to ESM info into DB: " + contentid + ", " + productid + ", " +
					name + ", " + display_name
					+ ", affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,",
					null);
		}
	}
	
	public DBQueryInfo deleteAllEntitlementSnychronizerMapping () {
		int affected = 0;
		
		try {
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			String sql = ConfigManager.getSqlStatement("deleteAllEntitlementSnychronizerMapping");

			affected = jdbcTemplate.update(sql);	
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
		}
		catch (Exception e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		}
		List<EntitlementSynchronizerMapping> esm_list = getAllEntitlementSnychronizerMapping ();
		if (esm_list.size() == 0) {
			LogHelper.info("Successfully delete all ESM info in DB.");
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		} else {
			LogHelper.info("Failed to delete ESM info in DB. : affected rows=" + affected);
			return new DBQueryInfo(ConstCode.ERR_GENERAL, "Error happens,",
					null);
		}
	}
	
	/**
	 * @author Willkie
	 * 
	 */
	public List<EntitlementSynchronizerMapping> getAllEntitlementSnychronizerMapping () {
		List<EntitlementSynchronizerMapping> esm_list = new ArrayList<EntitlementSynchronizerMapping>();
		
		try {
			String sqlsmt = ConfigManager.getSqlStatement("getAllEntitlementSnychronizerMapping");
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);

			esm_list = jdbcTemplate.query(sqlsmt, new ESMRowMapper());
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			LogHelper.debug("SQL Statement: " + sqlsmt);
			LogHelper.debug("Query result: " + esm_list.size());
			
			return esm_list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return esm_list;
	}
	
	/**
	 * @author Willkie
	 * @param ProductIDList
	 * @return
	 */
	@SuppressWarnings({ "unused" })
	public DBQueryInfo getESMByProductIDList(List<String> ProductIDList) {

		LogHelper.info("Query by ProductIDList: " + ProductIDList);

		List<EntitlementSynchronizerMapping> esm_list = new ArrayList<EntitlementSynchronizerMapping>();
		
		if (Validators.isEmpty(ProductIDList)) {
			return new DBQueryInfo(ConstCode.ERR_GENERAL,
					"Empty input ProductIDList", esm_list);
		}

		try {
			String sql = ConfigManager
					.getSqlStatement("getEntitlementSnychronizerMappingByProductIds");

			StringBuffer strb = new StringBuffer();
			for (String pd : ProductIDList) {
				strb.append("?,");
			}
			strb.setLength(strb.length() - 1);
			sql = sql.replaceAll("@LIST@", strb.toString());
			strb.setLength(0);

			// DBQuery profiling
			long time1 = System.currentTimeMillis();
			LogHelper.debug("------ DBQuery START ------");
			LogHelper.debug("SQL statement: " + sql + " with params: "
					+ ProductIDList);

			esm_list = jdbcTemplate.query(sql, ProductIDList.toArray(), new ESMRowMapper());

			long timeSpent = System.currentTimeMillis() - time1;
			LogHelper.debug("------ DBQuery END, timeSpent="
					+ timeSpent + " ------");

			LogHelper.debug("Query result: " + esm_list);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.toString(), esm_list);
		} catch (Exception e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_GENERAL, e.toString(), esm_list);
		}
		return new DBQueryInfo(ConstCode.ERR_OK, null, esm_list);
	}
	
	/**
	 * @author Willkie
	 * @return
	 */
	public List<ActionLog> getCheckerInfoFromActionLog(String hubid, String starttime, String endtime, String extra) {
		List<ActionLog> aLog = new ArrayList<ActionLog>();
		try {
			String sqlsmt =  "";
			String transaction_type = "TRANSACTION";
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);

			sqlsmt =  ConfigManager.getSqlStatement("getCheckerDetails");
			aLog = jdbcTemplate.query(sqlsmt, new Object[] {hubid, starttime, endtime}, new DBActionLogRowMapper());
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			LogHelper.debug("SQL Statement: " + sqlsmt);
			LogHelper.debug("Query result: " + aLog.size());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return aLog;
	}
	
	/**
	 * @author Willkie
	 * @param ContentIDList
	 * @return
	 */
	@SuppressWarnings({ "unused" })
	public DBQueryInfo getESMByContentIDList(List<String> contentIDList) {

		LogHelper.info("Query by ProductIDList: " + contentIDList);

		List<EntitlementSynchronizerMapping> esm_list = new ArrayList<EntitlementSynchronizerMapping>();
		
		if (Validators.isEmpty(contentIDList)) {
			return new DBQueryInfo(ConstCode.ERR_GENERAL,
					"Empty input ProductIDList", esm_list);
		}

		try {
			String sql = ConfigManager
					.getSqlStatement("getEntitlementSynchronizerMappingByContentIds");

			StringBuffer strb = new StringBuffer();
			for (String pd : contentIDList) {
				strb.append("?,");
			}
			strb.setLength(strb.length() - 1);
			sql = sql.replaceAll("@LIST@", strb.toString());
			strb.setLength(0);

			// DBQuery profiling
			long time1 = System.currentTimeMillis();
			LogHelper.debug("------ DBQuery START ------");
			LogHelper.debug("SQL statement: " + sql + " with params: "
					+ contentIDList);

			esm_list = jdbcTemplate.query(sql, contentIDList.toArray(), new ESMRowMapper());

			long timeSpent = System.currentTimeMillis() - time1;
			LogHelper.debug("------ DBQuery END, timeSpent="
					+ timeSpent + " ------");

			LogHelper.debug("Query result: " + esm_list);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.toString(), esm_list);
		} catch (Exception e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_GENERAL, e.toString(), esm_list);
		}
		return new DBQueryInfo(ConstCode.ERR_OK, null, esm_list);
	}
	
	/**
	 * Author: Prem
	 * @param content_id
	 * @return
	 * To check if offers need to be renamed to display_name.
	 */
	public boolean Content_id_match_found(String content_id){
		boolean match_found = false;
		try{
			String sql = ConfigManager.getSqlStatement("checkIfNeedToRenameOffer");
			LogHelper.debug(sql); //remove later
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			int count = jdbcTemplate.queryForObject(sql, new Object[]{content_id}, Integer.class);
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			if(count == 1){
				match_found = true;
			}
		}
		catch (Exception e) {
			StringWriter sw = new StringWriter();
        	e.printStackTrace(new PrintWriter(sw));
        	System.out.println(sw.toString());
        	
        	LogHelper.debug("Error finding matching content_id! : " + e.toString());
        	
			e.printStackTrace();
		}
		
		return match_found;
	}
	
	/**
	 * Author: Prem
	 * @return
	 * Backup existing ESM table by copying existing ESM table to a backup table.
	 */
	public DBQueryInfo backupEntitlementSnychronizerMapping(){
		try {
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			String sql = ConfigManager.getSqlStatement("createEntitlementSnychronizerMappingTableBackup");
			jdbcTemplate.update(sql);
			LogHelper.debug(sql); //remove later
			
			sql = ConfigManager.getSqlStatement("populateEntitlementSnychronizerMappingTableBackup");
			jdbcTemplate.update(sql);
			LogHelper.debug(sql); //remove later
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
			
			LogHelper.info("Backup of Entitlement Synchronizer Mapping Table successful.");
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		}
	}
	
	/**
	 * Author : Prem
	 * @return
	 * Roll back ESM table data
	 */
	public DBQueryInfo rollbackEntitlementSnychronizerMapping(){
		try {
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			List<EntitlementSynchronizerMapping> esm_list = getAllEntitlementSnychronizerMapping ();
			if (esm_list.size() != 0) {
				String sql = ConfigManager.getSqlStatement("deleteAllEntitlementSnychronizerMapping");
				jdbcTemplate.update(sql);	
				LogHelper.debug(sql); //remove later
			}
			
			String sql = ConfigManager.getSqlStatement("rollbackEntitlementSnychronizerMappingTable");
			jdbcTemplate.update(sql);
			LogHelper.debug(sql); //remove later
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
			
			LogHelper.info("Rollback of Entitlement Synchronizer Mapping Table successful.");
			return new DBQueryInfo(ConstCode.ERR_OK, null, null);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new DBQueryInfo(ConstCode.ERR_DB_EXCEPTION, e.getMessage(),
					null);
		}
	}
	
	/**
	 * Author : Prem
	 * Delete ESM backup table after done processing.
	 */
	public void dropEntitlementSnychronizerMappingBackup(){
		try {
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);
			
			String sql = ConfigManager.getSqlStatement("dropEntitlementSnychronizerMappingBackup");
			jdbcTemplate.update(sql);	
			LogHelper.debug(sql); //remove later
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
				+ "||t=" + (time2 - time1));
			
			LogHelper.info("Deletion of Entitlement Synchronizer Mapping Table backup successful.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Willkie
	 * @param hubid
	 * @param adminid
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public List<ActionLog> getAuditInfoFromActionLog(String hubid, String adminid, String starttime, String endtime) {
		List<ActionLog> aLog = new ArrayList<ActionLog>();
		try {
			String sqlsmt =  "";
			
			long time1 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1);

			if (Validators.isNotEmpty(hubid) && Validators.isNotEmpty(adminid)) {
				sqlsmt =  ConfigManager.getSqlStatement("getAuditDetailsWithHubIdAndAdminId");
				aLog = jdbcTemplate.query(sqlsmt, new Object[] {hubid, adminid, starttime, endtime}, new DBActionLogRowMapper());
			}
			else if (Validators.isNotEmpty(hubid) && Validators.isEmpty(adminid)) {
				sqlsmt =  ConfigManager.getSqlStatement("getAuditDetailsWithHubIdAndWithoutAdminId");
				aLog = jdbcTemplate.query(sqlsmt, new Object[] {hubid, starttime, endtime}, new DBActionLogRowMapper());
			}
			else if (Validators.isNotEmpty(hubid) && Validators.isEmpty(adminid)) {
				sqlsmt =  ConfigManager.getSqlStatement("getAuditDetailsWithoutHubIdAndWithAdminId");
				aLog = jdbcTemplate.query(sqlsmt, new Object[] {adminid, starttime, endtime}, new DBActionLogRowMapper());
			}
			else {
				sqlsmt =  ConfigManager.getSqlStatement("getAuditDetailsWithoutHubIdAndAdminId");
				aLog = jdbcTemplate.query(sqlsmt, new Object[] {starttime, endtime}, new DBActionLogRowMapper());
			}
			
			long time2 = System.currentTimeMillis();
			LogHelper.debug("DBQueryTimeCheck ||s=" + time1 + "||e=" + time2
					+ "||t=" + (time2 - time1));
			
			LogHelper.debug("SQL Statement: " + sqlsmt);
			LogHelper.debug("Query result: " + aLog.size());

		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aLog;
	}
}
