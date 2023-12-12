package com.antonsskafferi.projekt_dt142g;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Named
public class OrderBean {

    //TODO: implement a function to send items to order_meals and order_drinks
    private List<OrderItem> itemsList;

    int table_id;
    private Integer orderId;
    @PersistenceContext
    EntityManager em;

    /**
     * Pushes unique items into itemsList. if the items name already exists them we'll increment
     * an existing item's amount by 1.
     * @param title are used for construction.
     */
    public void addToList(String title) {


        /*OrderItem new_item = new OrderItem(1,"Chorizo buffe");
        if (!itemsList.contains(new_item)) {
            itemsList.add(new_item);
        } else {
            for (OrderItem existingItem : itemsList) {
                if (existingItem.getName().equals(new_item.getName())) {
                    existingItem.setAmount(existingItem.getAmount() + 1);
                }
            }
        }*/

    }

    /**
     *
     * @return private itemsList containing OrderItems
     */
    public List<OrderItem> getItemsList() {
        return itemsList;
    }

    @PostConstruct
    public void init() {
        //Just an example order.
        OrderItem t = new OrderItem(1,"Afrikansk buffe");
        OrderItem e = new OrderItem(1, "Gelato citron");
        OrderItem d = new OrderItem(1, "Italiensk Sallad");
        itemsList = List.of(t,e,d);

    }

    @Transactional
    public String insertToDb() {

        DiningOrderEntity newOrder = new DiningOrderEntity();
        newOrder.setTableNr(table_id);
        em.persist(newOrder);
        List<DiningOrderEntity> updatedID = em.createQuery("SELECT max(d.orderId) FROM DiningOrderEntity d", DiningOrderEntity.class).getResultList();
        int orderID = updatedID.get(0).getOrderId();
        orderId = orderID;

        List<OrderMealsEntity> orderList;
        for (OrderItem item : itemsList) {
            OrderMealsEntity order = new OrderMealsEntity();
            order.setOrderId(orderID);
            order.setAmount(item.getAmount());
            order.setDishTitle(item.getName());
            em.persist(order);
        }
        List<OrderMealsEntity> updater = em.createQuery("SELECT d FROM OrderMealsEntity d", OrderMealsEntity.class).getResultList();
        itemsList.clear();

        return "orderOverview.xhtml?faces-redirect=true";
    }

    public Integer getOrderId(){
        return orderId;
    }


}
