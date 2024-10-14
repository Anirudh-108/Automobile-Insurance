package com.automobile.repository;

import java.util.List;

<<<<<<< HEAD
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Repository;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

import com.automobile.enums.ClaimStatus;
import com.automobile.enums.PolicyStatus;
import com.automobile.model.ClaimPolicy;
<<<<<<< HEAD
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Executive;
import com.automobile.model.Policy;

=======
import com.automobile.model.Executive;
import com.automobile.model.Policy;

@Repository
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
public interface ClaimPolicyRepository extends JpaRepository<ClaimPolicy, Integer> {

	@Query("select p from CustomerPolicy cp JOIN cp.policy p JOIN cp.customer c where c.id=?1 and p.policyStatus=?2")
	List<Policy> findPolicyByStatus(int customerId, PolicyStatus active);

<<<<<<< HEAD
	@Query("select clmp from ClaimPolicy clmp JOIN clmp.customerPolicy cp where cp.id=?1")
	ClaimPolicy getStatusByCustomerPolicyId(int customerPolicyId);

	//Executive
	
=======
	// @Query((value = "select clmp from claim_policy clmp where clmp.claim_status =
	// '?' ", nativeQuery = true)
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
	@Query("select clmp from ClaimPolicy clmp where clmp.claimStatus = :claimStatus")
	List<ClaimPolicy> findByclaimStatus(ClaimStatus claimStatus);

	@Modifying
<<<<<<< HEAD
	@Query("update ClaimPolicy clmp SET clmp.claimStatus = :claimStatus, clmp.executive = :executive WHERE clmp.id = :claimpolicyId")
	ClaimPolicy updateClaimStatus(@Param("claimpolicyId") int claimpolicyId, @Param("claimStatus") ClaimStatus claimStatus, @Param("executive") Executive executive);


	List<ClaimPolicy> findByExecutive(Executive executive);
	
	
=======
	// @Query(value = "update claim_policy clmp set clmp.claimStatus = ?,
	// clmp.executive= ? where clmp.id = ?", nativeQuery = true)
	@Query("UPDATE ClaimPolicy cp SET cp.claimStatus = :claimStatus, cp.executive = :executive WHERE cp.id = :claimpolicyId")
	ClaimPolicy updateClaimStatus(@Param("claimpolicyId") int claimpolicyId,
			@Param("claimStatus") ClaimStatus claimStatus, @Param("executive") Executive executive);

	@Query("select cp from ClaimPolicy cp where cp.customer.id=?1")
	Page<ClaimPolicy> getAllClaims(int customerId, Pageable pageable);

	@Query("select COUNT(cp) from ClaimPolicy cp where cp.customer.id=?1")
	long getNumberOfActivePolicies(int customerId);

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}
