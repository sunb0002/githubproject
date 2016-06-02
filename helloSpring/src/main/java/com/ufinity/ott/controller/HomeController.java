package com.ufinity.ott.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufinity.ott.common.logging.LogHelper;
import com.ufinity.ott.domain.JsonResponseGeneral;
import com.ufinity.ott.domain.Uuid;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Welcome home! The client locale is {}." + locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

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
}