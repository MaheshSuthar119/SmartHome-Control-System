package com.example.smartdevice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ScheduleDTO {
    @NotNull
    private Long deviceId;

    @NotBlank
    private String action;

    @NotNull
    private LocalDateTime executeAt;

    // getters & setters
    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public LocalDateTime getExecuteAt() { return executeAt; }
    public void setExecuteAt(LocalDateTime executeAt) { this.executeAt = executeAt; }
}
