package com.lunchsplit.model;

import java.util.List;

public class PersonItems extends Person {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}