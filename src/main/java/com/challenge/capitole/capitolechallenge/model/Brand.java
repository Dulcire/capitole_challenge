package com.challenge.capitole.capitolechallenge.model;

import java.io.Serializable;
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
@Table(name = "BRANDS")
@Builder
public class Brand implements Serializable {
    private static final long serialVersionUID = 1822887863384952241L;
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "NAME")
    private String name;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        final Brand brands = (Brand) o;
        return getId() != null && Objects.equals(getId(), brands.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
