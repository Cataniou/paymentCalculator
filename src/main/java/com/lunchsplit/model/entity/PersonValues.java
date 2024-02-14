package com.lunchsplit.model.entity;

public class PersonValues extends Person {

    private double amountToPay; // Quantidade a ser paga por pessoa, já calculado o proporcional de taxas e descontos
    private String linkToPay; // Link de pagamento concatenado para direcionar o valor exato

    public PersonValues() {
    }

    public PersonValues(String name, double amountToPay, String linkToPay) {
        this.setName(name);
        this.setAmountToPay(amountToPay);
        this.setLinkToPay(linkToPay);
    }

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
