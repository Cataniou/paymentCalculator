package com.lunchsplit.controller;

import com.lunchsplit.model.Discount;
import com.lunchsplit.model.Item;
import com.lunchsplit.model.Person;
import com.lunchsplit.model.Tax;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LunchController {

    @PostMapping("/lunch/split")
    public ResponseEntity<String> splitLunch(@RequestBody LunchRequest request) {
        // Aqui, você pode processar os dados conforme necessário
        // O objeto 'request' conterá os dados no novo formato JSON

        // Exemplo de acesso aos dados:
        for (Person person : request.getPeople()) {
            System.out.println("Name: " + person.getName());
            for (Item item : person.getItems()) {
                System.out.println("Item: " + item.getName() + ", Price: " + item.getValue());
            }
        }

        for (Tax tax : request.getTaxes()) {
            System.out.println("Tax: " + (tax.isPercentage() ? (tax.getValue() + "%") : ("$" + tax.getValue())));
        }

        for (Discount discount : request.getDiscounts()) {
            System.out.println("Discount: " + (discount.isPercentage() ? (discount.getValue() + "%") : ("$" + discount.getValue())));
        }

        // Lógica para processar os dados e retornar uma resposta adequada
        return ResponseEntity.ok("Dados de refeição processados com sucesso!");
    }
}
