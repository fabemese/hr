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

    @Mapping(target = "company.employeeDtoList", ignore = true)
    @Mapping(target = "manager", source = "manager.id")
    @Mapping(target = "holidayList", ignore = true)
    EmployeeDto employeeToDto(Employee employee);

    List<EmployeeDto> employeesToDtos(List<Employee> employee);

    @Mapping(target = "manager", ignore = true)
    Employee dtoToEmployee(EmployeeDto employeeDto);
}
