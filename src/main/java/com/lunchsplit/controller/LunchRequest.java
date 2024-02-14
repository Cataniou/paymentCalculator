package com.lunchsplit.controller;

import com.lunchsplit.model.*;

import java.util.List;

public class LunchRequest {
    public List<PersonItems> people;
    public List<Tax> taxes;
    public List<Discount> discounts;
    public String paymentService;

    public String userInput;

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
