package com.antonsskafferi.projekt_dt142g;
import java.io.Serializable;
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

    private int table_id;
    private Integer orderId;
    @PersistenceContext
    EntityManager em;

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
    //TODO: look into persist(), check #resurser
        //new order created, we'll give it table_id
        DiningOrderEntity newOrder = new DiningOrderEntity();
        newOrder.setTableNr(table_id);
        em.persist(newOrder); //adds entity to db
        // Takes the latest order_id
        List<DiningOrderEntity> updatedID = em.createQuery("SELECT max(d.orderId) FROM DiningOrderEntity d", DiningOrderEntity.class).getResultList();
        // Fetches the updated order_id
        int orderID = updatedID.get(0).getOrderId();
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

    public Integer getOrderId(){
        return orderId;
    }

    public void clearList(){
        itemsList.clear();
    }

    public void setTable_id(int id){
        table_id = id;
    }

    public void pingBean(){
        System.out.print("object reached!");
    }

}
