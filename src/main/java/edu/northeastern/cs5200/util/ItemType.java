package edu.northeastern.cs5200.util;

public enum ItemType {
    BERRY("Berry"), POTION("Potion");
    private String name;

    ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
