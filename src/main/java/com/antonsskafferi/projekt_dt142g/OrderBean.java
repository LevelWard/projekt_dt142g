package com.antonsskafferi.projekt_dt142g;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Named
public class OrderBean {

    //TODO: implement a function to send items to order_meals and order_drinks
    private List<OrderItem> itemsList = null;
    private int table_id;

    @PersistenceContext
    EntityManager em;

    /**
     * Pushes unique items into itemsList. if the items name already exists them we'll increment
     * an existing item's amount by 1.
     * @param amount & title are used for construction.
     */
    public void push(int amount, String title) {
        OrderItem new_item = new OrderItem(amount, title);
        if (!itemsList.contains(new_item)) {
            itemsList.add(new_item);
        } else {
            for (OrderItem existingItem : itemsList) {
                if (existingItem.getName().equals(new_item.getName())) {
                    existingItem = new OrderItem(existingItem.getAmount() + 1, existingItem.getName());
                }
            }
        }
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




    }

    @Transactional
    public String insertToDb() {

        DiningOrderEntity newOrder = new DiningOrderEntity();
        newOrder.setTableNr(this.table_id);
        this.em.persist(newOrder);
        List<DiningOrderEntity> updatedID = em.createQuery("SELECT max(d.orderId) FROM DiningOrderEntity d", DiningOrderEntity.class).getResultList();
        int orderID = updatedID.get(0).getOrderId();


        List<OrderMealsEntity> orderList;
        for (OrderItem item : this.itemsList) {
            OrderMealsEntity order = new OrderMealsEntity();
            order.setOrderId(orderID);
            order.setAmount(item.getAmount());
            order.setDishTitle(item.getName());
            this.em.persist(order);
        }
        List<OrderMealsEntity> updater = em.createQuery("SELECT d FROM OrderMealsEntity d", OrderMealsEntity.class).getResultList();
        this.flushList();

        return "orderOverview.xhtml?faces-redirect=true";
    }

    /**
     * sends all items in list to the db, this should clear the list completely.
     */
    public void flushList(){
        insertToDb();
        itemsList.clear();
    }

}
