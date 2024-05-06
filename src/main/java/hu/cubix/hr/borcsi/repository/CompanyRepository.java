package hu.cubix.hr.borcsi.repository;

import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.model.PositionsOfCompanyWithAvgSalary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @EntityGraph("company_full")
    @Query("SELECT c FROM Company c")
    List<Company> findAllWithEmployees();

    /*   @Query("SELECT c FROM Company c LEFT JOIN FETCH c.employeeList WHERE c.id=?1")
        public List<Company> findAllFull(Long id);*/
    @Query("SELECT c FROM Company c  JOIN c.employeeList e WHERE e.salary>?1")
    //List<Company> findSalaryHigher(int limit);
    Page<Company> findSalaryHigher(int limit, Pageable pageable);

    //@Query("SELECT c FROM Company c  WHERE (SELECT COUNT(emp) FROM c.employeeList emp)>?1")
    @Query("SELECT c FROM Company c  INNER JOIN  c.employeeList GROUP BY c HAVING COUNT(*) > ?1")
    List<Company> findNumberOfEmployeeHigher(int limit);

    @Query("SELECT NEW hu.cubix.hr.borcsi.model.PositionsOfCompanyWithAvgSalary(avg(e.salary), e.position)"
            + " FROM Company c INNER JOIN  c.employeeList e "
            + "WHERE c.id=?1 "
            + "GROUP BY e.position "
            + "ORDER BY AVG(e.salary) desc")
    List<PositionsOfCompanyWithAvgSalary> findAverageSalaryByPosition(Long id);

    //@EntityGraph(attributePaths = {"employeeList"})
    @EntityGraph("company_full")
    Optional<Company> findById(Long id);

}
