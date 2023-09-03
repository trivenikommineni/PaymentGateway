package com.paymentgateway.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymentgateway.dto.Merchant;
import com.paymentgateway.dto.MerchantReponse;
import com.paymentgateway.dto.Payment;
import com.paymentgateway.dto.PaymentReponse;
import com.paymentgateway.exceptions.MerchantValidationException;
import com.paymentgateway.exceptions.PaymentGatewayException;
import com.paymentgateway.service.PaymentGatewayService;

import jakarta.validation.Valid;

@RestController
public class PaymentGatewayController {
	
	@Autowired
	private PaymentGatewayService service;

	@PostMapping("/register_merchant")
	public MerchantReponse saveMerchant(@Valid @RequestBody Merchant merchant) throws MerchantValidationException {
		MerchantReponse status = service.saveMerchant(merchant);
		return status;
	}

	@PostMapping("/payment")
	public PaymentReponse  paymentRequest(@Valid @RequestBody Payment payment) throws PaymentGatewayException {
		PaymentReponse status = service.PaymentRequest(payment);
		return status;
	}

	@GetMapping("/payment_status/{paymentId}")
	public PaymentReponse paymentStatus(@PathVariable UUID paymentId) {
		PaymentReponse status = service.checkStatus(paymentId);
		return status;	
	}

}
