package com.lunchsplit.util;

import com.lunchsplit.model.entity.Discount;
import com.lunchsplit.model.entity.Item;
import com.lunchsplit.model.entity.PersonItems;
import com.lunchsplit.model.entity.Tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CalcUtils {

    /**
     * Métodos de cálculo de valores
     */

    public static double calctotalConsumption(List<PersonItems> personItems) throws Exception {

        double counter = 0;

        for (PersonItems person : personItems) {
            if (person.getItems() == null)
                throw new Exception("Pessoa sem itens!");

            for (Item item : person.getItems()) {
                counter += item.getValue();
            }
        }

        //Arredondar para duas casas decimais
        BigDecimal bd = new BigDecimal(counter).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    public static double calcTaxes(List<Tax> taxes, double totalConsumption) {
        double counter = 0;

        if (taxes != null) {
            for (Tax tax : taxes) {
                counter += tax.isPercentage() ? ((tax.getValue() * totalConsumption) / 100) : tax.getValue();
            }
        }

        //Arredondar para duas casas decimais
        BigDecimal bd = new BigDecimal(counter).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double calcDiscounts(List<Discount> discounts, double totalConsumption) {
        double counter = 0;

        if (discounts != null) {
            for (Discount discount : discounts) {
                counter += discount.isPercentage() ? ((discount.getValue() * totalConsumption) / 100) : discount.getValue();
            }
        }

        //Arredondar para duas casas decimais
        BigDecimal bd = new BigDecimal(counter).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double calcTotalToPay(double totalConsumption, double totalTaxes, double totalDiscounts) {
        double totalToPay = totalConsumption + totalTaxes - totalDiscounts;
        totalToPay = totalToPay < 0 ? 0 : totalToPay;

        //Arredondar para duas casas decimais
        BigDecimal bd = new BigDecimal(totalToPay).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double calcAmountToPay(double consumptionPerson, double totalConsumption, double totalTaxes, double totalDiscounts) {
        double percentParticipation = consumptionPerson / totalConsumption;
        double amountToPay = (totalConsumption * percentParticipation) + (totalTaxes * percentParticipation) - (totalDiscounts * percentParticipation);

        //Arredondar para duas casas decimais
        BigDecimal bd = new BigDecimal(amountToPay).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
