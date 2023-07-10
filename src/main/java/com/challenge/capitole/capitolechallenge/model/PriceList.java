package com.challenge.capitole.capitolechallenge.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "PRICES_LIST")
public class PriceList {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRICE")
    private Double price;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        final PriceList brands = (PriceList) o;
        return getId() != null && Objects.equals(getId(), brands.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
