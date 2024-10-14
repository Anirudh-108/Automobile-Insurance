package com.automobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.automobile.enums.ClaimStatus;
import com.automobile.enums.PolicyStatus;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Executive;
import com.automobile.model.Policy;

public interface ClaimPolicyRepository extends JpaRepository<ClaimPolicy, Integer> {

	@Query("select p from CustomerPolicy cp JOIN cp.policy p JOIN cp.customer c where c.id=?1 and p.policyStatus=?2")
	List<Policy> findPolicyByStatus(int customerId, PolicyStatus active);

	@Query("select clmp from ClaimPolicy clmp JOIN clmp.customerPolicy cp where cp.id=?1")
	ClaimPolicy getStatusByCustomerPolicyId(int customerPolicyId);

	//Executive
	
	@Query("select clmp from ClaimPolicy clmp where clmp.claimStatus = :claimStatus")
	List<ClaimPolicy> findByclaimStatus(ClaimStatus claimStatus);

	@Modifying
	@Query("update ClaimPolicy clmp SET clmp.claimStatus = :claimStatus, clmp.executive = :executive WHERE clmp.id = :claimpolicyId")
	ClaimPolicy updateClaimStatus(@Param("claimpolicyId") int claimpolicyId, @Param("claimStatus") ClaimStatus claimStatus, @Param("executive") Executive executive);


	List<ClaimPolicy> findByExecutive(Executive executive);
	
	
}
