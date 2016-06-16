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
import com.ufinity.ott.domain.Device;
import com.ufinity.ott.domain.JPUser;
import com.ufinity.ott.domain.JsonResponseGeneral;
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
	public ResponseEntity<JPUser> greeting1() {

		List<String> whitelist = new ArrayList<String>();
		List<String> blacklist = new ArrayList<String>();

		Device d1 = new Device("Mike2", "mobile");
		Device d2 = new Device("92475300", "mobile");
		Device d3 = new Device("Home", "broadband");

		whitelist.add("www.kidssite.com");
		whitelist.add("safewebsite.sg");
		blacklist.add("www.blockedsite.com");
		blacklist.add("anotherblockedsite.com");
		blacklist.add("violencecontent.net");

		d3.setAllow_sites(whitelist);
		d3.setRestrict_sites(blacklist);

		blacklist.add("security-threat-content.com");
		blacklist.add("www.adultsite.com");
		whitelist.add("www.madoka.com");

		d1.setAllow_sites(whitelist);
		d1.setRestrict_sites(blacklist);

		JPUser sb = new JPUser("hubid@hotmail.com");
		sb.setFull_name("Patricia Tan Siu Gek");
		sb.setDevices(Arrays.asList(d1, d2, d3));

		return ResponseEntity.ok(sb);
	}

}