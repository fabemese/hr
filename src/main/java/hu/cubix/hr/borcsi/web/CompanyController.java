package hu.cubix.hr.borcsi.web;

import hu.cubix.hr.borcsi.dto.CompanyDto;
import hu.cubix.hr.borcsi.dto.EmployeeDto;
import hu.cubix.hr.borcsi.mapper.CompanyMapper;
import hu.cubix.hr.borcsi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyMapper companyMapper;

    private List<EmployeeDto> employees1 = new ArrayList<>();
    private List<EmployeeDto> employees2 = new ArrayList<>();
    private Map<Long, CompanyDto> companies = new HashMap<>();

    {
        employees1.add(new EmployeeDto(
                1L, "name1", "position", 1000,
                LocalDateTime.of(2024, 01, 01, 00, 00, 00))
        );
        employees1.add(new EmployeeDto(
                2L, "name", "position", 2000, LocalDateTime.of(2014, 01, 01, 00, 00, 00))
        );
    }

    {
        employees2.add(new EmployeeDto(
                21L, "name1", "position", 1000,
                LocalDateTime.of(2024, 01, 01, 00, 00, 00))
        );
    }

    {
        companies.put(1L, new CompanyDto(1L, 111, "comp1", "address1", employees1));
        companies.put(2L, new CompanyDto(2L, 222, "comp2", "address2", employees2));
    }

    @GetMapping
    public List<CompanyDto> getAllCompanies(@RequestParam Optional<Boolean> full) {
        return companyMapper.companiesToDtos(companyService.findAll(full));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable Long id, @RequestParam Optional<Boolean> full) {
        CompanyDto companyDto = companyMapper.companyToDto(companyService.findById(id, full));
        if (companyDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(companyDto);
    }

    @PostMapping
    public ResponseEntity<List<CompanyDto>> createCompany(@RequestBody CompanyDto companyDto) {
        if (companies.containsKey(companyDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        companies.put(companyDto.getId(), companyDto);
        return ResponseEntity.ok(new ArrayList<>(companies.values()));
    }

    @PostMapping("/{id}")
    public ResponseEntity<List<CompanyDto>> editEmployee(@RequestBody CompanyDto companyDto) {
        if (!companies.containsKey(companyDto.getId())) {
            return ResponseEntity.notFound().build();
        }
        companies.put(companyDto.getId(), companyDto);
        return ResponseEntity.ok(new ArrayList<>(companies.values()));
    }

    @DeleteMapping("/{id}")
    public List<CompanyDto> deleteCompany(@PathVariable Long id) {
        companies.remove(id);
        return new ArrayList<>(companies.values());
    }

    @PutMapping("/{id}")
    public CompanyDto addNewEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        companies.get(id).getEmployeeDtoList().add(employeeDto);
        return companies.get(id);

    }

    @DeleteMapping("/{id}/employee/{employeeId}")
    public CompanyDto deleteEmployee(@PathVariable Long id, @PathVariable Long employeeId) {
        List<EmployeeDto> newEmployees = companies.get(id).getEmployeeDtoList().stream().filter(e -> !e.getId().equals(employeeId)).collect(Collectors.toList());
        companies.get(id).setEmployeeDtoList(newEmployees);
        return companies.get(id);
    }

    @PostMapping("/{id}/employee")
    public CompanyDto newEmployees(@PathVariable Long id, @RequestBody List<EmployeeDto> newEmployees) {
        companies.get(id).setEmployeeDtoList(newEmployees);
        return companies.get(id);

    }
}
