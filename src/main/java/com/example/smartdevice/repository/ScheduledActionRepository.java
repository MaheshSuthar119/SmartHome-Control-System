package com.example.smartdevice.repository;

import com.example.smartdevice.model.ScheduledAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduledActionRepository extends JpaRepository<ScheduledAction, Long> {
    List<ScheduledAction> findByExecutedFalseAndExecuteAtLessThanEqual(LocalDateTime time);
}
