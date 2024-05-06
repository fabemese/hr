package hu.cubix.hr.borcsi.web;

import hu.cubix.hr.borcsi.dto.CompanyDto;
import hu.cubix.hr.borcsi.dto.EmployeeDto;
import hu.cubix.hr.borcsi.mapper.CompanyMapper;
import hu.cubix.hr.borcsi.mapper.EmployeeMapper;
import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping
    public List<CompanyDto> getAllCompanies(@RequestParam Optional<Boolean> full) {
        if (full.orElse(false)) {
            return companyMapper.companiesToDtos(companyService.findAllWithEmployees());
        } else {
            return companyMapper.companiesToPureDtos(companyService.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable Long id, @RequestParam Optional<Boolean> full) {
        try {
            Company company = companyService.findById(id, full);
            if (full.orElse(false)) {
                return ResponseEntity.ok(companyMapper.companyToDto(company));
            } else return ResponseEntity.ok(companyMapper.companyToPureDto(company));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<List<CompanyDto>> createCompany(@RequestBody CompanyDto companyDto) {
        /*if (companies.containsKey(companyDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        companies.put(companyDto.getId(), companyDto);
        return ResponseEntity.ok(new ArrayList<>(companies.values()));
         */
        Company company = companyService.createCompany(companyMapper.dtoToCompany(companyDto));
        return ResponseEntity.ok(Arrays.asList(companyMapper.companyToDto(company)));
    }


    @DeleteMapping("/{id}")
    public List<CompanyDto> deleteCompany(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public CompanyDto addNewEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Company company = companyService.addNewEmployee(id, employeeMapper.dtoToEmployee(employeeDto));
        return companyMapper.companyToDto(company);

    }

    @DeleteMapping("/{id}/employee/{employeeId}")
    public CompanyDto deleteEmployee(@PathVariable Long id, @PathVariable Long employeeId) {
        Company company = companyService.deleteEmployee(id, employeeId);
        return companyMapper.companyToDto(company);
    }

    @PostMapping("/{id}/employee")
    public CompanyDto newEmployees(@PathVariable Long id, @RequestBody List<EmployeeDto> newEmployees) {
        Company company = companyService.replaceEmployees(id, companyMapper.dtosToEmployees(newEmployees));
        return companyMapper.companyToDto(company);
    }
}
