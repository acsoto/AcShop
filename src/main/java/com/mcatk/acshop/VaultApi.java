package com.mcatk.acshop;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultApi {
    
    private static Economy econ;
    
    //启动Vault依赖
    public static boolean setupEconomy() {
        if (AcShop.getPlugin().getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp =
                AcShop.getPlugin().getServer().getServicesManager().
                        getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    //经济操作
    public static boolean takePlayerMoney(Player p, double m) {
        EconomyResponse r = econ.withdrawPlayer(p, m);
        return r.transactionSuccess();
    }
}
