package com.ufinity.ott.common.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ufinity.ott.common.config.ConfigManager;
import com.ufinity.ott.common.logging.LogHelper;
import com.ufinity.ott.common.utils.Common;
import com.ufinity.ott.common.utils.OTTSignature;
import com.ufinity.ott.domain.JsonResponseGeneral;

/**
 * 
 * @author SunBo
 * @CopyRight Ufinity - [2000-2016] All Rights Reserved
 * 
 */
public class SignatureValidationFilter extends OncePerRequestFilter {

	public static final String SIG_PARAM_NAME = "signature";
	public OTTSignature os;

	public SignatureValidationFilter() {
		os = new OTTSignature();
	}

	@Override
	public void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		boolean isValidSig = false;
		HttpServletRequest wrapped = new BodyReaderHttpServletRequestWrapper(
				request);

		try {
			String Sig = wrapped.getParameter(SIG_PARAM_NAME);

			String secretKey = ConfigManager
					.getConfig("ottgw.signature.secret_key");
			String HTTPMethod = wrapped.getMethod();
			String requestPath = wrapped.getRequestURI();
			String requestBody = Common.getRequestBody(wrapped);
			String RegenSig = os.generateSignature_noURLencode(secretKey,
					HTTPMethod, requestPath, requestBody);

			LogHelper.debug(Sig + "#_____#" + secretKey + "#_____#"
					+ HTTPMethod + "#_____#" + requestPath + "#_____#"
					+ requestBody + "#_____#" + RegenSig);

			if (RegenSig.equals(Sig)) {
				isValidSig = true;
			}
		} catch (Exception e) {
			LogHelper.error("Exception occured during signature validation.");
			e.printStackTrace();
		}

		if (isValidSig) {
			chain.doFilter(wrapped, response);
		} else {
			LogHelper.error("Invalid signature for the request: "
					+ wrapped.getRequestURL().toString());

			int StatusCode = HttpStatus.FORBIDDEN.value();
			JsonResponseGeneral jr = new JsonResponseGeneral(StatusCode,
					"Invalid Signature");
			response.setStatus(StatusCode);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			JSONObject json = new JSONObject(jr);
			response.getWriter().write(json.toString());
		}

	}

}
