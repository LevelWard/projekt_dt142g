package com.antonsskafferi.projekt_dt142g;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
@Named
public class Database {
    @PersistenceContext
    EntityManager em;


    //If a new order is created this list must be updated and it added to the back.
    private List<Integer> ordersList = null;


    public List<BookingsEntity> getBookings() {
        Query testQuery = em.createNamedQuery("bookingsEntity.allDates");
        return testQuery.getResultList();
    }

    public List<OrderDrinksEntity> getAllDrinks() {
        Query query = em.createNamedQuery("orderDrinksEntity.allDrinks");
        return query.getResultList();
    }



    public List<Integer> getOrderIds() {
        if (this.ordersList == null) {
            List<Integer> orderIdList = em.createQuery("SELECT c.orderId FROM DiningOrderEntity c where c.status=false")
                    .getResultList();
            this.ordersList = orderIdList;
        }
        //Get all the order Id's from stored list.
        return ordersList;
    }
    public List<Integer> getDoneIds() {
           return em.createQuery("SELECT c.orderId FROM DiningOrderEntity c where c.status=true")
                    .getResultList();
    }


    public List<OrderMealsEntity> foodForKitchen(int id) {
        List<OrderMealsEntity> resultList = em.createQuery("SELECT c FROM OrderMealsEntity c WHERE c.orderId=:ordersID order by c.orderId")
                .setParameter("ordersID", id)
                .getResultList();
        return resultList;
    }

    public String moveToTop(int id) {
        int current = getIndex(id);
        if (current != 0) {
            ordersList.remove(current);
            ordersList.add(0,id);
        }
        getOrderIds();
        return "kitchenOverview.xhtml?faces-redirect=true";
    }

    public String moveItemUp(int id) {
        int current = getIndex(id);

        if (current != 0) {
            Collections.swap(this.ordersList, current, current - 1);
        }
        getOrderIds();
        return "kitchenOverview.xhtml?faces-redirect=true";
    }

    public String moveItemDown(int id) {
        int current = getIndex(id);
        if (current != this.ordersList.size()) {
            Collections.swap(this.ordersList, current, current + 1);
        }
        getOrderIds();
        return "kitchenOverview.xhtml?faces-redirect=true";
    }

    public String moveToBottom(int id) {
        int current = getIndex(id);
        if (current != this.ordersList.size()-1) {
            ordersList.remove(current);
            ordersList.add(id);
        }
        getOrderIds();
        return "kitchenOverview.xhtml?faces-redirect=true";
    }

    private int getIndex(int id) {
        System.out.println(this.ordersList.indexOf(id));
        return this.ordersList.indexOf(id);
    }

    public void removeOrder(int id) {
        this.ordersList.remove(getIndex(id));
    }

    public List<Integer> testOrderList(){
        return ordersList;
    }


    public List<DishesEntity> foodForOrder(String Middle) {
        //get all foods in Dishes table.
        List resultList = em.createQuery("SELECT c.title FROM DishesEntity c WHERE c.type=:middle")
                .setParameter("middle", Middle).getResultList();
        return resultList;
    }

    public List<OrderMealsEntity> subtypeForOrder(String title) {

        List<OrderMealsEntity> resultList = em.createQuery("SELECT c.subType FROM DishesEntity c WHERE c.title=:title")
                .setParameter("title", title)
                .getResultList();
        return resultList;
    }

    public List<OrderMealsEntity> drinkForOrder(int id) {

        List<OrderMealsEntity> resultList = em.createQuery("SELECT c.drinkTitle FROM OrderDrinksEntity c WHERE c.orderId=:ordersID")
                .setParameter("ordersID", id)
                .getResultList();
        return resultList;
    }

    public List<OrderMealsEntity> drinkTypeForOrder(String title) {

        List<OrderMealsEntity> resultList = em.createQuery("SELECT c.type FROM DrinksEntity c WHERE c.title=:title")
                .setParameter("title", title)
                .getResultList();
        return resultList;
    }
}
