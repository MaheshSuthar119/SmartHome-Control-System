package com.example.smartdevice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "smart_devices")
public class SmartDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    @Column(nullable = false, length = 100)
    private String deviceName;

    @Column(nullable = false, length = 50)
    private String deviceType; // e.g., LIGHT, ROBOT, SENSOR

    @Column(nullable = false, length = 20)
    private String status; // ON/OFF

    @Column(nullable = false)
    private Integer batteryLevel;

    private LocalDateTime lastUpdated;

    public SmartDevice() {}

    // getters & setters

    public Long getDeviceId() { return deviceId; }
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(Integer batteryLevel) { this.batteryLevel = batteryLevel; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
