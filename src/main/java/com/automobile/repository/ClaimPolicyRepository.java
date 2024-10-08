package com.automobile.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.automobile.enums.ClaimStatus;
import com.automobile.enums.PolicyStatus;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.Executive;
import com.automobile.model.Policy;

@Repository
public interface ClaimPolicyRepository extends JpaRepository<ClaimPolicy, Integer> {

	@Query("select p from CustomerPolicy cp JOIN cp.policy p JOIN cp.customer c where c.id=?1 and p.policyStatus=?2")
	List<Policy> findPolicyByStatus(int customerId, PolicyStatus active);

	@Query("select clmp from ClaimPolicy clmp JOIN clmp.customerPolicy cp where cp.id=?1")
	ClaimPolicy getStatusByCustomerPolicyId(int customerPolicyId);

	//@Query((value = "select clmp from claim_policy clmp where clmp.claim_status  = '?' ", nativeQuery = true)
	@Query("select clmp from ClaimPolicy clmp where clmp.claimStatus = :claimStatus")
	List<ClaimPolicy> findByclaimStatus(ClaimStatus claimStatus);

	@Modifying
	//@Query(value = "update claim_policy clmp set clmp.claimStatus = ?, clmp.executive= ? where clmp.id = ?", nativeQuery = true)
	@Query("UPDATE ClaimPolicy cp SET cp.claimStatus = :claimStatus, cp.executive = :executive WHERE cp.id = :claimpolicyId")
	ClaimPolicy updateClaimStatus(@Param("claimpolicyId") int claimpolicyId, @Param("claimStatus") ClaimStatus claimStatus, @Param("executive") Executive executive);
	
}
