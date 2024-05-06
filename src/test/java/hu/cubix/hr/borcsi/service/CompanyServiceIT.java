package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.dto.CompanyDto;
import hu.cubix.hr.borcsi.dto.EmployeeDto;
import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.model.CompanyType;
import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.repository.CompanyRepository;
import hu.cubix.hr.borcsi.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureTestDatabase
public class CompanyServiceIT {
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

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void testAddNewEmployee() {
        Company company = new Company(null, 123, "test name", "test addresss", null, CompanyType.BT);
        Company createdCompany = companyService.createCompany(company);
        Employee employee = new Employee(null, "test name", "position", 1000,
                LocalDateTime.of(2024, 01, 01, 00, 00, 00));

        Company modifiedCompany = companyService.addNewEmployee(createdCompany.getId(), employee);
    /*    List<Employee> employeeList=modifiedCompany.getEmployeeList();
        assertThat(employeeList.size()==1);
        Employee employeeOfModifiedCompany=employeeList.get(0);
        Employee employeeFromDataBase=employeeRepository.getReferenceById(employeeOfModifiedCompany.getId());
        assertThat(employeeFromDataBase.getCompany().getId()==createdCompany.getId());
        assertThat(employeeFromDataBase.getSalary()==employee.getSalary());
        assertThat(employeeFromDataBase.getName()==employee.getName());
        assertThat(employeeFromDataBase.getPosition()==employee.getPosition());
        assertThat(employeeFromDataBase.getEntryDate()).isCloseTo(employee.getEntryDate(), within(1, ChronoUnit.MICROS));
   */
    }

    @Test
    public void testDeleteEmployee() {

    }

    @Test
    public void testReplaceEmployees() {

    }
}
