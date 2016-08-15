package com.ufinity.madoka.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufinity.madoka.common.logging.LogHelper;
import com.ufinity.madoka.domain.ResponseEntities.JsonGeneral;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/")
public class HomeController {

	@RequestMapping(value = "/json", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<JsonGeneral> allhail() {

		JsonGeneral jg = new JsonGeneral(1000, "All hail madoka");
		LogHelper.info("waifu here.");
		return ResponseEntity.ok(jg);

	}

	@RequestMapping(value = "/postjson", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<JsonGeneral> allhail2() {

		JsonGeneral jg = new JsonGeneral(1000, "Protected madoka");
		LogHelper.info("protected waifu here.");
		return ResponseEntity.ok(jg);

	}

}