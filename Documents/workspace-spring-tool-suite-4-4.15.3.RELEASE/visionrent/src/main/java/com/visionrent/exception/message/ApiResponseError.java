package com.visionrent.exception.message;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiResponseError {

	//AMACIN: customize error mesajlarimi bu sinif icinde tutacagiz
	/*
	 * {
    "timestamp": "2022-11-03T12:11:11.359+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Conversion = 'i'",
    "path": "/contactmessage/request"
		}
	 */
	
	private HttpStatus status;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;
	
	//exception mesaji
	private String message;
	
	//request edilen end-pointi tutmak icin
	private String requestURI;
	
	
	//private constructor
	//obje olusurken parametresiz cont. cagirilmasin
	private ApiResponseError() {
		timestamp=LocalDateTime.now();
	}
	
	public ApiResponseError(HttpStatus status) {
		this();
		this.message="Unexpected.Error";
		this.status=status;
		
	}
	
	public ApiResponseError(HttpStatus status,String message, String requestURI) {
		this(status);//cont. da status meth.(1 parametreli olani) olani cagirani calistir
		this.message=message;
		this.requestURI=requestURI;
		
	}

	
	
	//Getter Setter  -->timestamp setlenmesin
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
}
