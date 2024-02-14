package com.lunchsplit.controller;

import com.lunchsplit.model.Discount;
import com.lunchsplit.model.Person;
import com.lunchsplit.model.PersonItems;
import com.lunchsplit.model.Tax;

import java.util.List;

public class LunchRequest {
    public List<PersonItems> people;
    public List<Tax> taxes;
    public List<Discount> discounts;

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
