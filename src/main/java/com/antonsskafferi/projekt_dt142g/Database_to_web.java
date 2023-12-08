package com.antonsskafferi.projekt_dt142g;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Named
public class Database_to_web {
    @PersistenceContext
    EntityManager em;
    private List<DishesEntity> getAllDishes(){
        TypedQuery<DishesEntity> query = em.createNamedQuery("dishesEntity.allFirst_courses", DishesEntity.class);
        List<DishesEntity> res = query.getResultList();
        return res;
    }
    public List<DishesEntity> getStarter_course(){

        return getAllDishes();
    }
}
