package hu.cubix.hr.borcsi.mapper;

import hu.cubix.hr.borcsi.dto.EmployeeDto;
import hu.cubix.hr.borcsi.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "company.employeeList", ignore = true)
    EmployeeDto employeeToDto(Employee employee);

    List<EmployeeDto> employeesToDtos(List<Employee> employee);

    Employee dtoToEmployee(EmployeeDto employeeDto);
}
