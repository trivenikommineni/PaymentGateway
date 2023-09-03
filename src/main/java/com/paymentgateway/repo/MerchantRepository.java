package com.paymentgateway.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymentgateway.dao.MerchantsData;

@Repository
public interface MerchantRepository extends CrudRepository<MerchantsData, String> {

}
