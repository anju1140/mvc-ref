package com.tanvi.ref.mvcref.product.service;

import com.tanvi.ref.mvcref.product.Exception.ResourceNotFoundException;
import com.tanvi.ref.mvcref.product.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product updateProduct(Product product) throws ResourceNotFoundException;

    List<Product> getAllProducts();

    Product getProductById(long productId);

    void deleteProduct(long id);
}
