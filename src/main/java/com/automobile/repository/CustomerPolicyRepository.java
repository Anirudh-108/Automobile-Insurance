package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.CustomerPolicy;

public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Integer> {

	@Query("select cp from CustomerPolicy cp JOIN cp.policy p where p.id=?1")
	CustomerPolicy getCustomerPolicyByPolicyId(int policyId);

}
