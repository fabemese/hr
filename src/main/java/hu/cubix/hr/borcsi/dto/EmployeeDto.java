package hu.cubix.hr.borcsi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class EmployeeDto {

    Long id;
    @Size(min = 1)
    String name;
    @Size(min = 1)
    String position;
    @Min(0)
    Integer salary;
    @Past
    LocalDateTime entryDate;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, String position, Integer salary, LocalDateTime entryDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.entryDate = entryDate;
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

