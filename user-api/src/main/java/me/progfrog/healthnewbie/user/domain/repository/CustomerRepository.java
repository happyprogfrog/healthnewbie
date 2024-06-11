package me.progfrog.healthnewbie.user.domain.repository;

import me.progfrog.healthnewbie.user.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
