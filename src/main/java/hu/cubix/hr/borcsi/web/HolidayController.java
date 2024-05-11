package hu.cubix.hr.borcsi.web;

import hu.cubix.hr.borcsi.dto.HolidayDto;
import hu.cubix.hr.borcsi.dto.HolidayFilterDto;
import hu.cubix.hr.borcsi.mapper.HolidayMapper;
import hu.cubix.hr.borcsi.model.Holiday;
import hu.cubix.hr.borcsi.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/holiday")
public class HolidayController {

    @Autowired
    HolidayService holidayService;

    @Autowired
    HolidayMapper holidayMapper;

    @GetMapping
    public List<HolidayDto> findAll(@RequestBody HolidayFilterDto holidayFilterDto, Pageable pageable) {
        return holidayMapper.holidaysToDtos(holidayService.findAll(holidayFilterDto, pageable).getContent());
    }

    @PostMapping
    public Holiday createHoliday(@RequestBody HolidayDto holidayDto) {
        return holidayService.createHoliday(holidayMapper.dtoToHoliday(holidayDto));
    }

    @PutMapping
    public int judgeHoliday(@RequestParam Long id, @RequestParam Boolean isAccepted) {
        try {
            return holidayService.judging(id, isAccepted);
        } catch (AccessDeniedException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getReason(), e);
        }
    }

    @PutMapping("/change")
    public Holiday changeHolidayRequest(@RequestBody HolidayDto holiday) {
        try {
            return holidayService.changeHolidayRequest(holidayMapper.dtoToHoliday(holiday));
        } catch (AccessDeniedException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getReason(), e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHolidayRequest(@RequestParam Long id) {
        try {
            holidayService.deleteHolidayRequest(id);
        } catch (AccessDeniedException e) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, e.getReason(), e);
        }
    }
}
