package com.mcatk.acshop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mcatk.acshop.shop.Shops;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {
    
    private final File shopsFile;
    private final Gson gson;
    
    public FileOperation() {
        shopsFile = new File(AcShop.getPlugin().getDataFolder(), "shopsFile.json");
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public void saveShops(Shops shops) {
        try {
            FileWriter writer = new FileWriter(shopsFile);
            gson.toJson(shops, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Shops loadShops() {
        Shops shops = null;
        try {
            if (shopsFile.exists()) {
                FileReader reader = new FileReader(shopsFile);
                shops = gson.fromJson(reader, Shops.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shops == null ? new Shops() : shops;
    }
    
}
