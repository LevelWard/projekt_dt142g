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



    private List<DishesEntity> lunchEntities;

    @PostConstruct
    public void init() {
        // Initialize your lunchList from the database
        lunchEntities = em.createQuery("SELECT l FROM DishesEntity l", DishesEntity.class).getResultList();
    }

    public List<DishesEntity> getLunch() {
        return lunchEntities;
    }

    @Transactional
    public String saveLunch() {
        // Save changes to the database, if needed
        for (DishesEntity lunch : lunchEntities) {
            em.merge(lunch);
        }
        return "lunchMenu.xhtml?faces-redirect=true";
    }

   @Transactional
    public String deleteLunch(String title){
       em.createQuery("delete from DishesEntity l where l.title = :title")
               .setParameter("title", title)
               .executeUpdate();

       lunchEntities = em.createQuery("SELECT l FROM DishesEntity l", DishesEntity.class).getResultList();

       return "lunchMenu.xhtml?faces-redirect=true";
   }


}

