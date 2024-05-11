package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.model.Company_;
import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.model.Employee_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class EmployeeSpecifications {
    public static Specification<Employee> hasId(long id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Employee_.id), id));
    }

    public static Specification<Employee> hasName(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .like(
                        criteriaBuilder.lower(root.get(Employee_.name)), name.toLowerCase() + "%"));
    }

    public static Specification<Employee> hasPosition(String position) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(Employee_.position), position));
    }

    public static Specification<Employee> hasSalary(Integer salary) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .between(root.get(Employee_.salary), (int) (salary * 0.95), (int) Math.ceil(salary * 1.05)));
    }

    public static Specification<Employee> hasEntryDate(LocalDateTime entryDate) {
        LocalDateTime entry = LocalDateTime.of(entryDate.toLocalDate(), LocalTime.of(0, 0));
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .between(root.get(Employee_.entryDate), entry, entry.plusDays(1)));
    }

    public static Specification<Employee> hasCompany(Company company) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get(Employee_.company).get(Company_.name), company.getName() + "%"));
    }
}
