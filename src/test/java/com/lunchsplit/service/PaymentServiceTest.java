package com.lunchsplit.service;

import com.lunchsplit.model.LunchRequest;
import com.lunchsplit.model.LunchResponse;
import com.lunchsplit.model.entity.Discount;
import com.lunchsplit.model.entity.Item;
import com.lunchsplit.model.entity.PersonItems;
import com.lunchsplit.model.entity.Tax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PaymentServiceTest {

    @InjectMocks
    public PaymentService paymentService;

    List<PersonItems> personItems;
    List<Item> items;
    List<Tax> taxes;
    List<Discount> discounts;


    @BeforeEach
    public void setUp() {
        personItems = new ArrayList<>();
        items = new ArrayList<>();
        taxes = new ArrayList<>();
        discounts = new ArrayList<>();

        items.add(new Item("Hamburguer", 30.99));
        items.add(new Item("Refrigerante", 8));
        personItems.add(new PersonItems("Joao", new ArrayList<>(items)));
        items.clear();

        items.add(new Item("Batata", 49.99));
        items.add(new Item("Refrigerante", 5));
        personItems.add(new PersonItems("Lucas", new ArrayList<>(items)));
        items.clear();

        items.add(new Item("Salada", 36));
        items.add(new Item("Suco", 16));
        personItems.add(new PersonItems("Enzo", new ArrayList<>(items)));
        items.clear();

        taxes.add(new Tax(true, 10));
        taxes.add(new Tax(false, 6));

        discounts.add(new Discount(true, 2));
        discounts.add(new Discount(false, 4));
    }

    @Test
    void testCalculatePaymentEmptyData() {
        // Mockando os dados necessários
        LunchRequest request = new LunchRequest();
        request.setPeople(Collections.singletonList(new PersonItems()));
        request.setTaxes(Collections.singletonList(new Tax()));
        request.setDiscounts(Collections.singletonList(new Discount()));
        request.setPaymentService("PICPAY");
        request.setUserInput("usuario");

        // Execução
        try {
            LunchResponse result = paymentService.calculatePayment(request);

            // Assertions
            assertEquals(0, result.getTotalConsumption());
            assertEquals(0, result.getTotalTaxes());
            assertEquals(0, result.getTotalDiscounts());
            assertEquals(0, result.getTotalToPay());

        } catch (Exception e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    void testCalculatePaymentRealData() {
        // Mockando os dados necessários
        LunchRequest request = new LunchRequest();
        request.setPeople(personItems);
        request.setTaxes(taxes);
        request.setDiscounts(discounts);
        request.setPaymentService("PICPAY");
        request.setUserInput("usuario");

        // Execução
        try {
            LunchResponse result = paymentService.calculatePayment(request);

            // Assertions
            assertEquals(145.98, result.getTotalConsumption());
            assertEquals(20.6, result.getTotalTaxes());
            assertEquals(6.92, result.getTotalDiscounts());
            assertEquals(159.66, result.getTotalToPay());

        } catch (Exception e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }
}
