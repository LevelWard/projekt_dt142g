package com.antonsskafferi.projekt_dt142g;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import static java.sql.DriverManager.getConnection;

@ApplicationScoped
@Named
public class Database {

    @PersistenceContext
    EntityManager em;



    private List<LunchEntity> lunchEntities;

    @PostConstruct
    public void init() {
        // Initialize your lunchList from the database
        lunchEntities = em.createQuery("SELECT l FROM LunchEntity l", LunchEntity.class).getResultList();
    }

    public List<LunchEntity> getLunch() {
        return lunchEntities;
    }

    @Transactional
    public String saveLunch() {
        // Save changes to the database, if needed
        for (LunchEntity lunch : lunchEntities) {
            em.merge(lunch);
        }
        return "lunchMenu.xhtml?faces-redirect=true";
    }

   @Transactional
    public String deleteLunch(int id){
       em.createQuery("delete from LunchEntity l where l.lunchId = :id")
               .setParameter("id", id)
               .executeUpdate();

       lunchEntities = em.createQuery("SELECT l FROM LunchEntity l", LunchEntity.class).getResultList();

       return "lunchMenu.xhtml?faces-redirect=true";
   }
}

