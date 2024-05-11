package hu.cubix.hr.borcsi.service;


import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.model.PositionsOfCompanyWithAvgSalary;
import hu.cubix.hr.borcsi.repository.CompanyRepository;
import hu.cubix.hr.borcsi.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Transactional
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
        List<Company> c = companyRepository.findSalaryHigher(limit);
        Pageable firstPageWithTwoElements = PageRequest.of(0, 3);
        Page<Company> cd = companyRepository.findSalaryHigherPageable(limit, firstPageWithTwoElements);
        Pageable firstPageWithTwoElements2 = PageRequest.of(0, 2);
        Page<Company> b = companyRepository.findSalaryHigherPageable(limit, firstPageWithTwoElements2);
        Pageable firstPageWithTwoElements3 = PageRequest.of(1, 3);
        Page<Company> s = companyRepository.findSalaryHigherPageable(limit, firstPageWithTwoElements3);
        Pageable firstPageWithTwoElements4 = PageRequest.of(2, 2);
        Page<Company> s2 = companyRepository.findSalaryHigherPageable(limit, firstPageWithTwoElements4);
        Pageable firstPageWithTwoElements5 = PageRequest.of(4, 2);
        Page<Company> s3 = companyRepository.findSalaryHigherPageable(limit, firstPageWithTwoElements5);
        System.out.println("s" + s.getTotalPages() + " " + s.getTotalElements());
        System.out.println("s2" + s2.getTotalPages() + " " + s2.getTotalElements());
        System.out.println("s3" + s3.getTotalPages() + " " + s3.getTotalElements());

        Pageable firstPageWithTwoElements52 = PageRequest.of(1, 3, Sort.by("id").descending());
        Page<Company> s5 = companyRepository.findSalaryHigherPageable(limit, firstPageWithTwoElements52);
        Pageable firstPageWithTwoElements6 = PageRequest.of(2, 2, Sort.by("id").descending());
        Page<Company> s6 = companyRepository.findSalaryHigherPageable(limit, firstPageWithTwoElements6);
        Pageable firstPageWithTwoElements7 = PageRequest.of(4, 2, Sort.by("id").descending());
        Page<Company> s7 = companyRepository.findSalaryHigherPageable(limit, firstPageWithTwoElements7);
        System.out.println("s5 " + s5.getTotalPages() + " " + s5.getTotalElements());
        System.out.println("s6 " + s6.getTotalPages() + " " + s6.getTotalElements());
        System.out.println("s7 " + s7.getTotalPages() + " " + s7.getTotalElements());

        System.out.println("s" + s.getNumber() + " " + s.getNumberOfElements() + " " + s.getSize());
        System.out.println("s2" + s2.getNumber() + " " + s2.getNumberOfElements() + " " + s2.getSize());
        System.out.println("s3" + s3.getNumber() + " " + s3.getNumberOfElements() + " " + s3.getSize());

        System.out.println("s5 " + s5.getNumber() + " " + s5.getNumberOfElements() + " " + s5.getSize());
        System.out.println("s6 " + s6.getNumber() + " " + s6.getNumberOfElements() + " " + s6.getSize());
        System.out.println("s7 " + s7.getNumber() + " " + s7.getNumberOfElements() + " " + s7.getSize());

        List<Company> l = s.getContent();
        List<Company> l2 = s.getContent();
        List<Company> l3 = s3.getContent();
        List<Company> l5 = s5.getContent();
        List<Company> l6 = s6.getContent();
        List<Company> l7 = s7.getContent();

        return cd;
    }

    public List<Company> findNumberOfEmployeeHigher(Integer limit) {
        return companyRepository.findNumberOfEmployeeHigher(limit);
    }

    public List<PositionsOfCompanyWithAvgSalary> findPositionsOfCompanyWithAvgSalary(Long id) {
        return companyRepository.findAverageSalaryByPosition(id);
    }

    @Transactional
    public Company addNewEmployee(Long id, Employee employee) {
        Company company = companyRepository.findById(id).get();
        company.addEmployee(employee);
        employeeRepository.save(employee);
        return company;
    }

    @Transactional
    public Company deleteEmployee(long id, long employeeId) {
        Company company = companyRepository.findById(id).get();
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setCompany(null);
        company.getEmployeeList().remove(employee);
        employeeRepository.save(employee);
        return company;
    }

    @Transactional
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
