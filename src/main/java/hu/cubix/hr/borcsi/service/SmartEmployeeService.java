package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.config.HrConfigProperties;
import hu.cubix.hr.borcsi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.TreeMap;

import static java.time.LocalDate.now;


@Component
@Profile("smart")
public class SmartEmployeeService extends EmployeePureService {
    @Autowired
    HrConfigProperties hrConfigProperties;

    public int getPayRaisePercent(Employee employee) {
        double years = (double) employee.getEntryDate().toLocalDate().until(now(), ChronoUnit.DAYS) / 365;
        TreeMap<Double, Integer> limits = hrConfigProperties.getSalary().getSmart().getLimit();
        return limits.get(limits.lowerKey(years));
    }
}
