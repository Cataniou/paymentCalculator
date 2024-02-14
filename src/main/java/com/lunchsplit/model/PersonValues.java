package com.lunchsplit.model;

public class PersonValues extends Person {

    private double amountToPay;
    private String linkToPay;

    public String getLinkToPay() {
        return linkToPay;
    }

    public void setLinkToPay(String linkToPay) {
        this.linkToPay = linkToPay;
    }

    public double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(double amountToPay) {
        this.amountToPay = amountToPay;
    }
}
