package com.app.ecom.dto.convertor;

import com.app.ecom.dto.ProductDto;
import com.app.ecom.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoConvertor {

    public ProductDto convert(Product product)
    {
        ProductDto productDto = new ProductDto(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getStockQuantity(),product.getCategory(),product.getImageUrl(),product.getActive());
        return productDto;
    }

}
