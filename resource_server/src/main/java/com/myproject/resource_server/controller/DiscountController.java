package com.myproject.resource_server.controller;

import com.myproject.resource_server.payload.request.discount.ApplyDiscountRequest;
import com.myproject.resource_server.service.DiscountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountController extends APIController {

    @Autowired
    private DiscountService discountService;

    @PostMapping(value = "/cart/discount")
    public ResponseEntity<HttpStatus> applyDiscount(@RequestBody @Valid ApplyDiscountRequest applyDiscountRequest) {
        discountService.applyDiscount(applyDiscountRequest.getCode());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
