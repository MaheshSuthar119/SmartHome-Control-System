package com.example.smartdevice.service;

import com.example.smartdevice.model.*;
import com.example.smartdevice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SmartDeviceService {

    @Autowired
    private SmartDeviceRepository deviceRepo;

    @Autowired
    private DeviceLogRepository logRepo;

    @Autowired
    private ScheduledActionRepository scheduleRepo;

    public SmartDevice createDevice(SmartDevice device) {
        device.setLastUpdated(LocalDateTime.now());
        device.setStatus(device.getStatus() == null ? "OFF" : device.getStatus());
        if (device.getBatteryLevel() == null) device.setBatteryLevel(100);
        return deviceRepo.save(device);
    }

    public List<SmartDevice> getAllDevices() {
        return deviceRepo.findAll();
    }

    public Optional<SmartDevice> getDevice(Long id) {
        return deviceRepo.findById(id);
    }

    public SmartDevice updateDevice(Long id, SmartDevice patch) {
        return deviceRepo.findById(id).map(d -> {
            if (patch.getDeviceName() != null) d.setDeviceName(patch.getDeviceName());
            if (patch.getDeviceType() != null) d.setDeviceType(patch.getDeviceType());
            if (patch.getStatus() != null) d.setStatus(patch.getStatus());
            if (patch.getBatteryLevel() != null) d.setBatteryLevel(patch.getBatteryLevel());
            d.setLastUpdated(LocalDateTime.now());
            return deviceRepo.save(d);
        }).orElseThrow(() -> new RuntimeException("Device not found"));
    }

    public void deleteDevice(Long id) {
        deviceRepo.deleteById(id);
    }

    public DeviceLog logAction(Long deviceId, String action) {
        DeviceLog l = new DeviceLog();
        l.setDeviceId(deviceId);
        l.setAction(action);
        l.setTimestamp(LocalDateTime.now());
        return logRepo.save(l);
    }

    @Transactional
    public SmartDevice changeStatus(Long deviceId, String status) {
        SmartDevice d = deviceRepo.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
        d.setStatus(status);
        d.setLastUpdated(LocalDateTime.now());
        SmartDevice saved = deviceRepo.save(d);
        logAction(deviceId, "Status -> " + status);
        return saved;
    }

    public List<DeviceLog> getLogs(Long deviceId) {
        return logRepo.findByDeviceIdOrderByTimestampDesc(deviceId);
    }

    public ScheduledAction scheduleAction(Long deviceId, String action, LocalDateTime executeAt) {
        ScheduledAction s = new ScheduledAction();
        s.setDeviceId(deviceId);
        s.setAction(action);
        s.setExecuteAt(executeAt);
        s.setExecuted(false);
        return scheduleRepo.save(s);
    }

    // Poll scheduled actions every 20 seconds
    @Scheduled(fixedDelayString = "20000", initialDelay = 5000)
    @Transactional
    public void processScheduledActions() {
        List<ScheduledAction> pending = scheduleRepo.findByExecutedFalseAndExecuteAtLessThanEqual(LocalDateTime.now());
        for (ScheduledAction s : pending) {
            Optional<SmartDevice> od = deviceRepo.findById(s.getDeviceId());
            if (od.isPresent()) {
                SmartDevice d = od.get();
                // perform action simulation (for demonstration we only log)
                logAction(d.getDeviceId(), "ScheduledAction: " + s.getAction());
            } else {
                logAction(s.getDeviceId(), "ScheduledAction attempted but device missing");
            }
            s.setExecuted(true);
            scheduleRepo.save(s);
        }
        // Simulate battery drain for ON devices
        List<SmartDevice> devices = deviceRepo.findByStatus("ON");
        for (SmartDevice d : devices) {
            int newLevel = Math.max(0, d.getBatteryLevel() - 1);
            d.setBatteryLevel(newLevel);
            d.setLastUpdated(LocalDateTime.now());
            deviceRepo.save(d);
            logAction(d.getDeviceId(), "Battery decreased by 1%");
            if (newLevel <= 10) {
                d.setStatus("OFF");
                deviceRepo.save(d);
                logAction(d.getDeviceId(), "Auto-OFF due to low battery");
            }
        }
    }
}
