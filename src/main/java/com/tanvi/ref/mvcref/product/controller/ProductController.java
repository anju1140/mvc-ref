package com.tanvi.ref.mvcref.product.controller;

import com.tanvi.ref.mvcref.product.model.Product;
import com.tanvi.ref.mvcref.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){

        return ResponseEntity.ok().body(this.productService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id ){
        return ResponseEntity.ok().body(this.productService.getProductById(id));
    }
    @PostMapping("/products")
    public ResponseEntity<Product>  createProduct(@RequestBody Product product){

        return  ResponseEntity.ok().body(this.productService.createProduct(product));
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<Product>  updateProduct(@PathVariable long id,@RequestBody Product product){

        product.setId(id);
        return  ResponseEntity.ok().body(this.productService.createProduct(product));
    }

    @DeleteMapping("/products/{id}")
    public HttpStatus deleteProduct(@PathVariable long id){
       this.productService.deleteProduct(id);
       return  HttpStatus.OK;

    }
}
