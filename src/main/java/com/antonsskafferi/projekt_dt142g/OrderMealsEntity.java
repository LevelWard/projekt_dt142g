package com.antonsskafferi.projekt_dt142g;

import jakarta.persistence.*;
import jdk.jfr.Name;

@NamedQuery(name = "orderMealsEntity.allFood", query = "SELECT allFood FROM OrderMealsEntity allFood")
@Entity
@jakarta.persistence.Table(name = "ORDER_MEALS", schema = "asdb", catalog = "")
public class OrderMealsEntity {
    @Basic
    @jakarta.persistence.Column(name = "PEOPLE", nullable = true)
    private Byte people;

    public Byte getPeople() {
        return people;
    }

    public void setPeople(Byte people) {
        this.people = people;
    }

    @Basic
    @jakarta.persistence.Column(name = "AMOUNT", nullable = true)
    private Byte amount;

    public Byte getAmount() {
        return amount;
    }

    public void setAmount(Byte amount) {
        this.amount = amount;
    }

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_ID", nullable = false)
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DISH_TITLE", nullable = false, length = 50)
    private String dishTitle;

    public String getDishTitle() {
        return dishTitle;
    }

    public void setDishTitle(String dishTitle) {
        this.dishTitle = dishTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderMealsEntity that = (OrderMealsEntity) o;

        if (orderId != that.orderId) return false;
        if (people != null ? !people.equals(that.people) : that.people != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (dishTitle != null ? !dishTitle.equals(that.dishTitle) : that.dishTitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = people != null ? people.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + orderId;
        result = 31 * result + (dishTitle != null ? dishTitle.hashCode() : 0);
        return result;
    }
}
