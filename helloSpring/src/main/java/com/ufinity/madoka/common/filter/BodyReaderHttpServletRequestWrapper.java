package com.ufinity.madoka.common.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

//this class stops reading the request payload twice causing an exception
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private String _body;
	private HttpServletRequest _request;

	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		_request = request;

		_body = "";
		try (BufferedReader bufferedReader = request.getReader()) {
			String line;
			while ((line = bufferedReader.readLine()) != null)
				_body += line;
		}
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(_body.getBytes());
		return new ServletInputStream() {
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}
}