package hu.cubix.hr.borcsi.repository;

import hu.cubix.hr.borcsi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    public List<Employee> findByPosition(String position);

    public List<Employee> findByNameStartingWithIgnoreCase(String startWith);

    public List<Employee> findByEntryDateBetween(LocalDateTime start, LocalDateTime end);
}
