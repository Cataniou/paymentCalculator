package com.lunchsplit.model;

import com.lunchsplit.model.entity.PersonValues;

import java.util.List;

public class LunchResponse {

    /**
     * Estrutura padrão da resposta que é mandada para o FrontEnd
     */

    private double totalConsumption;
    private double totalTaxes;
    private double totalDiscounts;
    private double totalToPay;
    private String description;
    private List<PersonValues> peopleValues;

    public LunchResponse() {
    }

    public LunchResponse(String description) {
        this.setDescription(description);

        this.setTotalConsumption(0);
        this.setTotalTaxes(0);
        this.setTotalDiscounts(0);
        this.setTotalToPay(0);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
