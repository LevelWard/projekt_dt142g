package com.antonsskafferi.projekt_dt142g;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;

@SessionScoped
@Named
public class OrderBean implements Serializable {

    //TODO: implement a function to send items to order_meals and order_drinks
    private List<OrderItem> itemsList = new ArrayList<>();
    private int table_number;
    private Integer orderId;
    @PersistenceContext
    EntityManager em;
    @PostConstruct
    public void init(){
        table_number = 1;
    }

    /**
     * Pushes unique items into itemsList. if the items name already exists them we'll increment
     * an existing item's amount by 1.
     * @param title are used for construction.
     */
    public void addToList(String title) {
        OrderItem new_item = new OrderItem(1,title);
        boolean found = false;
            for (OrderItem existingItem : itemsList) {
                if (existingItem.getName().equals(new_item.getName())) {
                    found = true;
                    existingItem.setAmount(existingItem.getAmount() + 1);
                }
            }
        if (!found){
            itemsList.add(new_item);
        }

    }

    /**
     *
     * @return private itemsList containing OrderItems
     */
    public List<OrderItem> getItemsList() {
        return itemsList;
    }

    /**
     *
     * @return a redirect to orderOverview webpage.
     */
    @Transactional
    public String insertToDb() {
        //TODO: insertToDb should also initialize orders as "false", and also give them a timestamp.
    //this works now!
        //new order created, we'll now set its values
        LocalDateTime currentDateTime = LocalDateTime.now();
        DiningOrderEntity newOrder = new DiningOrderEntity();
        newOrder.setTableNr(table_number);
        newOrder.setStatus(false);
        newOrder.setDateOfOrder(Date.valueOf(currentDateTime.toLocalDate()));
        newOrder.setTimeOfOrder(Time.valueOf(currentDateTime.toLocalTime()));
        em.persist(newOrder); //adds entity to db
        // Takes the latest order_id
        Object maxOrderIdObject = em.createQuery("SELECT MAX(d.orderId) FROM DiningOrderEntity d").getSingleResult();
        // Fetches the updated order_id
        Integer orderID = (maxOrderIdObject != null) ? ((Number) maxOrderIdObject).intValue() : 0; //this should return an int but error log says it's trying to make a diningOrderEntity
        orderId = orderID;


        List<OrderMealsEntity> orderList;
        for (OrderItem item : itemsList) {
            OrderMealsEntity order = new OrderMealsEntity();
            order.setOrderId(orderID);
            order.setAmount(item.getAmount());
            order.setDishTitle(item.getName());
            em.persist(order); //adds entity list to db
        }
        List<OrderMealsEntity> updater = em.createQuery("SELECT d FROM OrderMealsEntity d", OrderMealsEntity.class).getResultList();
        itemsList.clear();

        return "index.xhtml?faces-redirect=true";
    }


    public int getTable_number() {
        return table_number;
    }

    public void setTable_number(int number){
        table_number = number;
    }

    public Integer getOrderId(){
        return orderId;
    }

    public void clearList(){
        itemsList.clear();
    }



    public String pingBean(){
        testVar = "Böna nådd";
        return "orderFormView.xhtml?faces-redirect=true";

    }

    private String testVar = "Ej nådd";

    public String getTestVar() {
        return testVar;
    }
}
