package com.automobile.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.automobile.enums.PolicyRequestStatus;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Executive;

public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Integer> {

	@Query("select cp from CustomerPolicy cp JOIN cp.policy p JOIN cp.customer c where p.id=?1 AND c.user.username=?2")
	Optional<CustomerPolicy> getCustomerPolicyByPolicyId(int policyId, String customerUsername);

	List<CustomerPolicy> findByCustomerId(int customerId);

	

	// Executive module query
	@Modifying
	// @Query("update customer_policy cp set cp.policy_request_status = ? ,
	// cp.updated_by = ?, where cp.id = ?", nativeQuery = true)
	@Query("UPDATE CustomerPolicy cp SET cp.policyRequestStatus = :policyRequestStatus, cp.updatedBy = :executive WHERE cp.id = :policyId")
	CustomerPolicy updatePolicyRequestStatus(@Param("policyId") int policyId,
			@Param("policyRequestStatus") PolicyRequestStatus policyRequestStatus,
			@Param("executive") Executive executive);

	@Query("select cp from CustomerPolicy cp JOIN cp.policy p where cp.customer.id=?1 AND p.policyStatus='Active'")
	List<CustomerPolicy> getAllActiveCustomerPolicies(int custId);

	@Query("select cp from CustomerPolicy cp JOIN cp.policy p where cp.customer.id=?1 AND p.policyStatus='Expired'")
	List<CustomerPolicy> getAllExpiredPolicies(int customerId);
	
	@Query("select cp from CustomerPolicy cp JOIN cp.policy p where cp.customer.id=?1 AND p.id=?2")
	CustomerPolicy getActivePolicyByPolicyId(int custId, int policyId);

	
}
