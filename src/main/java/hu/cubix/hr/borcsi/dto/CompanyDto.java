package hu.cubix.hr.borcsi.dto;

import java.util.List;

public class CompanyDto {
    private Long id;
    private Integer registrationNumber;
    private String name;
    private String address;
    private List<EmployeeDto> employeeDtoList;

    public CompanyDto(Long id, Integer registrationNumber, String name, String address, List<EmployeeDto> employeeDtoList) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.address = address;
        this.employeeDtoList = employeeDtoList;
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

    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }
}
