package com.ufinity.ott.common.cons;

public interface ActionType {

	// For OTT ActionLog
	public static final String PURCHASE = "PURCHASE";
	public static final String DELETE = "DELETE";
	public static final String LOGIN = "LOGIN";
	public static final String TRANSACTION = "TRANSACTION";
	public static final String AUDIT = "AUDIT";

	public static final String CREATE = "CREATE";
	public static final String CHANGE = "CHANGE_HUB_ID";
	public static final String UPGRADE = "UPGRADE";
	public static final String DOWNGRADE = "DOWNGRADE";

	public static final String ACTIVATE = "ACTIVATE";

	public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";

	public static final String ADD_MAPPING = "ADD_MAPPING";
	public static final String REMOVE_MAPPING = "REMOVE_MAPPING";

	// hub report action
	public static final String ONLINE_ASSOCIATION = "ONLINE_ASSOCIATION";
	public static final String CSC_RECOVERY_PASS = "CSC_RECOVERY_PASS";
	public static final String CSC_RECOVERY_ID = "CSC_RECOVERY_ID";
	public static final String SELF_RECOVERY_ID = "SELF_RECOVERY_ID";
	public static final String SELF_RECOVERY_PASS = "SELF_RECOVERY_PASS";
	public static final String SEND_OTP = "SEND_OTP";

}
