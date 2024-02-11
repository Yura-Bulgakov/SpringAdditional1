package ru.sber.shool.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceSchool {

    String serviceName();

    String host();
}
