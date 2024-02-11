package ru.sber.shool.factory;

import org.springframework.stereotype.Component;
import ru.sber.shool.service.Logger;

@Component
public class LoggerBeanFactory {
    public Logger createLoggerService(String serviceName) {
        return new Logger(serviceName);
    }
}
