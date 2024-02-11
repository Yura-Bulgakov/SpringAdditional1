package ru.sber.shool.factory;

import org.springframework.stereotype.Component;
import ru.sber.shool.service.Monitoring;

@Component
public class MonitoringBeanFactory {
    public Monitoring createMonitoringService(String host) {
        return new Monitoring(host);
    }
}
