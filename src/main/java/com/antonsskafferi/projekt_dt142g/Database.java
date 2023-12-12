package com.antonsskafferi.projekt_dt142g;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

import static java.sql.DriverManager.getConnection;

@ApplicationScoped
@Named
public class Database {

    private String title;
    private int price;
    private String description;
    private String type;
    private String subtype;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() { return subtype; }

    public void setSubtype(String subtype) { this.subtype = subtype; }
    @PersistenceContext
    EntityManager em;


    private List<DishesEntity> lunchEntities;

    private List<DrinksEntity> drinksEntities;


    public List<DrinksEntity> getDrinks() { return drinksEntities; }

    @Transactional
    public String saveDrinks() {
        // Save changes to the database, if needed
        for (DrinksEntity drinks : drinksEntities) {
            em.merge(drinks);
        }
        return "wineList.xhtml?faces-redirect=true";
    }

    @PostConstruct
    public void init() {
        // Initialize your lunchList from the database
        lunchEntities = em.createQuery("SELECT l FROM DishesEntity l", DishesEntity.class).getResultList();
        drinksEntities = em.createQuery("SELECT d FROM DrinksEntity d", DrinksEntity.class).getResultList();
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

    @Transactional
    public String deleteDrinks(String title){
        em.createQuery("delete from DrinksEntity d where d.title = :title")
                .setParameter("title", title)
                .executeUpdate();

        drinksEntities = em.createQuery("SELECT d FROM DrinksEntity d", DrinksEntity.class).getResultList();

        return "wineList.xhtml?faces-redirect=true";
    }

    @Transactional
    public String insertLunch(String title, int price, String description, String type, String subtype) {
        DishesEntity lunch = new DishesEntity();
        lunch.setTitle(title);
        lunch.setPrice(price);
        lunch.setDescription(description);
        lunch.setType(type);
        lunch.setSubtype(subtype);
        this.em.persist(lunch);
        lunchEntities = em.createQuery("SELECT l FROM DishesEntity l", DishesEntity.class).getResultList();
        return "lunchMenu.xhtml?faces-redirect=true";
    }

    @Transactional
    public String insertDrinks(String title, int price, String description, String type) {
        DrinksEntity drinks = new DrinksEntity();
        drinks.setTitle(title);
        drinks.setPrice(price);
        drinks.setDescription(description);
        drinks.setType(type);
        this.em.persist(drinks);
        drinksEntities = em.createQuery("SELECT d FROM DrinksEntity d", DrinksEntity.class).getResultList();
        return "wineList.xhtml?faces-redirect=true";
    }

}

