package com.lunchsplit.util;

import com.lunchsplit.model.entity.PaymentLink;

public class LinkUtils {

    /**
     * Métodos responsáveis por gerar os links de pagamento
     * Cada serviço de pagamento deve ter sua própria lógica para gerar o link
     */
    public static String generatePicpayLink(String user, double amount) {
        return String.format("%s%s/%s", PaymentLink.Services.PICPAY.getLinkBase(), user, amount);
    }

}
