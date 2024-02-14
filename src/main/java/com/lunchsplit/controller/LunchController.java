package com.lunchsplit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchsplit.model.LunchRequest;
import com.lunchsplit.model.LunchResponse;
import com.lunchsplit.model.entity.PaymentLink;
import com.lunchsplit.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LunchController {

    /**
     * Método principal do programa
     * Divide a conta proporcionalmente para as pessoas, conforme seu consumo
     * Também divide proporcionalmente os descontos e taxas
     */
    @PostMapping("/lunch/split")
    public ResponseEntity<String> splitLunch(@RequestBody LunchRequest request) {
        try {
            LunchResponse response = PaymentService.calculatePayment(request);
            ObjectMapper objectMapper = new ObjectMapper();
            return ResponseEntity.ok(objectMapper.writeValueAsString(response));
        } catch (Exception e) {
            ResponseEntity.badRequest();
            return ResponseEntity.ofNullable(e.getMessage());
        }
    }

    /**
     * Retorna os serviços de pagamento cadastrados na PaymentLink para popular a Lista no frontend
     */
    @GetMapping("/paymentServices")
    public ResponseEntity<String> getPaymentServices() {
        try {
            PaymentLink.Services[] services = PaymentLink.Services.values();
            ObjectMapper objectMapper = new ObjectMapper();
            return ResponseEntity.ok(objectMapper.writeValueAsString(services));
        } catch (Exception e) {
            ResponseEntity.badRequest();
            return ResponseEntity.ofNullable(e.getMessage());
        }
    }
}
