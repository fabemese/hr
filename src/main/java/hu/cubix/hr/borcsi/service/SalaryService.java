package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    EmployeeService employeeService;

    public SalaryService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setEmployeeSalary(Employee employee) {
        employee.setSalary((employeeService.getPayRaisePercent(employee) + 100) * employee.getSalary() / 100);
    }
}
