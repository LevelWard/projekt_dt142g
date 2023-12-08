package com.antonsskafferi.projekt_dt142g;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@jakarta.persistence.Table(name = "dining_table", schema = "asdb", catalog = "")
public class DiningTableEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "TABLE_NUMBER", nullable = false)
    private int tableNumber;

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiningTableEntity that = (DiningTableEntity) o;

        if (tableNumber != that.tableNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tableNumber;
    }
}
