package ru.sber.shool;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.util.StringUtils;
import ru.sber.shool.factory.LoggerBeanFactory;
import ru.sber.shool.factory.MonitoringBeanFactory;
import ru.sber.shool.factory.ServiceCompositeBeanDefinition;
import ru.sber.shool.service.Service;
import ru.sber.shool.service.ServiceSchool;

import java.util.HashMap;
import java.util.Map;

public class SchoolBeanDefinitionGenerate {

    private final static String SERVICE_FACTORY_BEAN = StringUtils.uncapitalize(ServiceCompositeBeanDefinition.class.getSimpleName());
    private final static String LOGGER_FACTORY_BEAN = StringUtils.uncapitalize(LoggerBeanFactory.class.getSimpleName());
    private final static String MONITORING_FACTORY_BEAN = StringUtils.uncapitalize(MonitoringBeanFactory.class.getSimpleName());

    public SchoolBeanDefinitionGenerate() {
    }

    /**
     * //     * @param serviceName имя сервиса
     *
     * @param beanDefinition класса с аннотацией {@link ru.sber.shool.service.ServiceSchool}
     * @return
     */
    public Map<String, BeanDefinition> generateBeanDefinition(BeanDefinition beanDefinition) {
        Map<String, BeanDefinition> expectedBeanDefinitions = new HashMap<>();
        String serviceName, host;
        String beanClassName = beanDefinition.getBeanClassName();

        if (beanClassName != null) {
            ServiceSchool annotation = getAnnotationParameters(beanClassName);
            serviceName = annotation.serviceName();
            host = annotation.host();
        } else {
            throw new RuntimeException();
        }

        expectedBeanDefinitions.put(serviceName, createBeanDefinition(Service.class, "create", SERVICE_FACTORY_BEAN));
        expectedBeanDefinitions.put(serviceName + "Logger", createBeanDefinition(Service.class, "createLoggerService", LOGGER_FACTORY_BEAN, serviceName));
        expectedBeanDefinitions.put(serviceName + "Monitoring", createBeanDefinition(Service.class, "createMonitoringService", MONITORING_FACTORY_BEAN, host));
        return expectedBeanDefinitions;
    }

    private ServiceSchool getAnnotationParameters(String beanClassName) {
        try {
            Class<?> beanClass = Class.forName(beanClassName);
            return beanClass.getAnnotation(ServiceSchool.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private BeanDefinition createBeanDefinition(Class<?> beanClass, String factoryMethod, String factoryBean,
                                                Object... constructorArgs) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder.setFactoryMethodOnBean(factoryMethod, factoryBean);
        for (Object arg : constructorArgs) {
            beanDefinitionBuilder.addConstructorArgValue(arg);
        }
        return beanDefinitionBuilder.getBeanDefinition();
    }
}
