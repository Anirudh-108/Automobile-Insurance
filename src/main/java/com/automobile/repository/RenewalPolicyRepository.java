package com.automobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.automobile.enums.PolicyStatus;
import com.automobile.model.Policy;

@Repository
public interface RenewalPolicyRepository extends JpaRepository<Policy, Integer> {

	@Query("select p from CustomerPolicy cp JOIN cp.policy p JOIN cp.customer c where c.id=?1 and p.policyStatus=?2")
	List<Policy> findPolicyByStatus(int customerId, PolicyStatus expired);

	// @Query("update p SET p.policy_status=?1 JOIN CustomerPolicy cp.policy p JOIN cp.customer c where c.customer_id=?2 AND p.id=?3")
	//Policy renewPolicy(PolicyStatus active, int customerId, int policyId);

}
