package hu.cubix.hr.borcsi.dto;

import java.time.LocalDateTime;

public class HolidayDto {
    Long id;
    LocalDateTime createdAt;
    EmployeeDto createdBy;

    LocalDateTime startDate;

    LocalDateTime endDate;
    Boolean isAccepted;

    public HolidayDto() {
    }

    public HolidayDto(LocalDateTime createdAt, EmployeeDto createdBy, LocalDateTime start, LocalDateTime end, Boolean isAccepted) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.startDate = start;
        this.endDate = end;
        this.isAccepted = isAccepted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public EmployeeDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(EmployeeDto createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }
}
