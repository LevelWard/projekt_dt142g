package com.antonsskafferi.projekt_dt142g;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@NamedQuery(name = "bookingsEntity.allDates", query = "SELECT allDates FROM BookingsEntity allDates")
@Entity
@jakarta.persistence.Table(name = "LUNCH", schema = "asdb", catalog = "")
public class BookingsEntity {
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

    public Date getDescription() {
        return de;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Basic
    @Column(name = "TIME_OF_BOOKING")
    private Timestamp timeOfBooking;

    public Timestamp getTimeOfBooking() {
        return timeOfBooking;
    }

    public void setTimeOfBooking(Timestamp timeOfBooking) {
        this.timeOfBooking = timeOfBooking;
    }

    @Basic
    @Column(name = "PEOPLE")
    private Byte people;

    public Byte getPeople() {
        return people;
    }

    public void setPeople(Byte people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingsEntity that = (BookingsEntity) o;

        if (bookingId != that.bookingId) return false;
        if (bookingTime != null ? !bookingTime.equals(that.bookingTime) : that.bookingTime != null) return false;
        if (bookingDate != null ? !bookingDate.equals(that.bookingDate) : that.bookingDate != null) return false;
        if (timeOfBooking != null ? !timeOfBooking.equals(that.timeOfBooking) : that.timeOfBooking != null)
            return false;
        if (people != null ? !people.equals(that.people) : that.people != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookingId;
        result = 31 * result + (bookingTime != null ? bookingTime.hashCode() : 0);
        result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
        result = 31 * result + (timeOfBooking != null ? timeOfBooking.hashCode() : 0);
        result = 31 * result + (people != null ? people.hashCode() : 0);
        return result;
    }
}
