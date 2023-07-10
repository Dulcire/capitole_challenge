package com.challenge.capitole.capitolechallenge.repositories;

import java.sql.Timestamp;
import java.util.List;

import com.challenge.capitole.capitolechallenge.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByStartDateAndBrandId_CodeAndProductId_Code(final Timestamp startDate, final String brandCode, final String productCode);
}
