package com.antonsskafferi.projekt_dt142g;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named
public class orderBean {

    //TODO: implement a function to send items to order_meals and order_drinks
    private List<OrderItem> itemsList;
    private int table_id;

    /**
     * Pushes unique items into itemsList. if the items name already exists them we'll increment
     * an existing item's amount by 1.
     * @param new_item an item to be added to the list.
     */
    public void push(OrderItem new_item) {

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


    /**
     * for each Meal from itemList:
     * insert new item in table_orders (save its order_nr)
     * insert new item in order_meals with order_nr, title, and amount included.
     * pop the item.
     */
    public void insertMealOrders(){

    }

    /**
     * for each drink from itemList:
     * insert new item in table_orders (save its order_nr)
     * insert new item in order_drinks with order_nr, title, and amount included.
     * pop the item.
     */
    public void insertDrinkOrders(){

    }

    /**
     * sends all items in list to the db, this should clear the list completely.
     */
    public void flushList(){
        insertDrinkOrders();
        insertMealOrders();
        itemsList.clear();
    }

}
