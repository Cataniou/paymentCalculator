package com.lunchsplit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunchsplit.model.*;
import com.lunchsplit.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LunchController {

    @PostMapping("/lunch/split")
    public ResponseEntity<String> splitLunch(@RequestBody LunchRequest request) {
        try {
            LunchResponse response = PaymentService.calculatePayment(request);
            ObjectMapper objectMapper = new ObjectMapper();
            return ResponseEntity.ok(objectMapper.writeValueAsString(response));
        }
        catch (Exception e)
        {
            ResponseEntity.badRequest();
            return ResponseEntity.ofNullable(e.getMessage());
        }
    }
}
