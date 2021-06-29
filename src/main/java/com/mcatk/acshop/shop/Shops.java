package com.mcatk.acshop.shop;

import com.mcatk.acshop.commodity.Item;

import java.util.HashMap;

public class Shops {
    private final HashMap<String, Shop> shopsHashMap = new HashMap<>();
    
    public HashMap<String, Shop> getShopsHashMap() {
        return shopsHashMap;
    }
    
    public Item getItem(String id1, String id2) {
        return shopsHashMap.get(id1).getItemHashMap().get(id2);
    }
    
}
