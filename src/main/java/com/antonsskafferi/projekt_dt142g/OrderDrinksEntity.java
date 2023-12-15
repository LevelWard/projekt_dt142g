package com.antonsskafferi.projekt_dt142g;

import jakarta.persistence.*;

@NamedQuery(name = "orderDrinksEntity.allDrinks", query = "SELECT allDrinks FROM OrderDrinksEntity allDrinks")
@Entity
@jakarta.persistence.Table(name = "ORDER_DRINKS", schema = "asdb", catalog = "")
public class OrderDrinksEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ORDER_ID", nullable = false)
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "DRINK_TITLE", nullable = false, length = 50)
    private String drinkTitle;

    public String getDrinkTitle() {
        return drinkTitle;
    }

    public void setDrinkTitle(String drinkTitle) {
        this.drinkTitle = drinkTitle;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = true)
    private Byte amount;

    public Byte getAmount() {
        return amount;
    }

    public void setAmount(Byte amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDrinksEntity that = (OrderDrinksEntity) o;

        if (orderId != that.orderId) return false;
        if (drinkTitle != null ? !drinkTitle.equals(that.drinkTitle) : that.drinkTitle != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (drinkTitle != null ? drinkTitle.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
