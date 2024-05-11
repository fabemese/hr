package hu.cubix.hr.borcsi.service;

import hu.cubix.hr.borcsi.dto.HolidayFilterDto;
import hu.cubix.hr.borcsi.model.Employee;
import hu.cubix.hr.borcsi.model.Holiday;
import hu.cubix.hr.borcsi.repository.HolidayRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class HolidayService {
    @Autowired
    HolidayRepository holidayRepository;

    @Autowired
    EmployeePureService employeeService;

    public Page<Holiday> findAll(HolidayFilterDto example, Pageable pageable) {
        Boolean isAccepted = example.getStatus();
        String createdByName = example.getEmployeeName();
        String nameofManager = example.getManagerName();
        LocalDateTime createdFrom = example.getCreatedFrom();
        LocalDateTime createdTo = example.getCreatedTo();
        LocalDateTime holidayStart = example.getHolidayStart();
        LocalDateTime holidayEnd = example.getHolidayEnd();

        Specification<Holiday> spec = Specification.where(null);
        spec.and(HolidaySpecifications.hasAccepted(isAccepted));

        if (StringUtils.hasText(createdByName)) {
            spec = spec.and(HolidaySpecifications.hasEmployeeName(createdByName));
        }
        if (StringUtils.hasText(nameofManager)) {
            spec = spec.and(HolidaySpecifications.hasManagerName(nameofManager));
        }

        Pageable firstPageWithTwoElements = PageRequest.of(0, 3);
        return holidayRepository.findAll(spec, pageable);
    }

    @Transactional
    public Holiday createHoliday(Holiday holiday) {
        Employee employee = getCurrentUser();
        holiday.setCreatedBy(employee);
        holiday.setCreatedAt(LocalDateTime.now());
        employee.addHoliday(holiday);
        return holidayRepository.save(holiday);
    }

    @Transactional
    public int judging(Long id, Boolean isAccepted) throws AccessDeniedException {
        Holiday holiday = holidayRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Employee modifier = getCurrentUser();
        Employee manager = holiday.getCreatedBy().getManager();
        if (manager != null && manager.getId() == modifier.getId()) {
            return holidayRepository.judgeRequest(id, isAccepted);
        } else
            throw new AccessDeniedException("Only manager of employee can approve a holiday request");
    }

    @Transactional
    public Holiday changeHolidayRequest(Holiday holiday) throws AccessDeniedException {
        Holiday oldHoliday = holidayRepository.findById(holiday.getId()).orElseThrow(NoSuchElementException::new);
        Employee modifier = getCurrentUser();
        if (oldHoliday.getCreatedBy().getId() != modifier.getId() || oldHoliday.getAccepted() != null) {
            throw new AccessDeniedException("Only owner can change a holiday request");
        }
        holiday.setCreatedBy(getCurrentUser());
        holiday.setCreatedAt(LocalDateTime.now());
        return holiday;

    }

    @Transactional
    public void deleteHolidayRequest(Long id) throws AccessDeniedException {
        Holiday oldHoliday = holidayRepository.findById(id).orElseThrow(NoSuchElementException::new);
        Employee modifier = getCurrentUser();
        if (oldHoliday.getCreatedBy().getId() == modifier.getId() && oldHoliday.getAccepted() == null) {
            //modifier.getHolidayList().remove(oldHoliday);
            oldHoliday.getCreatedBy().getHolidayList().remove(oldHoliday);
            holidayRepository.deleteById(id);
        } else
            throw new AccessDeniedException("Only owner can delete a holiday request");
    }

    private Employee getCurrentUser() {
        return employeeService.findById(1L).get();
    }
}
