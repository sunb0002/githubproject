package com.ufinity.ott.common.logging;

import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @since 2015-10-04
 * @author SunBo
 * @CopyRight Ufinity - [2000-2015] All Rights Reserved
 * 
 */
@Repository
public class LogHelper {

	@Value("${configFile.log4j}")
	private String file_path;

	private static String t_fil_path;

	private static Logger info = Logger.getLogger("loginfo");
	private static Logger debug = Logger.getLogger("logdebug");
	private static Logger error = Logger.getLogger("logerror");
	private static Logger croninfo = Logger.getLogger("croninfo");
	private static Logger cronerror = Logger.getLogger("cronerror");
	private static Logger pmpaudit = Logger.getLogger("pmpaudit");

	@PostConstruct
	private void initConfig() throws Exception {

		LogHelper.t_fil_path = file_path;

		System.out.print("Loading config file from path: " + t_fil_path);
		PropertyConfigurator.configure(t_fil_path);

	}

	private static String getMessage(Object message, int wrapLevel) {
		StackTraceElement[] stes = new Exception().getStackTrace();
		if (stes.length < 3 + wrapLevel) {
			System.err.println("Cannot log:" + message);
			return null;
		} else {
			StackTraceElement target = stes[wrapLevel + 2];
			String className = target.getClassName();
			StringTokenizer st = new StringTokenizer(className, ".");
			String shortClassName = "";
			while (st.hasMoreTokens()) {
				shortClassName = st.nextToken();
			}
			String prefix = shortClassName + "." + target.getMethodName() + "("
					+ target.getFileName() + ":" + target.getLineNumber()
					+ "):";
			String toLog = prefix + message;
			return toLog;
		}

	}

	/**
	 * Log a info to INFO logger
	 * 
	 * @param message
	 *            The message to log
	 */
	public static void info(Object message) {
		// System.out.println(message);
		LogHelper.info(message, 1);
	}

	public static void info(Object message, int wrapLevel) {
		LogHelper.info.info(LogHelper.getMessage(message, wrapLevel));
	}

	/**
	 * Log a info to DEBUG logger
	 * 
	 * @param message
	 *            The message to log
	 */
	public static void debug(Object message) {
		// System.out.println(message);
		LogHelper.debug(message, 1);
	}

	public static void debug(Object message, int wrapLevel) {
		LogHelper.debug.info(LogHelper.getMessage(message, wrapLevel));
	}

	/**
	 * Log a info to CRON INFO logger
	 * 
	 * @param message
	 *            The message to log
	 */
	public static void croninfo(Object message) {
		LogHelper.croninfo(message, 1);
	}

	public static void croninfo(Object message, int wrapLevel) {
		LogHelper.croninfo.info(LogHelper.getMessage(message, wrapLevel));
	}

	/**
	 * Log a info to CRON ERROR logger
	 * 
	 * @param message
	 *            The message to log
	 */
	public static void cronerror(Object message) {
		LogHelper.cronerror(message, 1);
	}

	public static void cronerror(Object message, int wrapLevel) {
		LogHelper.cronerror.info(LogHelper.getMessage(message, wrapLevel));
	}
	
	/**
	 * Log a info to PMP AUDIT logger
	 * 
	 * @param message
	 *            The message to log
	 */
	public static void pmpaudit(Object message) {
		LogHelper.pmpaudit(message, 1);
	}

	public static void pmpaudit(Object message, int wrapLevel) {
		LogHelper.pmpaudit.info(LogHelper.getMessage(message, wrapLevel));
	}

	/**
	 * Log a info to ERROR logger
	 * 
	 * @param message
	 *            The message to log
	 */
	public static void error(Object message) {
		// System.out.println(message);
		LogHelper.error(message, 1);
	}

	public static void error(Object message, int wrapLevel) {
		LogHelper.error.info(LogHelper.getMessage(message, wrapLevel));
	}

	public static void error(Object message, Throwable cause) {
		LogHelper.error(message, cause, 1);
	}

	public static void error(Object message, Throwable t, int wrapLevel) {
		LogHelper.error.info(LogHelper.getMessage(message, wrapLevel));
		if (t == null) {
			LogHelper.error.info("    null throwable passed......");
			return;
		}
		StackTraceElement[] stes = t.getStackTrace();
		for (int i = 0; i < stes.length; i++) {
			LogHelper.error.info("    at " + stes[i]);
		}
	}

}
