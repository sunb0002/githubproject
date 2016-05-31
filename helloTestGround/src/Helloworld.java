import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class Helloworld {

	
	public static void main(String[] args) {
	
		//test9();		
	testtemp();
		//testRightsLockerPOST();
		//testRightsLockerGET();
		
		//testOTTSignaturePOST();
		
	}
	
	public static void testtemp() {
		
		String url="S 7YyLuTgDCLGRbbasScRj/BCmh7CB4fua83UHk3lEoROuBNoAQyQ/N0xn5m/PHJxgyqqntx4QQfpwDLomJHdwPHuAVx4BzRUmvrTy8J5ftPuzttnL34HS573JIRxvylqkw5rWSenSO4egsA4mcsaZ8FZ5hhEsO73D94gsaFBUQqqCzWBKpnbGRm rKCQbu6V4zlOiQUxeCBmuQZDJpIrCgqzvlEqyPzhzv0Vybl6RTces7nLm7RTzrsjz87nybUts2EtL/2xKSUD1Flhe6 gLeaHXTqTpK cQrczrU0E4fbWp1bMTV5H5EKCd5nmhSkdsHm5Jd7vTgaK2X0lz8QwrUiB66bm1QPS8gFL23PKbKpeErogO3CF2UnYuZK9CEUeuw9wDPbZsEbvJ6xFFhh3fMCAZYdCBXkkkTvJa/bGSzF6gOXHaI/X3YNP2gqiOgX";		
		String url2=url.replaceAll(" ", "+");
		
		System.out.println(url2);
		
		
	}
	
	
	public static <T> T getService(String key, String method, Class<T> clazz) {
		
		try {
			T t = clazz.newInstance();
			System.out.println(key + " WWWWWWWWWWWWWWWWW " + t.toString());
			return t;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error liao");
			return null;
		}
	}
	
	
	
	
	
	
	public static void test1() { //GENERAL USE
		String abba = "http://localhost:8070/cgi-bin/printenv.pl?vctk3=TuzkmSEIVvPioRZClq4Hi4jPCxI3BA4o%2Fepx4dcIwJV575sj7x3DunhZ7BhZJuZIHxDbJBq%2BUGwjC70Ed5qr0%2Fv9KOrMU%2FXm%2FBVEfql%2BdciP0SKcY2IZsZzMz2z%2Bsr95Jg%2Ft6wt2TiAJ%2FoG1EOrtXfmMompTElBQoyVcjnUcaHYv%2Bh1irrTD6QTdm%2Btdi%2BxOkr1GiE70gjP7stn4FmgfM0897wY6BbEzR2PRxC9UG152ek5D2jnZTv7M8eDnQoE3SC86339dBhYZICBsBGVSuCZc%2F9jKEctNfE%2BgwNc2Iiyjs5Rt%2BZhLMQE7w7dT7UG1lmvwMSW%2FxCCyGsb3SbR5hAwsGnET9fhzy5Hi3miYtpQQp6USACeMdFAWvIrsYXZOaMHyRhGaBuohO6GypiC3Ja3Cc%2BjWJSwcmyJpKdM90PUFZ%2By4eO%2FFcC%2FfuOY2yzxWWX05aFG1sa8%3D";
		
		String abbaout = abba.substring(9, 11);

		
		
		//String mysql5 = abba.replaceAll("@email", "K_K_K");
		String mysql5 = abba.replaceAll("vctk3=", "vctk_invalid=");
		
		System.out.println(mysql5);

		
		
		return;
	}

	public void test2() { //HASHMAP TEST
		Map a = new LinkedHashMap();

		a.put("na  me1", "abcdef");
		a.put("name2", "me");
		a.put("name3", "you");
		a.put("name4", "he");
		a.put("name1", "you are me");
		a.put("name1", "youwwwwwwwwwwwwwwwwwe");
		a.put("name5", "youwwwxwwwe");
		
		//System.out.println(a);
		
		/*Iterator it = a.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			System.out.println("key:" + key);
			System.out.println("value:" + a.get(key));
		}*/

		for (Object id : a.keySet())
		{
			System.out.println(id + " " + a.get(id));
		}
		
		System.out.println("UuidAllDump finished. Output file is in the Delta Dump folder. \n Please keep secret to the user data.");
	}

	
	public static void test3() {
		String temp="";
		String ss="525053101340455&97774362:525053201195303&81685514:525058101144205&93858758:525053100030303&90051805:525058189207315&85978824:525058101329628&85979036";
		
		/*
		int count=ss.indexOf('&');
		if (count>0) temp=ss.substring(0, count);
		else temp=ss;
		System.out.println(temp);*/
		int AHRIMSItemp;
		String tempwlImsi, tempwlmsisdn="";
		
		StringTokenizer wlImsiT = new StringTokenizer(ss,":"); //TOKENizer CAnnot get null token
		while (wlImsiT.hasMoreTokens()) {
			String wlImsiToken = wlImsiT.nextToken();
			System.out.println(wlImsiToken);
			
			AHRIMSItemp=wlImsiToken.indexOf('&');
			if (AHRIMSItemp > 0) {
				tempwlImsi=wlImsiToken.substring(0, AHRIMSItemp);
				tempwlmsisdn=wlImsiToken.substring(AHRIMSItemp+1);
			}
			else tempwlImsi=wlImsiToken;
			
			
			
			System.out.println("AHR IMSI substring captured >>>> " + tempwlImsi + " <<<< Error if MSISDN not found in ESPS"); //Sun Bo's log
			System.out.println("AHR MSISDn substring captured >>>> " + tempwlmsisdn + " <<<< Error if MSISDN not found in ESPS"); //Sun Bo's log2
			
		}
		
/*		String[] st=ss.split(",");
		int i, abc=st.length;
		List<String> kkk=new ArrayList<String>();
		for (i=0; i<abc; i++)
		{
			kkk.add(st[i]);
		}
		
		i=1;
		
		for (String yy:kkk)
		{
			System.out.println("LINE"+i+++">>"+yy);
		}*/
	}
	
	public static void test4() {
		
		String delimiter  = "#_#";
		//String incoming_plain = "LOGIN_ID=johntan@gmail.com#_#PASSWORD=wertgbsW7uj";
		String incoming_plain = "LOGIN_ID=johntan@gmail.com#_#PASSWORD#_#PASSWORD222";
		String Hubid_raw, Pwd_raw = null;
		String hubId, password = null;
		
		String rawlist[] = incoming_plain.split(delimiter, 2);
		if (rawlist[0].indexOf("LOGIN_ID")>=0)
		{ Hubid_raw = rawlist[0]; Pwd_raw = rawlist[1];	}
		else
		{ Hubid_raw = rawlist[1]; Pwd_raw = rawlist[0];	}
		
		
			
			System.out.println(Hubid_raw);
			System.out.println(Pwd_raw);
		
		 
			
	}
	
	public static void test5() {
		
		long expirydate = System.currentTimeMillis();
		String date2="100A00";
		
		try {
		long date22=Long.parseLong(date2);
		System.out.println("Match: "+ (expirydate>date22) );
		System.out.println("Current: "+expirydate);
		
		} catch (NumberFormatException e) {
			System.out.println("Bad para");
			System.out.println(e);
			System.out.println();
			
			e.printStackTrace();
				}
			
	}

public static void test6() {
	
	HashMap hm=new HashMap<String, String>();
	hm.put("h1", 1);
	hm.put("h2", 2);
	hm.put("h3", 3);
	
	for (Object keyobj: hm.keySet()) {
		String key=(String)keyobj;
		
		System.out.println( key );
		System.out.println( hm.get(key) );
		
		
	}
}
	
public static void test7() {

	// final String passPattern = "\\w*(?=\\w*\\d)(?=\\w*[a-zA-Z])\\w*";
	//final String passPattern = "^\\w[-!@$%^&*()_+|~`{}\\[\\]:\";<>?,.\\\\\\w]*$";
	String x= "172.20.10.14*";
	String passPattern = x.replaceAll("\\?", "\\\\w").replaceAll("\\*", "\\\\w*");
	//final String passPattern = "(^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$)";
	String password = "172.20.10.1455";

	System.out.println(passPattern);
	if (Pattern.matches(passPattern, password)) {
		System.out.println("Yes");
	} else {
		System.out.println("No");
	}

}

public static void test8() {
	List dobDropYears=new ArrayList();
	Calendar calen=new GregorianCalendar();
	for (int i=1900;i<=(calen.YEAR);i++) {
		dobDropYears.add(String.format("%04d", i));
	}

		System.out.println(calen.get(Calendar.YEAR));
	
}

public static void test9() {

	// Create a couple ArrayList objects and populate them
	// with some delicious fruits.
	List firstList = new ArrayList() {{
	    add("apple");
	    add("orange");
	    add("pinapple");
	}};

	List secondList = new ArrayList() {{
	    add("apple");
	    add("orange");
	    add("banana");
	    add("strawberry");
	}};

	// Show the "before" lists
	System.out.println("First List: " + firstList);
	System.out.println("Second List: " + secondList);

	// Remove all elements in firstList from secondList
	List secondList_clone = new ArrayList(secondList);
	List firstList_clone = new ArrayList(firstList);
	secondList_clone.removeAll(firstList);
	firstList_clone.removeAll(secondList);
	
	// Show the "after" list
	System.out.println("Result: " + secondList_clone);
	System.out.println("Result: " + firstList_clone);
	
}

public static void testRightsLockerPOST() {
	
	OoyalaSignature os = new OoyalaSignature();
	String secretKey = "-M2eGzaCyQkx6-JPLpZsc9tVvc0wVnb_ijUZyZtS";
	String HTTPMethod = "POST";
	String requestPath = "/v2/entitlements/providers/RtdWQyOkHZu1Cf80gBYS_2mS3K-d/accounts/p1506001_44@outlook.com/content";
	HashMap<String, String> parameters = new HashMap<String, String>();
	parameters.put("api_key", "RtdWQyOkHZu1Cf80gBYS_2mS3K-d.qZPsh");
	
	long expiretime = System.currentTimeMillis() / 1000 + 86400L; // Current time plus 1 day
	String expires = Long.toString(expiretime);
	
	//expires = "1446678211";
	
	parameters.put("expires", expires); 
	// {"assets":[{"content_id":"tomoemami","publishing_rule_id":"madoka","external_product_id":"CTVR-20028"}]}
	// "{\"assets\":[{\"content_id\":\"tomoemami\",\"publishing_rule_id\":\"madoka\",\"external_product_id\":\"CTVR-20028\"}]}"
	String requestBody = "{\"assets\":[{\"start_time\":\"2015-11-18T11:11:04+00:00\",\"content_id\":\"IzNnllMjphu2XF3_UgPROoCi9B2BwtSg1\",\"end_time\":\"2015-11-28T11:11:04+00:00\",\"external_product_id\":\"PPV_0000000016\"}]}";
	
	try {
		String sig = os.generateSignature(secretKey, HTTPMethod,
				requestPath, parameters, requestBody);
		System.out.println("https://rl-staging.ooyala.com" + requestPath
				+ "?api_key=" + parameters.get("api_key") + "&expires="
				+ parameters.get("expires") + "&signature=" + sig);
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

	public static void testRightsLockerGET() {

		OoyalaSignature os = new OoyalaSignature();
		String secretKey = "-M2eGzaCyQkx6-JPLpZsc9tVvc0wVnb_ijUZyZtS";
		String HTTPMethod = "GET";
		String requestPath = "/v2/entitlements/providers/RtdWQyOkHZu1Cf80gBYS_2mS3K-d/accounts/camhay_09@yahoo.com/content";
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("api_key", "RtdWQyOkHZu1Cf80gBYS_2mS3K-d.qZPsh");
		
		long expiretime = System.currentTimeMillis() / 1000 + 86400L; // Current time plus 100 minutes
		String expires = Long.toString(expiretime);
		
		//expires = "1446678211";
		
		parameters.put("expires", expires);
		String requestBody ="" ;
				
		try {
			String sig = os.generateSignature(secretKey, HTTPMethod,
					requestPath, parameters, requestBody);
			System.out.println("https://rl.ooyala.com" + requestPath
					+ "?api_key=" + parameters.get("api_key") + "&expires="
					+ parameters.get("expires") + "&signature=" + sig);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public static void testRightsLockerDELETE() {

		OoyalaSignature os = new OoyalaSignature();
		String secretKey = "-M2eGzaCyQkx6-JPLpZsc9tVvc0wVnb_ijUZyZtS";
		String HTTPMethod = "DELETE";
		String requestPath = "/v2/entitlements/providers/RtdWQyOkHZu1Cf80gBYS_2mS3K-d/accounts/sunb0002/content/assets/kanamemadoka/external_products/CTVR-20028";
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("api_key", "RtdWQyOkHZu1Cf80gBYS_2mS3K-d.qZPsh");
		
		long expiretime = System.currentTimeMillis() / 1000 + 86400L; // Current time plus 100 minutes
		String expires = Long.toString(expiretime);
		
		//expires = "1446678211";
		
		parameters.put("expires", expires);
		String requestBody ="" ;
				
		try {
			String sig = os.generateSignature(secretKey, HTTPMethod,
					requestPath, parameters, requestBody);
			System.out.println("https://api.ooyala.com" + requestPath
					+ "?api_key=" + parameters.get("api_key") + "&expires="
					+ parameters.get("expires") + "&signature=" + sig);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void testOTTSignaturePOST() {

		OTTSignature os = new OTTSignature();
		String secretKey = "Ufinity2016";
		String HTTPMethod = "POST";
		String requestPath = "/ott/api/v1/Product/LysisInput";

		String requestBody = "[{\"lysis_id\":\"TVODPPV_Ufinity_test_53.50_172800_00000001\",\"category\":\"PPV\",\"gst_flag\":true,\"price_net\":30,\"price_final\":53.50,\"rental_period\":172800,\"type\":\"NEW\",\"description\":\"UfinityTestLysisInputData1\",\"test_field\":\"testing\"}]";

		try {
			String sig = os.generateSignature(secretKey, HTTPMethod,
					requestPath, requestBody);
			System.out.println("https://uat.gw.starhubgo.com" + requestPath
					+ "?signature=" + sig);
		} catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}