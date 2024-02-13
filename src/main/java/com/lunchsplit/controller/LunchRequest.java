package com.lunchsplit.controller;

import com.lunchsplit.model.Discount;
import com.lunchsplit.model.Person;
import com.lunchsplit.model.Tax;

import java.util.List;

public class LunchRequest {
    public List<Person> people;
    public List<Tax> taxes;
    public List<Discount> discounts;

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
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
