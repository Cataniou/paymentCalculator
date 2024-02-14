package com.lunchsplit.model;

public class PaymentLink {

    public enum Services {
        NENHUM(""),
        PICPAY("https://picpay.me/");


        private final String linkBase;

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
