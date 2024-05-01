package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.model.CompanyType;
import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.repository.CompanyRepository;
import hu.cubix.hr.borcsi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class InitDbService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    public void clearDB() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    public void insertTestData() {
        Employee employeeA = new Employee(
                null, "name1", "position", 1000,
                LocalDateTime.of(2024, 01, 01, 00, 00, 00));
        Employee employeeB =
                new Employee(
                        null, "name2", "hr", 2000,
                        LocalDateTime.of(2014, 01, 01, 00, 00, 00));
        Employee employeeC = new Employee(
                null, "name3", "position", 1000,
                LocalDateTime.of(2023, 01, 01, 00, 00, 00));

        Company company1 = new Company(null, 111, "servicecomp1", "address1", null, CompanyType.BT);
        Company company2 = new Company(null, 222, "servicecomp2", "address2", null, CompanyType.KFT);
        employeeA.setCompany(company1);
        employeeB.setCompany(company1);
        employeeC.setCompany(company2);
        List<Employee> employees1 = Arrays.asList(employeeA, employeeB);
        List<Employee> employees2 = Arrays.asList(employeeC);
        company1.setEmployeeList(employees1);
        company2.setEmployeeList(employees2);

        companyRepository.save(company1);
        companyRepository.save(company2);
    }
}
