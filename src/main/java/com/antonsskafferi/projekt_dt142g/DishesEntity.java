package com.antonsskafferi.projekt_dt142g;

import jakarta.persistence.*;

@NamedQuery(name = "dishesEntity.allDishes", query = "SELECT dishes FROM DishesEntity dishes")
@Entity
@jakarta.persistence.Table(name = "DISHES", schema = "asdb", catalog = "")
public class DishesEntity {

    @Id
    @jakarta.persistence.Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "PRICE", nullable = true)
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 200)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, length = 50)
    private String type;


    @Basic
    @Column(name = "SUBTYPE", nullable = true, length = 50)
    private String subType;

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
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
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}