package io.aljavap.fillingStation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.aljavap.fillingStation.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}