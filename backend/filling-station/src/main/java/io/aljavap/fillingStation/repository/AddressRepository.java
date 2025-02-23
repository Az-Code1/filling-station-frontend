package io.aljavap.fillingStation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.aljavap.fillingStation.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
