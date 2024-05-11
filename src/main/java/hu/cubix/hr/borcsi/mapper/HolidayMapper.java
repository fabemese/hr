package hu.cubix.hr.borcsi.mapper;

import hu.cubix.hr.borcsi.dto.HolidayDto;
import hu.cubix.hr.borcsi.model.Holiday;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HolidayMapper {

    HolidayMapper INSTANCE = Mappers.getMapper(HolidayMapper.class);

    @Mapping(target = "createdBy.holidayList", ignore = true)
    @Mapping(target = "createdBy.manager", source = "createdBy.manager.id")
    HolidayDto holidayToDto(Holiday holiday);

    @Mapping(target = "createdBy.manager", ignore = true)
    Holiday dtoToHoliday(HolidayDto holidayDto);

    List<HolidayDto> holidaysToDtos(List<Holiday> holidayList);


}
