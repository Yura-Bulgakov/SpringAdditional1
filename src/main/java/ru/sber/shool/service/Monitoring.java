package ru.sber.shool.service;

public class Monitoring {
    private String host;

    public Monitoring(String host) {
        this.host = host;
    }

    public void sendMetrics() {
        System.out.println("Send metrics in " + host);
    }
}
