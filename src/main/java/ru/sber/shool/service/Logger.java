package ru.sber.shool.service;

public class Logger {
    private String serviceName;

    public Logger(String serviceName) {
        this.serviceName = serviceName;
    }

    public void sendStartServiceMassage() {
        System.out.println(serviceName + " start");
    }
}
