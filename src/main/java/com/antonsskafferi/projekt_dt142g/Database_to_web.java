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
        List<DishesEntity> res = getAllDishes("dishesEntity.allFirst_courses");
        return res;
    }
    public List<DishesEntity> getMiddle_course(){

        List<DishesEntity> res = getAllDishes("dishesEntity.allMiddle_courses");
        return res;
    }

    public List<DishesEntity> getLast_course(){

        List<DishesEntity> res = getAllDishes("dishesEntity.allLast_courses");
        return res;
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

    public int getNoOfFirstToShow(int size){
        return Math.min(size, 4);
    }
    public int getNoOfMiddleToShow(int size){
        return Math.min(size, 2);
    }
    public int getNoOfLastToShow(int size){
        return Math.min(size, 2);
    }
    public int getNoOfWineToShow(int size){
        return Math.min(size, 4);
    }
}

