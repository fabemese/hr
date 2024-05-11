package hu.cubix.hr.borcsi.repository;

import hu.cubix.hr.borcsi.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HolidayRepository extends JpaRepository<Holiday, Long>, JpaSpecificationExecutor<Holiday> {
    @Modifying
    @Query("UPDATE Holiday h SET h.isAccepted = :isAccepted WHERE h.id= :id")
    int judgeRequest(Long id, Boolean isAccepted);
}
