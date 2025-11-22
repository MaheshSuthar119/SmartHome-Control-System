package com.example.smartdevice.repository;

import com.example.smartdevice.model.SmartDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartDeviceRepository extends JpaRepository<SmartDevice, Long> {
    List<SmartDevice> findByDeviceType(String deviceType);
    List<SmartDevice> findByStatus(String status);
}
