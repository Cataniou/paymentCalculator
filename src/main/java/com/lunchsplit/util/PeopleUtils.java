package com.lunchsplit.util;

import com.lunchsplit.model.entity.Item;
import com.lunchsplit.model.entity.PaymentLink;
import com.lunchsplit.model.entity.PersonItems;
import com.lunchsplit.model.entity.PersonValues;

import java.util.ArrayList;
import java.util.List;

public class PeopleUtils {

    /**
     * Métodos de cálculo de valores
     */

    public static List<PersonValues> generatePersonValuesList(List<PersonItems> personItems, double totalConsumption, double totalTaxes, double totalDiscounts, String paymentService, String userInput) {
        List<PersonValues> personValuesList = new ArrayList<>();

        for (PersonItems person : personItems) {
            double consumptionPerson = 0;
            double amountToPay;
            String linkToPay = null;
            PersonValues personValues = new PersonValues();
            personValues.setName(person.getName());
            for (Item item : person.getItems()) {
                consumptionPerson += item.getValue();
            }

            amountToPay = CalcUtils.calcAmountToPay(consumptionPerson, totalConsumption, totalTaxes, totalDiscounts);

            personValues.setAmountToPay(amountToPay);

            if (paymentService != null && PaymentLink.Services.valueOf(paymentService) == PaymentLink.Services.PICPAY)
                linkToPay = LinkUtils.generatePicpayLink(userInput, amountToPay);

            personValues.setLinkToPay(linkToPay);

            personValuesList.add(personValues);
        }

        return personValuesList;
    }
}
