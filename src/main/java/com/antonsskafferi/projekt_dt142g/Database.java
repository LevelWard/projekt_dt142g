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


    //A list where kitchenOverview content is stored.
    private List<OrderMealsEntity> ordersList = null;





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



    public List<OrderMealsEntity> foodForKitchen(int id){
        if (this.ordersList == null){
            List<OrderMealsEntity> resultList = em.createQuery("SELECT c FROM OrderMealsEntity c WHERE c.orderId=:ordersID order by c.orderId")
                    .setParameter("ordersID", id)
                    .getResultList();
            this.ordersList = resultList;
        }

        return this.ordersList;
    }

    public void moveToTop(){

    }

    public void moveItemUp(){

    }

    public void moveItemDown(){

    }

    public void moveToBottom(){

    }




    public List<OrderMealsEntity> getFoodForKitchen(){
        return this.ordersList;
    }
    public List<OrderMealsEntity> subtypeForOrder(String title){

        List<OrderMealsEntity> resultList = em.createQuery("SELECT c.subType FROM DishesEntity c WHERE c.title=:title")
                .setParameter("title", title)
                .getResultList();
        return resultList;
    }

    public List<OrderMealsEntity> drinkForOrder(int id){

        List<OrderMealsEntity> resultList = em.createQuery("SELECT c.drinkTitle FROM OrderDrinksEntity c WHERE c.orderId=:ordersID")
                .setParameter("ordersID", id)
                .getResultList();
        return resultList;
    }
    public List<OrderMealsEntity> drinkTypeForOrder(String title){

        List<OrderMealsEntity> resultList = em.createQuery("SELECT c.type FROM DrinksEntity c WHERE c.title=:title")
                .setParameter("title", title)
                .getResultList();
        return resultList;
    }



}
