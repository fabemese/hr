package hu.cubix.hr.borcsi;

import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.service.CompanyService;
import hu.cubix.hr.borcsi.service.InitDbService;
import hu.cubix.hr.borcsi.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

    @Autowired
    SalaryService salaryService;
    @Autowired
    InitDbService initDbService;

    @Autowired
    CompanyService companyService;

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Employee employee = new Employee();
        employee.setEntryDate(LocalDateTime.of(2024, 01, 01, 00, 00, 00));
        employee.setSalary(1000);
        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employee);
        employee = new Employee();
        employee.setEntryDate(LocalDateTime.of(2014, 01, 01, 00, 00, 00));
        employee.setSalary(2000);
        employeeSet.add(employee);
        employeeSet.forEach(e -> {
            System.out.println(e.getEntryDate());
            salaryService.setEmployeeSalary(e);
            System.out.println(e.getSalary());
        });

//        initDbService.clearDB();
        //      initDbService.insertTestData();

        //companyService.findHigherSalary(100).stream().forEach(c -> System.out.println(c.getId()));
        Page<Company> page = companyService.findHigherSalary(100);
        System.out.println("összes employee " + page.getTotalElements());
        System.out.println("összes oldal " + page.getTotalPages());

        companyService.findNumberOfEmployeeHigher(0).stream().forEach(c -> System.out.println(c.getId()));
        companyService.findPositionsOfCompanyWithAvgSalary(702L).stream()
                .forEach(c -> System.out.println(c.getAvgSalary() + " " + c.getPosition()));
    }
}
