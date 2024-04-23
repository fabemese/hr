package hu.cubix.hr.borcsi.web;

import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public Integer payRaiseForEmployee(@RequestBody Employee employee) {
        return employeeService.getPayRaisePercent(employee);
    }
}
