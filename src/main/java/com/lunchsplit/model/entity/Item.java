package com.lunchsplit.model.entity;

public class Item {
    private String name; // Nome do que foi consumido, só importa para fins visuais, não tem relevância na distribuição
    private double value; // Valor do que foi consumido para cálculo

    public Item() {
    }

    public Item(String name, double value) {
        this.setName(name);
        this.setValue(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
