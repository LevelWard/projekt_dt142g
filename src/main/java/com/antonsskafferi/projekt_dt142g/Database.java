package com.antonsskafferi.projekt_dt142g;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Named
public class Database {

    @PersistenceContext
    EntityManager em;

    private List<LunchTest> lunchTest;

    @PostConstruct
    public void init() {
        // Initialize your lunchList from the database
        lunchTest = em.createQuery("SELECT l FROM LunchEntity l", LunchTest.class).getResultList();
    }

    public List<LunchTest> getLunch() {
        return lunchTest;
    }

    public void saveLunch() {
        // Save changes to the database, if needed
        for (LunchTest lunch : lunchTest) {
            em.merge(lunch);
        }
    }
}

