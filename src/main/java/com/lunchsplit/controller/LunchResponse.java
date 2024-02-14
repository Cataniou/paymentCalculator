package com.lunchsplit.controller;

import com.lunchsplit.model.PersonValues;

import java.util.List;

public class LunchResponse {

    private double totalConsumption;
    private double totalTaxes;
    private double totalDiscounts;
    private double totalToPay;

    private List<PersonValues> peopleValues;

    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public double getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(double totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public double getTotalDiscounts() {
        return totalDiscounts;
    }

    public void setTotalDiscounts(double totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    public double getTotalToPay() {
        return totalToPay;
    }

    public void setTotalToPay(double totalToPay) {
        this.totalToPay = totalToPay;
    }

    public List<PersonValues> getPeopleValues() {
        return peopleValues;
    }

    public void setPeopleValues(List<PersonValues> peopleValues) {
        this.peopleValues = peopleValues;
    }
}
