package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.model.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	@Query("select p from CustomerPolicy cp JOIN cp.customer c JOIN cp.policy p where c.id=?1 AND p.id=?2")
	Policy getPolicyByCustomerAndPolicyId(int customerId, int policyId);

}
