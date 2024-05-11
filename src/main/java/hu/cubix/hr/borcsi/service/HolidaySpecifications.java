package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.model.Employee_;
import hu.cubix.hr.borcsi.model.Holiday;
import hu.cubix.hr.borcsi.model.Holiday_;
import org.springframework.data.jpa.domain.Specification;

public class HolidaySpecifications {
    public static Specification<Holiday> hasAccepted(Boolean isApproved) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Holiday_.isAccepted), isApproved));
    }

    public static Specification<Holiday> hasEmployeeName(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .like(
                        criteriaBuilder.lower(root.get(Holiday_.createdBy).get(Employee_.name)), name.toLowerCase() + "%"));
    }

    public static Specification<Holiday> hasManagerName(String name) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .like(
                        criteriaBuilder.lower(root.get(Holiday_.createdBy).get(Employee_.manager).get(Employee_.name)), name.toLowerCase() + "%"));
    }

}
