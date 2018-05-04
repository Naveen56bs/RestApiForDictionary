package com.dictionary.model;

import org.springframework.http.HttpStatus;

/**
 * 
 * ResponseBean would contain the response status and the message that needs to
 * be rendered to the user. ResponseBean would act as an instance variable of
 * dictionary bean and the dictionaryBean would be returned
 *
 */
public class ResponseBean {

	private HttpStatus httpStatus;
	private String message;

	public ResponseBean(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
