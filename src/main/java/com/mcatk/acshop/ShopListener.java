package com.mcatk.acshop;

import com.mcatk.acshop.commodity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ShopListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().contains("§6AC商店-")) {
            if (event.getWhoClicked() instanceof Player) {
                ItemStack icon = event.getCurrentItem();
                event.setCancelled(true);
                if (icon != null) {
                    List<String> list = icon.getItemMeta().getLore();
                    if (list != null) {
                        String shopId = event.getInventory().getTitle().split("-")[1];
                        String itemId = list.get(0).split(":")[1];
                        Item item = AcShop.getShops().getItem(shopId, itemId);
                        new Operation().buy((Player) event.getWhoClicked(), item);
                    }
                    if (icon.getItemMeta().getDisplayName() != null) {
                        if (icon.getItemMeta().getDisplayName().equals("返回")) {
                            ((Player) event.getWhoClicked()).chat("/menu_shop");
                        }
                    }
                }
            }
        }
    }
    
}
