package com.mcatk.acshop;

import com.mcatk.acshop.commodity.Item;
import com.mcatk.acshop.commodity.ItemType;
import com.mcatk.acshop.shop.Shop;
import com.mcatk.itemmanager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class ShopGui {
    
    private Shop shop;
    
    public ShopGui(Shop shop) {
        this.shop = shop;
    }
    
    public Inventory getGui() {
        Inventory gui = Bukkit.
                createInventory(null, 54, shop.getName() + "  §6AC商店-" + shop.getId());
        ArrayList<String> keySet = new ArrayList<>(shop.getItemHashMap().keySet());
        Collections.sort(keySet);
        for (String key : keySet) {
            if (gui.firstEmpty() != -1) {
                gui.addItem(getIcon(shop.getItemHashMap().get(key)));
            }
        }
        gui.setItem(53, getQuitIcon());
        return gui;
    }
    
    private ItemStack getIcon(Item item) {
        if (item.getType().equals(ItemType.ITEM_STACK)) {
            return getIconWithItemStack(item);
        }
        if (item.getType().equals(ItemType.ITEM_CMD)) {
            return getIconWithCmdItem(item);
        }
        
        return null;
    }
    
    private ItemStack getIconWithItemStack(Item item) {
        ItemStack icon = ItemManager.
                getItem(item.getSortId(), item.getItemId()).clone();
        addPriceLore(icon, item.getId(), item.getPrice());
        return icon;
    }
    
    private ItemStack getIconWithCmdItem(Item item) {
        ItemStack icon = new ItemStack(Material.REDSTONE, 1);
        addPriceLore(icon, item.getId(), item.getPrice());
        return icon;
    }
    
    private void addPriceLore(ItemStack icon, String id, int price) {
        ItemMeta meta = icon.getItemMeta();
        ArrayList<String> list = new ArrayList<>();
        list.add("商品ID:" + id);
        if (meta.getLore() != null) {
            list.addAll(meta.getLore());
        }
        list.add("§e§m§l一§6§m§l一§c§m§l一§a§m§l一§b§m§l一§a§m§l一§c§m§l一§6§m§l一§e§m§l一");
        list.add("§a§l点 击 购 买");
        list.add("§6 $消耗$ §a " + price + " §aAC点");
        list.add("§e§m§l一§6§m§l一§c§m§l一§a§m§l一§b§m§l一§a§m§l一§c§m§l一§6§m§l一§e§m§l一");
        meta.setLore(list);
        icon.setItemMeta(meta);
    }
    
    private ItemStack getQuitIcon() {
        ItemStack icon = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName("返回");
        icon.setItemMeta(meta);
        return icon;
    }
    
}
