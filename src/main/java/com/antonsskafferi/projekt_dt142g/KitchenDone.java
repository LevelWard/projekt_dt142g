package com.antonsskafferi.projekt_dt142g;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
@Named
public class KitchenDone {

    @PersistenceContext
    EntityManager em;

    private List<DiningOrderEntity> orderList = null;

    @PostConstruct
    public void init() {
        // Initialize your lunchList from the database
        this.orderList = em.createQuery("SELECT l FROM DiningOrderEntity l", DiningOrderEntity.class).getResultList();
    }

    @Transactional
    public String markAsDone(int id) {
        // Save changes to the database, if needed
        for (DiningOrderEntity order : orderList) {
            if (order.getOrderId() == id) {
                order.setStatus(true);
            }
            em.merge(order);
        }
        return "kitchenOverview.xhtml?faces-redirect=true";
    }

    @Transactional
    public String markAsNotDone(int id) {
        // Save changes to the database, if needed
        for (DiningOrderEntity order : orderList) {
            if (order.getOrderId() == id || idToTable(id) == order.getTableNr()) {

                order.setStatus(false);
            }
            em.merge(order);
        }
        return "orderOverview.xhtml?faces-redirect=true";
    }

    @Transactional
    public Integer idToTable(int id) {
        for (DiningOrderEntity order : orderList) {
            if (order.getOrderId() == id) {
                return order.getTableNr();
            }
        }
        return 0;
    }
}

