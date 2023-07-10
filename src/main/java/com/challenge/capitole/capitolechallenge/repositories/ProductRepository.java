package com.challenge.capitole.capitolechallenge.repositories;

import java.util.Optional;

import com.challenge.capitole.capitolechallenge.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByCode(String code);
}
