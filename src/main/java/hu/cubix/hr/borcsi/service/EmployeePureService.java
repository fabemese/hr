package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
abstract public class EmployeePureService implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> save(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            return null;
        }
        employeeRepository.save(employee);
        return employeeRepository.findAll();
    }

    public Employee edit(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            return employeeRepository.save(employee);
        }
        throw new NoSuchElementException();
    }


    public List<Employee> delete(Long id) {
        employeeRepository.deleteById(id);
        return employeeRepository.findAll();
    }

    public List<Employee> getTopSalaries(Optional<Integer> limit) {
        if (limit.isPresent()) {
            return employeeRepository.findAll().stream().filter(e -> e.getSalary() > limit.get()).toList();
        }
        return employeeRepository.findAll();
    }

    public List<Employee> findByPosition(String position) {
        return employeeRepository.findByPosition(position);
    }

    public List<Employee> findEmployeeNameStartWith(String startWith) {
        return employeeRepository.findByNameStartingWithIgnoreCase(startWith);
    }

    public List<Employee> findEntryDateBetween(LocalDateTime start, LocalDateTime end) {
        return employeeRepository.findByEntryDateBetween(start, end);
    }

    public List<Employee> getEmployeesLikeEmployee(Employee employee) {
        return null;
    }


}
