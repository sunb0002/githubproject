package com.ufinity.ott.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufinity.ott.common.logging.LogHelper;
import com.ufinity.ott.domain.DeviceProfile;
import com.ufinity.ott.domain.JsonResponseGeneral;
import com.ufinity.ott.domain.UserProfile;
import com.ufinity.ott.domain.Uuid;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(value = "/json", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public JsonResponseGeneral greeting4() {

		Uuid sb = new Uuid();
		sb.setUuid("kaname");
		sb.setNickName("madoka");
		LogHelper.info("JsonController: " + sb.toString());

		JsonResponseGeneral jr = new JsonResponseGeneral();
		jr.setMESSAGE(sb.toString());

		return jr;

	}

	@RequestMapping(value = "/jp", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<UserProfile> greeting1() {

		List<String> whitelist = new ArrayList<String>();
		List<String> blacklist = new ArrayList<String>();

		DeviceProfile d1 = new DeviceProfile("Madoka", "mobile", true);
		DeviceProfile d2 = new DeviceProfile("92475300", "mobile", false);
		DeviceProfile d3 = new DeviceProfile("Homu", "broadband", true);

		whitelist.add("www.kidssite.com");
		whitelist.add("safewebsite.sg");
		blacklist.add("www.blockedsite.com");
		blacklist.add("anotherblockedsite.com");
		blacklist.add("violencecontent.net");

		d3.setAllow_sites(whitelist);
		d3.setRestrict_sites(blacklist);

		List<String> whitelist2 = new ArrayList<String>(whitelist);
		List<String> blacklist2 = new ArrayList<String>(blacklist);
		blacklist2.add("security-threat-content.com");
		blacklist2.add("www.adultsite.com");
		whitelist2.add("www.madoka.com");

		d1.setAllow_sites(whitelist2);
		d1.setRestrict_sites(blacklist2);
		
		d1.setParent_access(true);
		d3.setParent_access(false);
		d1.setNumber("87654321");
		d3.setNumber("12345678");
		
		UserProfile sb = new UserProfile("hubid@ufinity.com");
		sb.setFull_name("Patricia Tan Siu Gek");
		sb.setDevices(Arrays.asList(d1, d2, d3));

		return ResponseEntity.ok(sb);
	}

}