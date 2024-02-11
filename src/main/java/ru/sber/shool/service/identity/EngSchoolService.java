package ru.sber.shool.service.identity;


import org.springframework.stereotype.Component;
import ru.sber.shool.service.ServiceSchool;

@ServiceSchool(serviceName = "School1", host = "google.com")
@Component
public class EngSchoolService {
    public EngSchoolService() {
    }
}
