package com.lunchsplit.model.entity;

public class PaymentLink {

    /**
     * Enumeradores com seus respectivos links base para concatenar e retornar no pagamento
     */

    public enum Services {
        NENHUM(""),
        PICPAY("https://picpay.me/");


        public final String linkBase;

        Services(String linkBase) {
            this.linkBase = linkBase;
        }

        public String getLinkBase() {
            return linkBase;
        }
    }

    public Services services;

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }
}
