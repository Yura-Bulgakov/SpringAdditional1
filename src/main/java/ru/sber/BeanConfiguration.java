package ru.sber;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.sber.shool.ProcessorCandidatesSupplier;
import ru.sber.shool.SchoolBeanDefinitionGenerate;
import ru.sber.shool.SchoolServiceBeanFactoryPostProcessor;

@Configuration
@ComponentScan({"ru.sber.shool.service.identity", "ru.sber.shool.factory"})
public class BeanConfiguration {

    @Bean
    public ProcessorCandidatesSupplier processorCandidatesSupplier() {
        return new ProcessorCandidatesSupplier();
    }

    @Bean
    public SchoolBeanDefinitionGenerate schoolBeanDefinitionGenerate() {
        return new SchoolBeanDefinitionGenerate();
    }

    @Bean
    @DependsOn({"processorCandidatesSupplier", "schoolBeanDefinitionGenerate"})
    public BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new SchoolServiceBeanFactoryPostProcessor(processorCandidatesSupplier(), schoolBeanDefinitionGenerate());
    }
}
