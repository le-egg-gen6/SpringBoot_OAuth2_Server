package com.myproject.resource_server.service.impl;

import com.myproject.resource_server.converter.product.ProductDetailsResponseConverter;
import com.myproject.resource_server.converter.product.ProductResponseConverter;
import com.myproject.resource_server.converter.product.ProductVariantResponseConverter;
import com.myproject.resource_server.error.exception.InvalidArgumentException;
import com.myproject.resource_server.error.exception.ResourceNotFoundException;
import com.myproject.resource_server.model.Product;
import com.myproject.resource_server.model.ProductVariant;
import com.myproject.resource_server.model.specs.ProductVariantSpecs;
import com.myproject.resource_server.payload.response.product.ProductDetailsResponse;
import com.myproject.resource_server.payload.response.product.ProductResponse;
import com.myproject.resource_server.payload.response.product.ProductVariantResponse;
import com.myproject.resource_server.repository.ProductRepository;
import com.myproject.resource_server.repository.ProductVariantRepository;
import com.myproject.resource_server.service.ProductService;
import com.myproject.resource_server.service.cache.ProductCacheService;
import com.myproject.resource_server.service.cache.ProductVariantCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductCacheService productCacheService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private ProductVariantCacheService productVariantCacheService;

    @Autowired
    private ProductResponseConverter productResponseConverter;

    @Autowired
    private ProductVariantResponseConverter productVariantResponseConverter;

    @Autowired
    private ProductDetailsResponseConverter productDetailsResponseConverter;

    @Override
    public ProductDetailsResponse findByUrl(String url) {
        Product product = productCacheService.findByUrl(url);
        if (Objects.isNull(product)) {
            throw new ResourceNotFoundException(String.format("Product not found with the url %s", url));
        }
        return productDetailsResponseConverter.apply(product);
    }


    @Override
    public ProductVariant findProductVariantById(Long id) {
        ProductVariant productVariant = productVariantCacheService.findById(id);
        if (Objects.isNull(productVariant)) {
            throw new ResourceNotFoundException(String.format("Could not find any product variant with the id %d", id));
        }
        return productVariant;
    }

    @Override
    public List<ProductVariantResponse> getAll(Integer page, Integer size, String sort, String category, Float minPrice, Float maxPrice, String color) {
        PageRequest pageRequest;
        if (Objects.nonNull(sort) && !sort.isBlank()) {
            Sort sortRequest = getSort(sort);
            if (Objects.isNull(sortRequest)) {
                throw new InvalidArgumentException("Invalid sort parameter");
            }
            pageRequest = PageRequest.of(page, size, sortRequest);
        } else {
            pageRequest = PageRequest.of(page, size);
        }

        Specification<ProductVariant> combinations =
                Objects.requireNonNull(Specification.where(ProductVariantSpecs.withColor(color)))
                        .and(ProductVariantSpecs.withCategory(category))
                        .and(ProductVariantSpecs.minPrice(minPrice))
                        .and(ProductVariantSpecs.maxPrice(maxPrice));

        return productVariantRepository.findAll(combinations, pageRequest)
                .stream()
                .map(productVariantResponseConverter)
                .collect(Collectors.toList());
    }

    @Override
    public Long getAllCount(String category, Float minPrice, Float maxPrice, String color) {
        Specification<ProductVariant> combinations =
                Objects.requireNonNull(Specification.where(ProductVariantSpecs.withColor(color)))
                        .and(ProductVariantSpecs.withCategory(category))
                        .and(ProductVariantSpecs.minPrice(minPrice))
                        .and(ProductVariantSpecs.maxPrice(maxPrice));

        return productVariantRepository.count(combinations);
    }

    @Override
    public List<ProductResponse> getRelatedProducts(String url) {
        Product product = productCacheService.findByUrl(url);
        if (Objects.isNull(product)) {
            throw new ResourceNotFoundException("Related products not found");
        }
        List<Product> products = productCacheService.getRelatedProducts(product.getProductCategory(), product.getId());
        return products
                .stream()
                .map(productResponseConverter)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getNewlyAddedProducts() {
        List<Product> products = productCacheService.findTop8ByOrderByDateCreatedDesc();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Newly added products not found");
        }
        return products
                .stream()
                .map(productResponseConverter)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductVariantResponse> getMostSelling() {
        List<ProductVariant> productVariants = productVariantCacheService.findTop8ByOrderBySellCountDesc();
        if (productVariants.isEmpty()) {
            throw new ResourceNotFoundException("Most selling products not found");
        }

        return productVariants
                .stream()
                .map(productVariantResponseConverter)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getInterested() {
        List<Product> products = productCacheService.findTop8ByOrderByDateCreatedDesc();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Interested products not found");
        }
        return products
                .stream()
                .map(productResponseConverter)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> searchProductDisplay(String keyword, Integer page, Integer size) {
        if (Objects.isNull(page) || Objects.isNull(size)) {
            throw new InvalidArgumentException("Page and size are required");
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Product> products = productRepository.findAllByNameContainingIgnoreCase(keyword, pageRequest);
        return products
                .stream()
                .map(productResponseConverter)
                .collect(Collectors.toList());
    }


    private Sort getSort(String sort) {
        return switch (sort) {
            case "lowest" -> Sort.by(Sort.Direction.ASC, "price");
            case "highest" -> Sort.by(Sort.Direction.DESC, "price");
            default -> null;
        };
    }

}

