package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.config.HrConfigProperties;
import hu.cubix.hr.borcsi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!smart")
public class DefaultEmployeeService extends EmployeePureService {
    @Autowired
    HrConfigProperties hrConfigProperties;

    public int getPayRaisePercent(Employee employee) {

        return hrConfigProperties.getSalary().getDef().getPercent();
    }
}
