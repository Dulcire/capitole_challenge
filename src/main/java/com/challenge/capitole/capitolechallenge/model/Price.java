package com.challenge.capitole.capitolechallenge.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import com.challenge.capitole.capitolechallenge.enums.Currency;
import com.challenge.capitole.capitolechallenge.enums.Priority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PRICES")
public class Price implements Serializable {
    private static final long serialVersionUID = -1663840504969255751L;

    @Id
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Priority priority;

    @Column(name = "PRICE")
    private Double price;

    @Enumerated(EnumType.STRING)
    private Currency curr;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", nullable = false)
    private Brand brandId;


    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "PRICE_LIST", nullable = false)
    private PriceList priceList;

    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Column(name = "END_DATE")
    private Timestamp endDate;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        final Price prices = (Price) o;
        return getId() != null && Objects.equals(getId(), prices.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
