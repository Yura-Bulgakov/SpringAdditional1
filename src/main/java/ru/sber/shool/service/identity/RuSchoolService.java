package ru.sber.shool.service.identity;


import org.springframework.stereotype.Component;
import ru.sber.shool.service.ServiceSchool;

@ServiceSchool(serviceName = "School2", host = "yandex.ru")
@Component
public class RuSchoolService {
    public RuSchoolService() {
    }
}
