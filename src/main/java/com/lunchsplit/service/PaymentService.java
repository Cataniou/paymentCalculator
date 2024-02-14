package com.lunchsplit.service;

import com.lunchsplit.controller.LunchRequest;
import com.lunchsplit.controller.LunchResponse;
import com.lunchsplit.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    public static LunchResponse calculatePayment(LunchRequest request) throws Exception {
        LunchResponse response = new LunchResponse();
        try
        {
            double totalConsumption = 0;
            double totalTaxes = 0;
            double totalDiscounts = 0;
            double totalToPay;
            BigDecimal bd;
            List<PersonValues> personValuesList = new ArrayList<>();

            for (PersonItems person : request.getPeople()) {
                for (Item item : person.getItems()) {
                    totalConsumption += item.getValue();
                }
            }

            //Arredondar para duas casas decimais
            bd = new BigDecimal(totalConsumption).setScale(2, RoundingMode.HALF_UP);
            totalConsumption = bd.doubleValue();

            response.setTotalConsumption(totalConsumption);

            for (Tax tax : request.getTaxes()) {
                totalTaxes += tax.isPercentage ? ((tax.getValue() * totalConsumption) / 100) : tax.getValue();
            }

            //Arredondar para duas casas decimais
            bd = new BigDecimal(totalTaxes).setScale(2, RoundingMode.HALF_UP);
            totalTaxes = bd.doubleValue();

            response.setTotalTaxes(totalTaxes);

            for (Discount discount : request.getDiscounts()) {
                totalDiscounts += discount.isPercentage ? ((discount.getValue() * totalConsumption) / 100) : discount.getValue();
            }

            //Arredondar para duas casas decimais
            bd = new BigDecimal(totalDiscounts).setScale(2, RoundingMode.HALF_UP);
            totalDiscounts = bd.doubleValue();

            response.setTotalDiscounts(totalDiscounts);

            totalToPay = totalConsumption + totalTaxes - totalDiscounts;
            totalToPay = totalToPay < 0 ? 0 : totalToPay;

            //Arredondar para duas casas decimais
            bd = new BigDecimal(totalToPay).setScale(2, RoundingMode.HALF_UP);
            totalToPay = bd.doubleValue();

            response.setTotalToPay(totalToPay);

            for (PersonItems person : request.getPeople()) {
                double consumptionPerson = 0;
                double percentParticipation;
                double amountToPay;
                PersonValues personValues = new PersonValues();
                personValues.setName(person.getName());
                for (Item item : person.getItems()) {
                    consumptionPerson += item.getValue();
                }

                percentParticipation = consumptionPerson / totalConsumption;
                amountToPay = (totalConsumption * percentParticipation) + (totalTaxes * percentParticipation) - (totalDiscounts * percentParticipation);

                //Arredondar para duas casas decimais
                bd = new BigDecimal(amountToPay).setScale(2, RoundingMode.HALF_UP);
                amountToPay = bd.doubleValue();

                personValues.setAmountToPay(amountToPay);
                //TO:DO lógica do link de pagamento;
                personValues.setLinkToPay("https://www.picpay.com/");
                personValuesList.add(personValues);
            }

            response.setPeopleValues(personValuesList);
            return response;
        }
        catch (Exception e) {
            throw new Exception("Erro ao processar dados de refeição!");

        }
    }

    public String generatePicpayLink(double amount) {
        // Implementar a lógica para gerar o link do Picpay
        // ...

        return "https://www.picpay.com/";
    }
}
