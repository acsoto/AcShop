package com.mcatk.acshop;

import com.mcatk.acshop.command.AcShopCommand;
import com.mcatk.acshop.command.AdminCommand;
import com.mcatk.acshop.shop.Shops;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AcShop extends JavaPlugin {
    
    private static AcShop plugin;
    private static Shops shops;
    
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        regCommand();
        regListener();
        VaultApi.setupEconomy();
        loadShops();
    }
    
    public void loadShops() {
        shops = new FileOperation().loadShops();
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public static AcShop getPlugin() {
        return plugin;
    }
    
    public static Shops getShops() {
        return shops;
    }
    
    private void regCommand() {
        Bukkit.getPluginCommand("as").
                setExecutor(new AcShopCommand());
        Bukkit.getPluginCommand("asadmin").
                setExecutor(new AdminCommand());
    }
    
    private void regListener() {
        Bukkit.getPluginManager().
                registerEvents(new ShopListener(), this);
    }
}
