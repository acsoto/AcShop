package com.mcatk.acshop.commodity;

public class Item {
    private ItemType type;
    private String id;
    private int price;
    private String sortId;
    private String itemId;
    private String cmd;
    
    public Item(ItemType type, String id, int price, String sortId, String itemId) {
        this.type = type;
        this.id = id;
        this.price = price;
        this.sortId = sortId;
        this.itemId = itemId;
    }
    
    public Item(ItemType type, String id, int price, String cmd) {
        this.type = type;
        this.id = id;
        this.price = price;
        this.cmd = cmd;
    }
    
    public String getId() {
        return id;
    }
    
    public int getPrice() {
        return price;
    }
    
    public ItemType getType() {
        return type;
    }
    
    public String getSortId() {
        return sortId;
    }
    
    public String getItemId() {
        return itemId;
    }
    
    public String getCmd() {
        return cmd;
    }
    
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
    
    @Override
    public String toString() {
        if (type.equals(ItemType.ITEM_STACK)) {
            return type + id + price + "(" + sortId + "." + itemId + ")";
        } else {
            return type + id + price + "(" + cmd + ")";
        }
    }
}