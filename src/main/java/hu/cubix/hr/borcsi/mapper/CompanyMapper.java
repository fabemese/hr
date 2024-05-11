package hu.cubix.hr.borcsi.mapper;

import hu.cubix.hr.borcsi.dto.CompanyDto;
import hu.cubix.hr.borcsi.dto.EmployeeDto;
import hu.cubix.hr.borcsi.model.Company;
import hu.cubix.hr.borcsi.model.Employee;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "employeeDtoList", source = "employeeList")
    CompanyDto companyToDto(Company company);

    @Mapping(target = "employeeDtoList", ignore = true)
    @Named("pure")
    CompanyDto companyToPureDto(Company company);

    @Mapping(target = "employeeDtoList", source = "employeeList")
    List<CompanyDto> companiesToDtos(List<Company> company);

    @IterableMapping(qualifiedByName = "pure")
    List<CompanyDto> companiesToPureDtos(List<Company> company);

    Company dtoToCompany(CompanyDto companyDto);

    @Mapping(target = "company", ignore = true)
    @Mapping(target = "manager", source = "manager.id")
    @Mapping(target = "holidayList", ignore = true)
    EmployeeDto employeeToDto(Employee employee);

    @Mapping(target = "manager", ignore = true)
    @Named("employee")
    Employee dtoToEmployee(EmployeeDto employee);

    @IterableMapping(qualifiedByName = "employee")
    List<Employee> dtosToEmployees(List<EmployeeDto> employees);
}
