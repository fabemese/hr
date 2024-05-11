package hu.cubix.hr.borcsi.dto;

import java.time.LocalDateTime;

public class HolidayFilterDto {
    private Boolean status;
    private String managerName;
    private String employeeName;
    private LocalDateTime createdFrom;
    private LocalDateTime createdTo;
    private LocalDateTime holidayStart;
    private LocalDateTime holidayEnd;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDateTime getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(LocalDateTime createdFrom) {
        this.createdFrom = createdFrom;
    }

    public LocalDateTime getCreatedTo() {
        return createdTo;
    }

    public void setCreatedTo(LocalDateTime createdTo) {
        this.createdTo = createdTo;
    }

    public LocalDateTime getHolidayStart() {
        return holidayStart;
    }

    public void setHolidayStart(LocalDateTime holidayStart) {
        this.holidayStart = holidayStart;
    }

    public LocalDateTime getHolidayEnd() {
        return holidayEnd;
    }

    public void setHolidayEnd(LocalDateTime holidayEnd) {
        this.holidayEnd = holidayEnd;
    }

}
