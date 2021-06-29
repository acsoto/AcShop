package com.mcatk.acshop.command;

import com.mcatk.acshop.AcShop;
import com.mcatk.acshop.Operation;
import com.mcatk.acshop.ShopGui;
import com.mcatk.acshop.commodity.Item;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AcShopCommand implements CommandExecutor {
    
    private CommandSender sender;
    private String[] args;
    
    void printHelp() {
        sender.sendMessage("帮助");
        sender.sendMessage("打开商店：/as gui <商店ID>");
        sender.sendMessage("购买：/as buy <商店ID> <商品ID>");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        this.args = args;
        if (args.length == 0) {
            printHelp();
            return true;
        }
        if (!(sender instanceof Player)) {
            return false;
        }
        switch (args[0].toLowerCase()) {
            case "gui":
                gui();
                break;
            case "buy":
                buy();
                break;
            default:
        }
        return true;
    }
    
    private void gui() {
        if (args.length != 2) {
            sender.sendMessage("参数长度错误");
            return;
        }
        String shopId = args[1];
        if (!AcShop.getShops().getShopsHashMap().containsKey(shopId)) {
            sender.sendMessage("无此商店");
            return;
        }
        if (sender instanceof Player) {
            ((Player) sender).openInventory(
                    new ShopGui(AcShop.getShops().getShopsHashMap().get(shopId))
                            .getGui()
            );
        }
    }
    
    private void buy() {
        if (args.length != 3) {
            sender.sendMessage("参数长度错误");
            return;
        }
        String shopId = args[1];
        String itemId = args[2];
        if (!AcShop.getShops().getShopsHashMap().containsKey(shopId)) {
            sender.sendMessage("无此商店");
            return;
        }
        if (!AcShop.getShops().getShopsHashMap().get(shopId).
                getItemHashMap().containsKey(itemId)) {
            sender.sendMessage("无此商品");
            return;
        }
        Item item = AcShop.getShops().getShopsHashMap().get(shopId).
                getItemHashMap().get(itemId);
        new Operation().buy((Player) sender, item);
    }
    
}
