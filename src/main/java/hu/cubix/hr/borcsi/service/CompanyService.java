package hu.cubix.hr.borcsi.service;


import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.model.PositionsOfCompanyWithAvgSalary;
import hu.cubix.hr.borcsi.repository.CompanyRepository;
import hu.cubix.hr.borcsi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> findAllWithEmployees() {
        return companyRepository.findAllWithEmployees();
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(Long id, Optional<Boolean> full) {
        return companyRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Page<Company> findHigherSalary(Integer limit) {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 3);
        return companyRepository.findSalaryHigher(limit, firstPageWithTwoElements);
    }

    public List<Company> findNumberOfEmployeeHigher(Integer limit) {
        return companyRepository.findNumberOfEmployeeHigher(limit);
    }

    public List<PositionsOfCompanyWithAvgSalary> findPositionsOfCompanyWithAvgSalary(Long id) {
        return companyRepository.findAverageSalaryByPosition(id);
    }

    public Company addNewEmployee(Long id, Employee employee) {
        Company company = companyRepository.findById(id).get();
        company.addEmployee(employee);
        employeeRepository.save(employee);
        return company;
    }

    public Company deleteEmployee(long id, long employeeId) {
        Company company = companyRepository.findById(id).get();
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setCompany(null);
        company.getEmployeeList().remove(employee);
        employeeRepository.save(employee);
        return company;
    }

    public Company replaceEmployees(long id, List<Employee> employees) {
        Company company = companyRepository.findById(id).get();
        company.getEmployeeList().forEach(e -> e.setCompany(null));
        company.getEmployeeList().clear();

        employees.forEach(e -> {
            company.addEmployee(e);
            employeeRepository.save(e);
        });

        return company;
    }

}
