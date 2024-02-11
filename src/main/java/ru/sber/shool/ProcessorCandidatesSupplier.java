package ru.sber.shool;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import ru.sber.shool.service.ServiceSchool;

import java.util.HashMap;
import java.util.Map;

public class ProcessorCandidatesSupplier {
    public ProcessorCandidatesSupplier() {
    }

    /**
     * Метод находит компоненты с аннтацией {@link ru.sber.shool.service.ServiceSchool}
     *
     * @param beanFactory
     * @return Map с BeanDefinition
     */
    public Map<String, BeanDefinition> getCandidates(DefaultListableBeanFactory beanFactory) {
        Map<String, BeanDefinition> beanMap = new HashMap<>();
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName != null && hasServiceSchoolAnnotation(beanClassName)) {
                beanMap.put(name, beanDefinition);
            }
        }
        return beanMap;
    }

    private boolean hasServiceSchoolAnnotation(String beanClassName) {
        try {
            Class<?> beanClass = Class.forName(beanClassName);
            return beanClass.isAnnotationPresent(ServiceSchool.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
