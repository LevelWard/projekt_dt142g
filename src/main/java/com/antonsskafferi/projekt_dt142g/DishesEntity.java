package com.antonsskafferi.projekt_dt142g;

import jakarta.enterprise.inject.Model;
import jakarta.persistence.*;

import java.util.Objects;

@Model
@NamedQuery(name = "dishesEntity.allData", query = "SELECT d FROM DishesEntity d")
@Entity
@Table(name = "dishes", schema = "asdb", catalog = "")
public class DishesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TITLE")
    private String title;
    @Basic
    @Column(name = "PRICE")
    private Integer price;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "TYPE")
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishesEntity that = (DishesEntity) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, description, type);
    }
}
