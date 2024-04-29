package hu.cubix.hr.borcsi.mapper;

import hu.cubix.hr.borcsi.dto.CompanyDto;
import hu.cubix.hr.borcsi.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "employeeDtoList", source = "employeeList")
    CompanyDto companyToDto(Company company);

    @Mapping(target = "employeeList", source = "employeeDtoList")
    List<CompanyDto> companiesToDtos(List<Company> company);

    Company dtoToCompany(CompanyDto companyDto);


}
