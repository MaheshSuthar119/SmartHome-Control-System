package com.example.smartdevice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scheduled_actions")
public class ScheduledAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(nullable = false)
    private Long deviceId;

    @Column(nullable = false, length = 500)
    private String action;

    @Column(nullable = false)
    private LocalDateTime executeAt;

    @Column(nullable = false)
    private Boolean executed = false;

    public ScheduledAction() {}

    // getters & setters
    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public LocalDateTime getExecuteAt() { return executeAt; }
    public void setExecuteAt(LocalDateTime executeAt) { this.executeAt = executeAt; }

    public Boolean getExecuted() { return executed; }
    public void setExecuted(Boolean executed) { this.executed = executed; }
}
