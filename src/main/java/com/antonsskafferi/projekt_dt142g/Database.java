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

    public List<OrderDrinksEntity> getAllDrinks(){
        Query query = em.createNamedQuery("orderDrinksEntity.allDrinks");
        return query.getResultList();
    }

    /*public List<SimpleListIntPair> getFoodOrders(){
        //Get all the order Id's
        List<Integer> orderIdList = em.createQuery("SELECT c.orderId FROM DiningOrderEntity c")
                .getResultList();

        //Make a list which can be displayed with found items
        List<SimpleListIntPair> displayItems = Collections.<SimpleListIntPair>emptyList();

        //Get the food for each order into value pair (orderId, dishes)
        for (Integer id : orderIdList) {

            List<OrderMealsEntity> resultForId = foodForOrder(id);

            SimpleListIntPair values = new SimpleListIntPair(id,resultForId);

            displayItems.add(values);

        }

        return displayItems;
    }*/

    public List<Integer> getOrderIds(){
        //Get all the order Id's
        List<Integer> orderIdList = em.createQuery("SELECT c.orderId FROM DiningOrderEntity c")
                .getResultList();

        return orderIdList;
    }



    public List<OrderMealsEntity> foodForOrder(int id){

        List<OrderMealsEntity> resultList = em.createQuery("SELECT c FROM OrderMealsEntity c WHERE c.orderId=:ordersID")
                .setParameter("ordersID", id)
                .getResultList();
        return resultList;
    }

    public List<DishesEntity> getLuchByDay (String day){
        List<DishesEntity> dayList = em.createQuery("SELECT Buffe FROM DishesEntity Buffe WHERE Buffe.subType=:day")
                .setParameter("day", day)
                .getResultList();
        return dayList;

    }

    public List<MusicEntity> getMusicByWeek (int week){
        List<MusicEntity> musikListy = em.createQuery("SELECT musik from MusicEntity musik where musik.week=:week")
                .setParameter("week", week)
                .getResultList();
        return musikListy;
    }



}
