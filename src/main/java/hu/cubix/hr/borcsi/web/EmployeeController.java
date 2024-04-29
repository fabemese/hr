package hu.cubix.hr.borcsi.web;

import hu.cubix.hr.borcsi.dto.EmployeeDto;
import hu.cubix.hr.borcsi.mapper.EmployeeMapper;
import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.service.EmployeePureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeePureService employeeService;

    /*    @GetMapping
        public List<EmployeeDto> getAllEmployees() {
            return employeeMapper.employeesToDtos(employeeService.findAll());
        }
  /*      @GetMapping(params = "limit")
        public List<EmployeeDto> getTopSalaries(@RequestParam Integer limit) {
            return employees.values()).stream().filter(e -> e.getSalary() > limit).toList();
        }
    */
    @GetMapping()
    public List<EmployeeDto> getTopSalaries(@RequestParam Optional<Integer> limit) {
        return employeeMapper.employeesToDtos(employeeService.getTopSalaries(limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(employeeMapper.employeeToDto(employee));
    }

    @GetMapping(params = "position")
    public List<EmployeeDto> getEmployeeByPosition(@Param("position") String position) {
        return employeeMapper.employeesToDtos(employeeService.findByPosition(position));

    }

    @GetMapping(params = "startwith")
    public List<EmployeeDto> getEmployeeNameStartWith(@RequestParam String startwith) {
        return employeeMapper.employeesToDtos(employeeService.findEmployeeNameStartWith(startwith));
    }

    @GetMapping(params = {"start", "end"})
    public List<EmployeeDto> getEmployeeEntryDateBetween(@RequestParam LocalDateTime start,
                                                         @RequestParam LocalDateTime end) {
        return employeeMapper.employeesToDtos(employeeService.findEntryDateBetween(start, end));
    }

    @PostMapping
    public ResponseEntity<List<EmployeeDto>> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        List<Employee> employees = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
        if (employees == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(employeeMapper.employeesToDtos(employees));
    }

    @PostMapping("/{id}")
    public ResponseEntity<EmployeeDto> editEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        try {
            return ResponseEntity.ok(
                    employeeMapper.employeeToDto(
                            employeeService.edit(employeeMapper.dtoToEmployee(employeeDto))));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public List<EmployeeDto> deleteEmployee(@PathVariable Long id) {

        return employeeMapper.employeesToDtos(employeeService.delete(id));
    }

}
