package hu.cubix.hr.borcsi.service;


import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CompanyService {
    private List<Employee> employees1 = new ArrayList<>();
    private List<Employee> employees2 = new ArrayList<>();
    private Map<Long, Company> companies = new HashMap<>();

    {
        employees1.add(new Employee(
                1L, "name1", "position", 1000,
                LocalDateTime.of(2024, 01, 01, 00, 00, 00))
        );
        employees1.add(new Employee(
                2L, "name", "position", 2000, LocalDateTime.of(2014, 01, 01, 00, 00, 00))
        );
    }

    {
        employees2.add(new Employee(
                21L, "name1", "position", 1000,
                LocalDateTime.of(2024, 01, 01, 00, 00, 00))
        );
    }

    {
        companies.put(1L, new Company(1L, 111, "servicecomp1", "address1", employees1));
        companies.put(2L, new Company(2L, 222, "servicecomp2", "address2", employees2));
    }

    public List<Company> findAll(Optional<Boolean> full) {
        if (full.orElse(false)) {
            return new ArrayList<>(companies.values());
        } else {
            return companies.values().stream().map(c -> new Company(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null)).toList();
        }
    }

    public Company findById(Long id, Optional<Boolean> full) {
        if (!companies.containsKey(id)) return null;
        if (full.orElse(false))
            return companies.get(id);
        Company company = companies.get(id);
        company = new Company(company.getId(), company.getRegistrationNumber(), company.getName(), company.getAddress(), company.getEmployeeList());
        company.setEmployeeList(null);
        return company;
    }

}
