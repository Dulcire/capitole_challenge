package com.challenge.capitole.capitolechallenge.dtos;

import java.sql.Timestamp;
import java.util.Objects;

import com.challenge.capitole.capitolechallenge.enums.Currency;
import com.challenge.capitole.capitolechallenge.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {

    private Priority priority;
    private Double price;
    private Currency curr;
    private BrandDto brandId;
    private ProductDto productId;
    private PriceListDto priceList;
    private Timestamp startDate;
    private Timestamp endDate;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PriceDto other = (PriceDto) o;
        return Objects.equals(priority, other.priority) && Objects.equals(price, other.price) && Objects.equals(curr, other.curr) && Objects.equals(brandId, other.brandId) && Objects.equals(productId, other.productId) && Objects.equals(priceList, other.priceList) && Objects.equals(startDate, other.startDate) && Objects.equals(endDate, other.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority, price, curr, brandId, productId, priceList, startDate, endDate);
    }
}
