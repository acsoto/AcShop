package com.mcatk.acshop.shop;

import com.mcatk.acshop.commodity.Item;

import java.util.HashMap;

public class Shop {
    
    private String id;
    private String name;
    private final HashMap<String, Item> itemHashMap;
    
    public Shop(String id) {
        this.id = id;
        this.name = id;
        this.itemHashMap = new HashMap<>();
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public HashMap<String, Item> getItemHashMap() {
        return itemHashMap;
    }
    
}
