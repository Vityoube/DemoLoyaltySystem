package com.vityoube.demoloyaltysystem.exception;

import static java.lang.String.format;

public class ProductNotFoundException extends Exception{
    private String productName;

    public ProductNotFoundException(String productName){
        this.productName = productName;
    }

    @Override
    public String getMessage() {
        return format("Product %s not found", productName);
    }
}
