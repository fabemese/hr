package hu.cubix.hr.borcsi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String position;
    Integer salary;
    LocalDateTime entryDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    Employee manager;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    List<Holiday> holidayList;

    public Employee() {
    }

    public Employee(Long id, String name, String position, Integer salary, LocalDateTime entryDate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.entryDate = entryDate;
    }

    public void addHoliday(Holiday holiday) {
        if (this.holidayList == null)
            this.holidayList = new ArrayList<>();

        this.holidayList.add(holiday);
        holiday.setCreatedBy(this);
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Holiday> getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List<Holiday> holidayList) {
        this.holidayList = holidayList;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object employee) {
        if (employee == this)
            return true;
        if (!(employee instanceof Employee))
            return false;
        return this.id.equals(((Employee) employee).getId());
    }
}
