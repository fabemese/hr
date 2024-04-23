package hu.cubix.hr.borcsi.model;

import java.time.LocalDateTime;

public class Employee {
    Long id;
    String name;
    String position;
    Integer salary;
    LocalDateTime entryDate;

    public Employee() {
    }

    public Employee(Long id, String name, String position, Integer salary, LocalDateTime entryDate) {
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

    @Override
    public boolean equals(Object employee) {
        if (employee == this)
            return true;
        if (!(employee instanceof Employee))
            return false;
        return this.id == ((Employee) employee).getId();
    }
}
