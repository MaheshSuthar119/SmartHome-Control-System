package com.example.smartdevice.controller;

import com.example.smartdevice.dto.*;
import com.example.smartdevice.model.*;
import com.example.smartdevice.service.SmartDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
@Validated
public class SmartDeviceController {

    @Autowired
    private SmartDeviceService service;

    @PostMapping
    public ResponseEntity<SmartDevice> createDevice(@RequestBody DeviceDTO dto) {
        SmartDevice d = new SmartDevice();
        d.setDeviceName(dto.getDeviceName());
        d.setDeviceType(dto.getDeviceType());
        d.setBatteryLevel(dto.getBatteryLevel() == null ? 100 : dto.getBatteryLevel());
        d.setStatus("OFF");
        SmartDevice saved = service.createDevice(d);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<SmartDevice> getAll() {
        return service.getAllDevices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartDevice> getById(@PathVariable Long id) {
        return service.getDevice(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SmartDevice> update(@PathVariable Long id, @RequestBody SmartDevice patch) {
        try {
            return ResponseEntity.ok(service.updateDevice(id, patch));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<SmartDevice> changeStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            SmartDevice updated = service.changeStatus(id, status.toUpperCase());
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/action")
    public ResponseEntity<Void> performAction(@PathVariable Long id, @RequestParam String action) {
        service.logAction(id, "Action: " + action);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/logs")
    public List<DeviceLog> getLogs(@PathVariable Long id) {
        return service.getLogs(id);
    }

    @PostMapping("/schedule")
    public ResponseEntity<ScheduledAction> schedule(@RequestBody ScheduleDTO dto) {
        ScheduledAction s = service.scheduleAction(dto.getDeviceId(), dto.getAction(), dto.getExecuteAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }
}
