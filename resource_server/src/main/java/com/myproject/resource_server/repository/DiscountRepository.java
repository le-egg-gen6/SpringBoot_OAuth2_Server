package com.myproject.resource_server.repository;

import com.myproject.resource_server.model.Discount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long> {

    Optional<Discount> findByCode(String code);

}