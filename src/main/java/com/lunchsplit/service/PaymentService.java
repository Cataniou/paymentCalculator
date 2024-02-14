package com.lunchsplit.service;

import com.lunchsplit.model.LunchRequest;
import com.lunchsplit.model.LunchResponse;
import com.lunchsplit.model.entity.PersonValues;
import com.lunchsplit.util.CalcUtils;
import com.lunchsplit.util.PeopleUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    /**
     * Método responsável por realizar os cálculos e gerar a resposta para o Controller
     */

    public static LunchResponse calculatePayment(LunchRequest request) {
        LunchResponse response = new LunchResponse("Erro ao processar dados!");

        if (request.getPeople() != null) {
            try {
                double totalConsumption;
                double totalTaxes;
                double totalDiscounts;
                double totalToPay;
                List<PersonValues> personValuesList;

                // Calcula Total de Consumação
                totalConsumption = CalcUtils.calctotalConsumption(request.getPeople());
                response.setTotalConsumption(totalConsumption);

                // Calcula de Taxas
                totalTaxes = CalcUtils.calcTaxes(request.getTaxes(), totalConsumption);
                response.setTotalTaxes(totalTaxes);

                // Calcula de Descontos
                totalDiscounts = CalcUtils.calcDiscounts(request.getDiscounts(), totalConsumption);
                response.setTotalDiscounts(totalDiscounts);

                // Calcula Total a Pagar
                totalToPay = CalcUtils.calcTotalToPay(totalConsumption, totalTaxes, totalDiscounts);
                response.setTotalToPay(totalToPay);

                // Gera a Lista de Pessoas com seus valores
                personValuesList = PeopleUtils.generatePersonValuesList(request.getPeople(), totalConsumption, totalTaxes, totalDiscounts, request.getPaymentService(), request.getUserInput());
                response.setPeopleValues(personValuesList);

                response.setDescription("Sucesso ao processar os dados!");
            } catch (Exception e) {
                response.setDescription("Erro ao processar os dados");

            }
        }
        return response;
    }
}
