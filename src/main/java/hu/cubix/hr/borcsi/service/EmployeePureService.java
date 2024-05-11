package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Transactional
    public Employee save(Employee employee) {
        if (employee.getId() != null && employeeRepository.existsById(employee.getId())) {
            return null;
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee edit(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            return employeeRepository.save(employee);
        }
        throw new NoSuchElementException();
    }

    @Transactional
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


    public List<Employee> findEmployeesByExample(Employee example) {
        Long id = example.getId();
        String name = example.getName();
        String position = example.getPosition();
        Integer salary = example.getSalary();
        LocalDateTime entryDate = example.getEntryDate();

        Specification<Employee> spec = Specification.where(null);
        if (id > 0) {
            spec = spec.and(EmployeeSpecifications.hasId(id));
        }
        if (StringUtils.hasText(name)) {
            spec = spec.and(EmployeeSpecifications.hasName(name));
        }
        if (StringUtils.hasText(position)) {
            spec = spec.and(EmployeeSpecifications.hasPosition(position));
        }
        if (salary != null && salary > 0) {
            spec = spec.and(EmployeeSpecifications.hasSalary(salary));
        }
        if (entryDate != null)
            spec = spec.and(EmployeeSpecifications.hasEntryDate(entryDate));

        return employeeRepository.findAll(spec, Sort.by(("id")));
    }
}
