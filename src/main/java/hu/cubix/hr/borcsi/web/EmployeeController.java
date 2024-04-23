package hu.cubix.hr.borcsi.web;

import hu.cubix.hr.borcsi.dto.EmployeeDto;
import hu.cubix.hr.borcsi.mapper.EmployeeMapper;
import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.service.EmployeePureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<EmployeeDto> getTopSalaries2(@RequestParam Optional<Integer> limit) {
        return employeeMapper.employeesToDtos(employeeService.getTopSalaries(limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeMapper.employeeToDto(employee));
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
    public ResponseEntity<List<EmployeeDto>> editEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        List<Employee> employees = employeeService.edit(employeeMapper.dtoToEmployee(employeeDto));
        if (employees == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(employeeMapper.employeesToDtos(employees));
    }

    @DeleteMapping("/{id}")
    public List<EmployeeDto> deleteEmployee(@PathVariable Long id) {

        return employeeMapper.employeesToDtos(employeeService.delete(id));
    }

}
