package com.example.smartdevice.dto;

import jakarta.validation.constraints.*;

public class DeviceDTO {
    @NotBlank
    private String deviceName;

    @NotBlank
    private String deviceType;

    @Min(0) @Max(100)
    private Integer batteryLevel;

    // getters & setters
    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }
    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    public Integer getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(Integer batteryLevel) { this.batteryLevel = batteryLevel; }
}
