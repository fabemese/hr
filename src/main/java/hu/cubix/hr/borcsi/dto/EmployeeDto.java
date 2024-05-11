package hu.cubix.hr.borcsi.dto;

import hu.cubix.hr.borcsi.model.Holiday;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class EmployeeDto {

    Long id;
    @NotEmpty
    @Size(min = 1)
    String name;
    @NotEmpty
    @Size(min = 1)
    String position;
    @Min(0)
    Integer salary;
    @Past
    LocalDateTime entryDate;
    CompanyDto company;
    //EmployeeDto manager;
    Long manager;

    List<Holiday> holidayList;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, String position, Integer salary, LocalDateTime entryDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.entryDate = entryDate;
    }

    public EmployeeDto(Long id, String name, String position, Integer salary, LocalDateTime entryDate, CompanyDto company, List<Holiday> holidayList) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.entryDate = entryDate;
        this.company = company;
        this.holidayList = holidayList;
    }

    public EmployeeDto(Long id, String name, String position, Integer salary, LocalDateTime entryDate, CompanyDto company, Long manager, List<Holiday> holidayList) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.entryDate = entryDate;
        this.company = company;
        this.manager = manager;
        this.holidayList = holidayList;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    public List<Holiday> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<Holiday> holidayList) {
        this.holidayList = holidayList;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }
}

