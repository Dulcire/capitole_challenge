package com.challenge.capitole.capitolechallenge.repositories;

import java.util.Optional;

import com.challenge.capitole.capitolechallenge.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findBrandByCode(final String code);
}
