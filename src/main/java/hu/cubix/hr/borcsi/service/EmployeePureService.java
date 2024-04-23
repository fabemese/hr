package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;

@Service
abstract public class EmployeePureService implements EmployeeService {
    private Map<Long, Employee> employees = new HashMap<>();

    {
        employees.put(1L,
                new Employee(
                        1L, "name", "position", 1000,
                        LocalDateTime.of(2024, 1, 1, 0, 0, 0))
        );
        employees.put(2L,
                new Employee(
                        2L, "name", "position", 2000, LocalDateTime.of(2014, 1, 1, 0, 0, 0))
        );
    }

    public List<Employee> findAll() {
        return new ArrayList<Employee>(employees.values());
    }

    public Employee findById(long id) {
        return employees.get(id);
    }

    public List<Employee> save(Employee employee) {
        if (employees.containsKey(employee.getId())) {
            return null;
        }
        employees.put(employee.getId(), employee);
        return new ArrayList<Employee>(employees.values());
    }

    public List<Employee> edit(Employee employee) {
        if (!employees.containsKey(employee.getId())) {
            return null;
        }
        employees.put(employee.getId(), employee);
        return new ArrayList<Employee>(employees.values());
    }

    public List<Employee> delete(Long id) {
        employees.remove(id);
        return new ArrayList<Employee>(employees.values());
    }

    public List<Employee> getTopSalaries(@RequestParam Optional<Integer> limit) {
        if (limit.isPresent()) {
            return employees.values().stream().filter(e -> e.getSalary() > limit.get()).toList();
        }
        return new ArrayList<Employee>(employees.values());
    }
}
