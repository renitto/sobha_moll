package com.example.renitto.scmapp.Model;

/**
 * Created by Renitto on 3/1/2016.
 */
public class ModelShoppingPlanner {
    String shop_item_name;
    boolean shop_status;

    public String getShop_item_name() {
        return shop_item_name;
    }

    public void setShop_item_name(String shop_item_name) {
        this.shop_item_name = shop_item_name;
    }

    public boolean isShop_status() {
        return shop_status;
    }

    public void setShop_status(boolean shop_status) {
        this.shop_status = shop_status;
    }

    public ModelShoppingPlanner(String shop_item_name, boolean shop_status) {
        this.shop_item_name = shop_item_name;
        this.shop_status = shop_status;
    }
}
