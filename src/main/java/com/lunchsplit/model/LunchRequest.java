package com.lunchsplit.model;

import com.lunchsplit.model.entity.Discount;
import com.lunchsplit.model.entity.PersonItems;
import com.lunchsplit.model.entity.Tax;

import java.util.List;

public class LunchRequest {

    /**
     * Estrutura padrão da requisição que o FrontEnd manda
     */
    private List<PersonItems> people;
    private List<Tax> taxes;
    private List<Discount> discounts;
    private String paymentService;

    private String userInput;

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(String service) {
        this.paymentService = service;
    }

    public List<PersonItems> getPeople() {
        return people;
    }

    public void setPeople(List<PersonItems> people) {
        this.people = people;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Tax> taxes) {
        this.taxes = taxes;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
}
