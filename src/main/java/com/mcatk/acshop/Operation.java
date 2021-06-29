package com.mcatk.acshop;

import com.mcatk.acshop.commodity.Item;
import com.mcatk.acshop.commodity.ItemType;
import com.mcatk.itemmanager.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Operation {
    
    public void buy(Player player, Item item) {
        if (VaultApi.takePlayerMoney(player, item.getPrice())) {
            AcShop.getPlugin().getLogger().info(
                    player.getName() + "购买了 " + item
            );
            if (item.getType().equals(ItemType.ITEM_STACK)) {
                giveItem(player, item);
            }
            if (item.getType().equals(ItemType.ITEM_CMD)) {
                executeCommand(player, item);
            }
            player.sendMessage("购买成功");
        } else {
            player.sendMessage("AC点不足");
        }
    }
    
    private void giveItem(Player player, Item item) {
        ItemStack itemStack = ItemManager.
                getItem(item.getSortId(), item.getItemId()).clone();
        player.getInventory().addItem(itemStack);
    }
    
    private void executeCommand(Player player, Item item) {
        String cmd = item.getCmd();
        if (cmd.contains("%p")) {
            cmd = cmd.replace("%p", player.getName());
        }
        if (!player.isOp()) {
            player.setOp(true);
            player.chat("/" + cmd);
            player.setOp(false);
        } else {
            player.chat("/" + cmd);
        }
    }
}
