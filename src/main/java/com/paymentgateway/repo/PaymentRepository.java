package com.paymentgateway.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymentgateway.dao.PaymentStatus;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentStatus, String> {

	public PaymentStatus findByPaymentId(UUID paymentId);

}
