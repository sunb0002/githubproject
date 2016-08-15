package com.ufinity.madoka.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * 
 * @author SunBo
 * @Upgraded from other GitHub code.
 * 
 */
public class CsrfHeaderFilter extends OncePerRequestFilter {

	public final String CSRFCookieName = "XSRF-TOKEN";
	public final String CSRFHeaderName = "X-XSRF-TOKEN";

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		CsrfToken csrf = (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());
		System.out.println("CsrfHeaderFilter hello");
		if (csrf != null) {
			String method = httpServletRequest.getMethod();
			String sessionToken = csrf.getToken();
			System.out.println(sessionToken);

			if (HttpMethod.POST.matches(method)) {
				// handlePOST(httpServletRequest, httpServletResponse,
				// sessionToken);
			} else {
				handleGET(httpServletRequest, httpServletResponse, sessionToken);
			}

		}
		System.out.println("CsrfHeaderFilter bye");
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private void handleGET(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			String sessionToken) {

		Cookie cookie = WebUtils.getCookie(httpServletRequest, "XSRF-TOKEN");
		if (cookie == null || (sessionToken != null && !sessionToken.equals(cookie.getValue()))) {
			cookie = new Cookie("XSRF-TOKEN", sessionToken);
			cookie.setPath("/");
			System.out.println("CsrfHeaderFilter add cookie");
			httpServletResponse.addCookie(cookie);
			System.out.println("CsrfHeaderFilter cookie added");
		}

	}

	// Doable, but choose not to use because Spring Security already handles
	// CSRF POST
	/*
	 * private void handlePOST(HttpServletRequest httpServletRequest,
	 * HttpServletResponse response, String sessionToken) throws IOException {
	 * 
	 * String header = httpServletRequest.getHeader(CSRFHeaderName);
	 * 
	 * System.out.println("pppppppost: " + header);
	 * 
	 * if (Validators.isEmpty(header) || !header.equalsIgnoreCase(sessionToken))
	 * { System.out.println("Invalid CSRF token, bye hacker."); int StatusCode =
	 * HttpStatus.FORBIDDEN.value(); JsonGeneral jr = new
	 * JsonGeneral(StatusCode, "Invalid CSRF token, bye hacker.");
	 * response.setStatus(StatusCode);
	 * response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	 * response.setCharacterEncoding(StandardCharsets.UTF_8.name()); JSONObject
	 * json = new JSONObject(jr); response.getWriter().write(json.toString()); }
	 * 
	 * }
	 */
}