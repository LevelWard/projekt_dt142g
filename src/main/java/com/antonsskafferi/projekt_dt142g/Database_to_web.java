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
    private List<DishesEntity> getAllDishes(String type){
        TypedQuery<DishesEntity> query = em.createNamedQuery(type, DishesEntity.class);
        List<DishesEntity> res = query.getResultList();
        return res;
    }
    public List<DishesEntity> getStarter_course(){

        return getAllDishes("dishesEntity.allFirst_courses");
    }
    public List<DishesEntity> getMiddle_course(){

        return getAllDishes("dishesEntity.allMiddle_courses");
    }

    public List<DishesEntity> getLast_course(){

        return getAllDishes("dishesEntity.allLast_courses");
    }

    private List<DrinksEntity> getAllDrinks(){
        TypedQuery<DrinksEntity> query = em.createNamedQuery("drinksEntity.allWhine", DrinksEntity.class);
        List<DrinksEntity> res = query.getResultList();
        return res;
    }
    public List<DrinksEntity> getWhine(){

        return getAllDrinks();
    }

    //Private size of first,last, middle to return so facelet can decide its maximum repeatnumbers, moreover if
    // first.size >4 then alwasy set 4.

    private int NoOfFirst = 0;
    private int NoOfMiddle = 0;
    private int NoOfLast = 0;
}

