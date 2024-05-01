package hu.cubix.hr.borcsi.service;


import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.model.PositionsOfCompanyWithAvgSalary;
import hu.cubix.hr.borcsi.repository.CompanyRepository;
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

    public List<Company> findAll() {
        return companyRepository.findAll();

      /*  if (full.orElse(false)) {
            return new ArrayList<>(companies.values());
        } else {
            return companies.values().stream().map(c -> new Company(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null)).toList();
        }*/
    }

    public Company findById(Long id, Optional<Boolean> full) {

        return companyRepository.findById(id).orElseThrow(NoSuchElementException::new);
       /* if (!companies.containsKey(id)) return null;
        if (full.orElse(false))
            return companies.get(id);
        Company company = companies.get(id);
        company = new Company(company.getId(), company.getRegistrationNumber(), company.getName(), company.getAddress(), company.getEmployeeList());
        company.setEmployeeList(null);
        return company;*/
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

}
