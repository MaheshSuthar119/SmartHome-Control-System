package com.example.smartdevice.repository;

import com.example.smartdevice.model.DeviceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceLogRepository extends JpaRepository<DeviceLog, Long> {
    List<DeviceLog> findByDeviceIdOrderByTimestampDesc(Long deviceId);
}
