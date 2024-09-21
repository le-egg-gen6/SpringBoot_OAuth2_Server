package com.myproject.resource_server.controller;

import com.myproject.resource_server.payload.response.category.ProductCategoryResponse;
import com.myproject.resource_server.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController extends APIController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping(value = "/category")
    public ResponseEntity<List<ProductCategoryResponse>> getAllCategories() {
        List<ProductCategoryResponse> productCategories = productCategoryService.findAllByOrderByName();
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }

}
