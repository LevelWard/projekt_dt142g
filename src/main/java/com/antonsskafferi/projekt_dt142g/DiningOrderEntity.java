package com.antonsskafferi.projekt_dt142g;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@NamedQuery(name = "diningOrderEntity.allOrders", query = "SELECT orders FROM DiningOrderEntity orders")
@Entity
@jakarta.persistence.Table(name = "DINING_ORDER", schema = "asdb", catalog = "")
public class DiningOrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ORDER_ID", nullable = false)
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "TIME_OF_ORDER", nullable = true)
    private Time timeOfOrder;

    public Time getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(Time timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }


    @Basic
    @Column(name = "STATUS", nullable = true)
    private Boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    @Basic
    @Column(name = "DATE_OF_ORDER", nullable = true)
    private Date dateOfOrder;

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    @Basic
    @Column(name = "TABLE_NR", nullable = true)
    private Integer tableNr;

    public Integer getTableNr() {
        return tableNr;
    }

    public void setTableNr(Integer tableNr) {
        this.tableNr = tableNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiningOrderEntity that = (DiningOrderEntity) o;

        if (orderId != that.orderId) return false;
        if (timeOfOrder != null ? !timeOfOrder.equals(that.timeOfOrder) : that.timeOfOrder != null) return false;
        if (dateOfOrder != null ? !dateOfOrder.equals(that.dateOfOrder) : that.dateOfOrder != null) return false;
        if (tableNr != null ? !tableNr.equals(that.tableNr) : that.tableNr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (timeOfOrder != null ? timeOfOrder.hashCode() : 0);
        result = 31 * result + (dateOfOrder != null ? dateOfOrder.hashCode() : 0);
        result = 31 * result + (tableNr != null ? tableNr.hashCode() : 0);
        return result;
    }
}
