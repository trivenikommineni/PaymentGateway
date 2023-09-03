package com.paymentgateway.dto;

import org.springframework.lang.NonNull;

import jakarta.validation.constraints.NotNull;

public class Payment {
	
	@NotNull
	private String merchantId;
	
	@NotNull
	private double amount;
	
	@NotNull
	private String currency;
	
	@NotNull
	private String orderId;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
