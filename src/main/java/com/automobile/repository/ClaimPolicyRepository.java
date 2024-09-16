package com.automobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.automobile.enums.PolicyStatus;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.Policy;

public interface ClaimPolicyRepository extends JpaRepository<ClaimPolicy, Integer> {

	@Query("select p from CustomerPolicy cp JOIN cp.policy p JOIN cp.customer c where c.id=?1 and p.policyStatus=?2")
	List<Policy> findPolicyByStatus(int customerId, PolicyStatus active);

}
