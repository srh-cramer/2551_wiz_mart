package com.cramer._551_shop_vorlage.model;

public class Spellbook extends Product {

    private String school;
    private int spellCount;

    public Spellbook(int id, String name, double price, String description,
                     int stock, String school, int spellCount) {

        super(id, name, price, "Spellbook", description, stock);

        this.school = school;
        this.spellCount = spellCount;
    }

    public String getSchool() {
        return school;
    }

    public int getSpellCount() {
        return spellCount;
    }
}
