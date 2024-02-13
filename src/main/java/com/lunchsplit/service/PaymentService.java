package com.lunchsplit.service;

import com.lunchsplit.model.Order;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public double calculatePayment(Order order) {
        // Implementar a lógica para calcular o pagamento proporcional
        // ...

        return 0.0;
    }

    public String generatePicpayLink(double amount) {
        // Implementar a lógica para gerar o link do Picpay
        // ...

        return "https://www.picpay.com/";
    }
}
