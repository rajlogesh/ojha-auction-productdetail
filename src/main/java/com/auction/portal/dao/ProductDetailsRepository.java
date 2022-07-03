package com.auction.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.entity.CompanyDetailsEntity;

public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Long>{

}
