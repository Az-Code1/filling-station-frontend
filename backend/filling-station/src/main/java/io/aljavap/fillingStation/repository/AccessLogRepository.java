package io.aljavap.fillingStation.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import io.aljavap.fillingStation.entity.AccessLog;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}
