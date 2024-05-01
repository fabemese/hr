package hu.cubix.hr.borcsi.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Company {
    @Id
    @GeneratedValue
    private Long id;
    private Integer registrationNumber;
    private String name;
    private String address;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employeeList;


    @Enumerated(EnumType.STRING)
   /* @ElementCollection
    @CollectionTable(name="COMP_TYP")
    private Set<CompanyType> companyType;*/
    private CompanyType companyType;

    public Company() {
    }


    public Company(Long id, Integer registrationNumber, String name, String address, List<Employee> employeeList, CompanyType companyType) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
        this.employeeList = employeeList;
        this.companyType = companyType;
    }


    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    //public Set<CompanyType> getCompanyType() {
    public CompanyType getCompanyType() {
        return companyType;
    }

    //    public void setCompanyType(Set<CompanyType> companyType) {
    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

}
