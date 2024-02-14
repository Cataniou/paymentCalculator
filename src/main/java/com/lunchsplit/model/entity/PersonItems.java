package com.lunchsplit.model.entity;

import java.util.List;

public class PersonItems extends Person {

    private List<Item> items; // Itens que foram consumidos

    public PersonItems() {
    }

    public PersonItems(String name, List<Item> items) {
        this.setName(name);
        this.setItems(items);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
