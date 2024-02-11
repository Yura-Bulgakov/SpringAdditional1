package ru.sber;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sber.shool.service.Logger;
import ru.sber.shool.service.Monitoring;
import ru.sber.shool.service.Service;

import java.util.Map;

public class InheritanceDemo {

    public static void main(String... args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        Map<String, Service> serviceBeans = applicationContext.getBeansOfType(Service.class);

        System.out.println("Бины сервисы: ");
        serviceBeans.forEach((beanName, bean) -> {
            System.out.printf("Имя бина: %s, бин: %s %n", beanName, bean);
        });

        Map<String, Logger> loggerBeans = applicationContext.getBeansOfType(Logger.class);
        System.out.println("Бины логгеры: ");
        loggerBeans.forEach((beanName, bean) -> {
            System.out.printf("Имя бина: %s, бин: %s %n", beanName, bean);
            bean.sendStartServiceMassage();
        });

        Map<String, Monitoring> monitoringBeans = applicationContext.getBeansOfType(Monitoring.class);
        System.out.println("Бины мониторинги: ");
        monitoringBeans.forEach((beanName, bean) -> {
            System.out.printf("Имя бина: %s, бин: %s %n", beanName, bean);
            bean.sendMetrics();
        });
    }
}
