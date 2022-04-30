package com.tanvi.ref.mvcref.product.repository;

import com.tanvi.ref.mvcref.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
