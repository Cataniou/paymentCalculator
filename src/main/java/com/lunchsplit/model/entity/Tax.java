package com.lunchsplit.model.entity;

public class Tax {
    public boolean isPercentage; // Parâmetro para sinalizar se o valor se trata de porcentagem ou não
    private double value; // Valor em questão da Taxa

    public Tax() {
    }

    public Tax(boolean isPercentage, double value) {
        this.setPercentage(isPercentage);
        this.setValue(value);
    }

    public boolean isPercentage() {
        return isPercentage;
    }

    public void setPercentage(boolean percentage) {
        isPercentage = percentage;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
