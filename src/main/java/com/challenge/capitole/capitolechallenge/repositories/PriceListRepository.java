package com.challenge.capitole.capitolechallenge.repositories;

import com.challenge.capitole.capitolechallenge.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {


}
