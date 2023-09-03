package com.paymentgateway.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentgateway.dao.MerchantsData;
import com.paymentgateway.dao.PaymentStatus;
import com.paymentgateway.dto.Merchant;
import com.paymentgateway.dto.MerchantReponse;
import com.paymentgateway.dto.Payment;
import com.paymentgateway.dto.PaymentReponse;
import com.paymentgateway.exceptions.MerchantValidationException;
import com.paymentgateway.exceptions.PaymentGatewayException;
import com.paymentgateway.repo.MerchantRepository;
import com.paymentgateway.repo.PaymentRepository;

@Service
public class PaymentGatewayService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private MerchantRepository merchantRepository;

	public MerchantReponse saveMerchant(Merchant merchant) throws MerchantValidationException {
		String id = merchant.getMerchantId();

		if (merchantRepository.existsById(id)) {
			String msg = "Merchant is already available with Id:" + id;
			throw new MerchantValidationException(msg);

		} else {
			MerchantsData mdata = new MerchantsData();
			mdata.setMerchantId(merchant.getMerchantId());
			mdata.setAddress(merchant.getAddress());
			mdata.setBusinessType(merchant.getBusinessType());
			mdata.setEmail(merchant.getEmail());
			mdata.setName(merchant.getName());
			mdata.setPhone(merchant.getPhone());
			try {
				merchantRepository.save(mdata);
				return new MerchantReponse(id, "Success");

			} catch (Exception e) {
				return new MerchantReponse(id, "Failed to save Merchant cause of " + e.getMessage());
			}

		}
	}

	public PaymentReponse PaymentRequest(Payment payment) throws PaymentGatewayException {

		PaymentStatus pstatus = new PaymentStatus();
		pstatus.setAmount(payment.getAmount());
		pstatus.setCurrency(payment.getCurrency());
		pstatus.setMerchantId(payment.getMerchantId());
		pstatus.setOrderId(payment.getOrderId());

		Random random = new Random();
		int int_random = random.nextInt(2);
		String result = (int_random == 0) ? "Failed" : "Success";
		pstatus.setPaymentStatus(result);

		PaymentStatus ps = null;
		try {
			ps = paymentRepository.save(pstatus);
			return new PaymentReponse(String.valueOf(ps.getPaymentId()), result);

		} catch (Exception e) {
			throw new PaymentGatewayException(e.getMessage());
		}
	}

	public PaymentReponse checkStatus(UUID paymentId) {

		PaymentStatus paymentStatus = paymentRepository.findByPaymentId(paymentId);
		if (paymentStatus == null) {
			String msg = "PaymentId not exist";
			return new PaymentReponse(String.valueOf(paymentId), msg);
		} else {

			return new PaymentReponse(String.valueOf(paymentId), paymentStatus.getPaymentStatus());
		}

	}

}
