package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(String customerId);

	// Customer Id
	@Query("DELETE  from Customer c WHERE c.customerId=?1")
	int deleteCustomerByCustomerId(String customer_id);

	// using Customer Id
	@Query("UPDATE Customer c SET firstName=?1 WHERE c.customerId=?2")
	int updateCustomerByCustomerId(String firstName, String customer_id);

	// whose Email contains 'gmail'
	@Query("SELECT c FROM Customer c WHERE c.email LIKE %:gmail%")
	List<Customer> findAllCustomersWhoseEmailContainsGmail(@Param("gmail") String filter);
}
