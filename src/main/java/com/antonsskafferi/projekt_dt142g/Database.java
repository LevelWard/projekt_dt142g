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

    private List<LunchEntity> lunchList;

    @PostConstruct
    public void init() {
        // Initialize your lunchList from the database
        lunchList = em.createQuery("SELECT l FROM LunchEntity l", LunchEntity.class).getResultList();
    }

    public List<LunchEntity> getLunch() {
        return lunchList;
    }

    public void saveLunch() {
        // Save changes to the database, if needed
        for (LunchEntity lunch : lunchList) {
            em.merge(lunch);
        }
    }
}

