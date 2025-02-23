package io.aljavap.fillingStation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.aljavap.fillingStation.entity.Guarantor;

public interface GuarantorRepository extends JpaRepository<Guarantor, Long> {
}
