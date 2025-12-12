package com.app.ecom.dto.convertor;

import com.app.ecom.dto.ProductDto;
import com.app.ecom.dto.ProductRequest;
import com.app.ecom.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConvertor {

    public Product convert(ProductRequest productRequest)
    {
        Product product = new Product(productRequest.getName(),productRequest.getDescription(),productRequest.getPrice(),productRequest.getStockQuantity(),productRequest.getCategory(),productRequest.getImageUrl());

        return product;

    }


}
