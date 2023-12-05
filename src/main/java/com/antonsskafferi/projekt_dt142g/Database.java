package com.antonsskafferi.projekt_dt142g;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

@ApplicationScoped
@Named
public class Database {
    @PersistenceContext
    EntityManager em;

    public List<BookingsEntity> getBookings(){
        Query testQuery = em.createNamedQuery("bookingsEntity.allDates");
        return testQuery.getResultList();
    }

}

