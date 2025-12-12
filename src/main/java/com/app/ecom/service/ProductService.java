package com.app.ecom.service;

import com.app.ecom.dto.ProductDto;
import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.convertor.ProductConvertor;
import com.app.ecom.dto.convertor.ProductDtoConvertor;
import com.app.ecom.model.Product;
import com.app.ecom.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    private final ProductConvertor productConvertor;

    private final ProductDtoConvertor productDtoConvertor;

    public ProductService(ProductRepository productRepository, ProductConvertor productConvertor, ProductDtoConvertor productDtoConvertor) {
        this.productRepository = productRepository;
        this.productConvertor = productConvertor;
        this.productDtoConvertor = productDtoConvertor;
    }

    public ProductDto createProduct(ProductRequest productRequest)
    {
        Product savedProduct = productConvertor.convert(productRequest);
        return productDtoConvertor.convert(productRepository.save(savedProduct));
    }
    public ProductDto updateProduct(String id, ProductRequest productRequest)
    {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("product couldn't found by id: " +id));
        product.setCategory(productRequest.getCategory());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());
        productRepository.save(product);
        return productDtoConvertor.convert(product);


    }

    public List<ProductDto> getAllProducts()
    {
        return productRepository.findByActiveTrue().stream().map(productDtoConvertor::convert).collect(Collectors.toList());
    }

    public void deleteProduct(String id)
    {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product couldn't not found by id:" + id));
        product.setActive(false);
        productRepository.save(product);
    }

    public List<ProductDto> searchProducts(String keyword)
    {
        return productRepository.searchProducts(keyword).stream().map(productDtoConvertor::convert).collect(Collectors.toList());
    }


}
