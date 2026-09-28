package com.cramer._551_shop_vorlage.model;

public class Cloak extends Product {

    private String material;
    private int protectionLevel;

    public Cloak(int id, String name, double price, String description,
                 int stock, String material, int protectionLevel) {

        super(id, name, price, "Cloak", description, stock);

        this.material = material;
        this.protectionLevel = protectionLevel;
    }

    public String getMaterial() {
        return material;
    }

    public int getProtectionLevel() {
        return protectionLevel;
    }
}
