package io.aljavap.fillingStation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.aljavap.fillingStation.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	public Optional<Customer> findByCustomerPhoneNumber(String customerPhoneNumber);
}
