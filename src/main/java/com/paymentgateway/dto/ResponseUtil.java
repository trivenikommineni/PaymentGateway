package com.paymentgateway.dto;

public class ResponseUtil {

	private String id;

	private String status;

	public ResponseUtil(String id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public ResponseUtil() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
