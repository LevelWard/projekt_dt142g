package com.antonsskafferi.projekt_dt142g;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@NamedQuery(name = "lunchEntity.allDates", query = "SELECT allDates FROM LunchEntity allDates")
@Entity
@jakarta.persistence.Table(name = "LUNCH", schema = "asdb", catalog = "")
public class LunchEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ID")
    private int id;

    public int getLunchId() {
        return id;
    }

    public void setLunchId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PRICE")
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "TYPE")
    private String type;

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

        BookingsEntity that = (BookingsEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (type != null ? !type.equals(that.type) : that.type != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, description, type);
    }
}
