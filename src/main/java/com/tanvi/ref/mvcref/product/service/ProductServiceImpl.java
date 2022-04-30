package com.tanvi.ref.mvcref.product.service;

import com.tanvi.ref.mvcref.product.Exception.ResourceNotFoundException;
import com.tanvi.ref.mvcref.product.model.Product;
import com.tanvi.ref.mvcref.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements  ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

        Optional<Product> productDb = this.productRepository.findById(product.getId());

        if (productDb.isPresent()) {
            Product productUpdate = productDb.get();
            productUpdate.setId(product.getId());
            productUpdate.setName((product.getName()));
            productUpdate.setDescription(product.getDescription());
            productUpdate.setPrice(product.getPrice());
            return productUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found Exception:" + product.getId());
        }


    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) {
        Optional<Product> productDb = this.productRepository.findById(productId);
        if (productDb.isPresent())
            return productDb.get();
        else {
            throw new ResourceNotFoundException("Record not found with id : " + productId);
        }
    }


    public void deleteProduct(long productId){
        Optional<Product> productDb = this.productRepository.findById(productId);
        if(productDb.isPresent())
             productRepository.delete(productDb.get());
        else{
            throw  new ResourceNotFoundException("Record not found with id "+productId);
        }
    }
}