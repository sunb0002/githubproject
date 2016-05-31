package com.ufinity.ott.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ufinity.ott.common.logging.LogHelper;
import com.ufinity.ott.dao.AWSEmailClient;
import com.ufinity.ott.domain.HTTPQueryInfo;
import com.ufinity.ott.domain.JsonResponseGeneral;
import com.ufinity.ott.domain.Shopping_Cart;
import com.ufinity.ott.domain.Uuid;
import com.ufinity.ott.domain.RequestEntities.InstantBuy_RequestBody;
import com.ufinity.ott.domain.RightsLocker.RLEntity;
import com.ufinity.ott.service.CreditCardManager;
import com.ufinity.ott.service.EntitlementManager;
import com.ufinity.ott.service.TransactionManager;


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
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "home";
	}
	
	
	@RequestMapping(value = "/json", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Uuid greeting4() {
        
		Uuid sb = new Uuid();
		sb.setUuid("kaname");
		sb.setNickName("madoka");
		LogHelper.debug("JsonController: "+sb.toString());
    	
    	return sb;
    	
    }	
}