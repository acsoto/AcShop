package com.mcatk.acshop.command;

import com.mcatk.acshop.AcShop;
import com.mcatk.acshop.FileOperation;
import com.mcatk.acshop.commodity.Item;
import com.mcatk.acshop.commodity.ItemType;
import com.mcatk.acshop.shop.Shop;
import com.mcatk.itemmanager.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommand implements CommandExecutor {
    
    private CommandSender sender;
    private String[] args;
    
    void printHelp() {
        sender.sendMessage("帮助：无设置判错机制，严格按照格式执行");
        sender.sendMessage("上架物品：/asadmin add <item/cmd> <商店ID> <商品ID> <价格> ");
        sender.sendMessage("设置：/asadmin setcmd <商店ID> <商品ID> <cmd>");
        sender.sendMessage("重载：/asadmin reload ");
        //sender.sendMessage("删除物品：/acshop del <商店ID> <物品ID>");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            return false;
        }
        this.sender = sender;
        this.args = args;
        if (args.length == 0) {
            printHelp();
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "add":
                add();
                break;
            case "reload":
                AcShop.getPlugin().loadShops();
                break;
            case "setcmd":
                setCmd();
                break;
            default:
        }
        new FileOperation().saveShops(AcShop.getShops());
        return true;
    }
    
    private void add() {
        String shopId = args[2];
        if (!AcShop.getShops().getShopsHashMap().containsKey(shopId)) {
            AcShop.getShops().getShopsHashMap().put(shopId, new Shop(shopId));
        }
        String itemId = args[3];
        int price = Integer.parseInt(args[4]);
        switch (args[1].toLowerCase()) {
            case "item":
                addItemStackItem(shopId, itemId, price);
                break;
            case "cmd":
                addCmdItem(shopId, itemId, price);
                break;
            default:
        }
    }
    
    private void addItemStackItem(String shopId, String itemId, int price) {
        // 商品类型固定为保留字 Shop-xxx
        String sortId = "Shop_" + shopId;
        ItemManager.addItem(sortId, itemId,
                ((Player) sender).getInventory().getItemInMainHand());
        AcShop.getShops().getShopsHashMap().get(shopId).getItemHashMap().put(
                itemId, new Item(ItemType.ITEM_STACK, itemId, price, sortId, itemId)
        );
        sender.sendMessage("Ok");
    }
    
    private void addCmdItem(String shopId, String itemId, int price) {
        AcShop.getShops().getShopsHashMap().get(shopId).getItemHashMap().put(
                itemId, new Item(ItemType.ITEM_CMD, itemId, price, "")
        );
        sender.sendMessage("Ok");
    }
    
    private void setCmd() {
        String shopId = args[1];
        String itemId = args[2];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 3; i < args.length; i++) {
            stringBuilder.append(args[i]).append(" ");
        }
        AcShop.getShops().getShopsHashMap().get(shopId).getItemHashMap()
                .get(itemId).setCmd(stringBuilder.toString());
        sender.sendMessage("Ok");
    }
    
}
