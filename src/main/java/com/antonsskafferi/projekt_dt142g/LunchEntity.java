package com.antonsskafferi.projekt_dt142g;

import jakarta.enterprise.inject.Model;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;
import jakarta.transaction.Transactional;
@Model
@NamedQuery(name = "lunchEntity.allData", query = "SELECT l FROM LunchEntity l")
@Entity
@jakarta.persistence.Table(name = "LUNCH", schema = "asdb", catalog = "")
public class LunchEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ID")
    private int lunchId;

    public int getlunchId() {
        return lunchId;
    }

    public void setlunchId(int lunchId) {
        this.lunchId = lunchId;
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

        LunchEntity that = (LunchEntity) o;

        if (lunchId != that.lunchId) return false;
        if (price != that.price) return false;
        if (!Objects.equals(description, that.description)) return false;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lunchId, price, description, type);
    }
}
