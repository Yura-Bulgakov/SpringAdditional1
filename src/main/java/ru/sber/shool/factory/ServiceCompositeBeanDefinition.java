package ru.sber.shool.factory;

import org.springframework.stereotype.Component;
import ru.sber.shool.service.Service;

@Component
public class ServiceCompositeBeanDefinition {


    public Service create() {
        return new Service();
    }
}
