package com.automobile.repository;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;
=======
import java.util.List;
import java.util.Optional;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

<<<<<<< HEAD
import com.automobile.dto.CustomerPolicyDto;
=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import com.automobile.enums.PolicyRequestStatus;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Executive;

public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Integer> {

<<<<<<< HEAD
	@Query("select cp from CustomerPolicy cp JOIN cp.policy p JOIN cp.customer c where p.id=?1 AND c.userInfo.username=?2")
	CustomerPolicy getCustomerPolicyByPolicyId(int policyId, String customerUsername);

	//Executive
	
	
	List<CustomerPolicy> findByCustomerId(int customerId);
	
	List<CustomerPolicy> findByPolicyRequestStatus(PolicyRequestStatus policyRequestStatus);
	
	CustomerPolicy findByPolicyId(int policyId);
	
	@Modifying
	@Query("UPDATE CustomerPolicy cp SET cp.policyRequestStatus = :policyRequestStatus, cp.executive = :executive WHERE cp.id = :policyId")
    CustomerPolicy updatePolicyRequestStatus(@Param("policyId") int policyId, @Param("policyRequestStatus") PolicyRequestStatus policyRequestStatus, @Param("executive") Executive executive);
	
	
	//Admin
	int countByExecutive_IdAndPolicyRequestStatus(int executiveId, PolicyRequestStatus status);
	
	@Query("SELECT cp FROM CustomerPolicy cp WHERE cp.executive.id = :executiveId")
	 List<CustomerPolicy> getPoliciesHandledByExecutive(@Param("executiveId") int executiveId);
	
	@Query("SELECT cp FROM CustomerPolicy cp WHERE cp.executive.id = :executiveId AND cp.buyingDate BETWEEN :startDate AND :endDate")
    List<CustomerPolicy> findPoliciesByExecutiveAndDateRange(int executiveId, LocalDate startDate, LocalDate endDate);
	
	
	/*@Query(value = "SELECT "
	        + "    e.id, "
	        + "    e.name, "
	        + "    (SELECT COUNT(*) FROM Customer_Policy cp WHERE cp.executive_id = e.id AND cp.policy_Request_Status = 'Approved') AS approved, "
	        + "    (SELECT COUNT(*) FROM Customer_Policy cp WHERE cp.executive_id = e.id AND cp.policy_Request_Status = 'Cancelled') AS cancelled, "
	        + "    (SELECT COUNT(*) FROM Claim_Policy clp WHERE clp.executive_id = e.id AND clp.claim_Status = 'Denied') AS denied, "
	        + "    (SELECT COUNT(*) FROM Claim_Policy clp WHERE clp.executive_id = e.id AND clp.claim_Status = 'Approved') AS approved_claims "
	        + "FROM "
	        + "    executive e "
	        + "WHERE "
	        + "    e.id = ?1", nativeQuery = true)
	List<Object[]> getExecutivePerformanceWithClaims(int executiveId);*/
	
	/*@Query(value = "SELECT "
	                 + " e.id, "
	                 + " e.name, "
	                 + " (SELECT COUNT(*) FROM customer_policy cp WHERE cp.executive_id = e.id AND cp.policy_request_status = 'Approved') AS approved_policies, "
	                 + " (SELECT COUNT(*) FROM customer_policy cp WHERE cp.executive_id = e.id AND cp.policy_request_status = 'Cancelled') AS cancelled_policies, "
	                 + " (SELECT COUNT(*) FROM claim_policy clp WHERE clp.executive_id = e.id AND clp.claim_status = 'Denied') AS denied_claims, "
	                 + " (SELECT COUNT(*) FROM claim_policy clp WHERE clp.executive_id = e.id AND clp.claim_status = 'Approved') AS approved_claims "
	                 + "FROM executive e", nativeQuery = true)
	    List<Object[]> getAllExecutivesPerformanceWithClaims();
*/

	
	
	  
	/*
	    @Query("SELECT new com.automobile.dto.ExecutivePerformanceDto("
	            + "e.id, e.name, "
	            + "(SELECT COUNT(cp) FROM CustomerPolicy cp WHERE cp.executive.id = e.id AND cp.policyRequestStatus = 'Approved'), "
	            + "(SELECT COUNT(cp) FROM CustomerPolicy cp WHERE cp.executive.id = e.id AND cp.policyRequestStatus = 'Cancelled'), "
	            + "(SELECT COUNT(cp) FROM CustomerPolicy cp WHERE cp.executive.id = e.id AND cp.policyRequestStatus = 'Requested'), " // For Pending
	            + "(SELECT COUNT(clp) FROM ClaimPolicy clp WHERE clp.executive.id = e.id AND clp.claimStatus = 'Approved'), "
	            + "(SELECT COUNT(clp) FROM ClaimPolicy clp WHERE clp.executive.id = e.id AND clp.claimStatus = 'Denied'), "
	            + "(SELECT COUNT(clp) FROM ClaimPolicy clp WHERE clp.executive.id = e.id AND clp.claimStatus = 'Pending') " // For Pending Claims
	            + ") "
	            + "FROM Executive e "
	            + "WHERE e.id = ?1")
	    ExecutivePerformanceDto getExecutivePerformanceWithClaims(int executiveId);
	    */
	    
	    List<CustomerPolicy> findByExecutive(Executive executive);
	    @Query(value = "SELECT "
	            + "    (SELECT COUNT(*) FROM policy) AS total_policies, "
	            + "    (SELECT MAX(policy_type) FROM policy) AS most_common_policy_type, "
	            + "    (SELECT COUNT(*) FROM customer) AS total_customers, "
	            + "    (SELECT COUNT(*) FROM executive) AS total_executives, "
	            + "    (SELECT MAX(premium_amount) FROM policy) AS highest_premium, "
	            + "    (SELECT cp.customer_id FROM customer_policy cp "
	            + "     JOIN policy p ON cp.policy_id = p.id "
	            + "     WHERE cp.policy_request_status = 'Approved' "
	            + "     GROUP BY cp.customer_id "
	            + "     HAVING MAX(p.premium_amount) = (SELECT MAX(premium_amount) FROM policy)) AS highest_premium_customer_id "
	            + "FROM dual", nativeQuery = true)
	    List<Object[]> generateReport();
	    //List<ReportDto> generateReport();


	    
	   /* @Query("SELECT new com.example.dto.ReportDto("
	            + "(SELECT COUNT(p) FROM Policy p), "
	            + "(SELECT p.policyType FROM Policy p GROUP BY p.policyType ORDER BY COUNT(p) DESC LIMIT 1), "
	            + "(SELECT COUNT(c) FROM Customer c), "
	            + "(SELECT COUNT(e) FROM Executive e), "
	            + "(SELECT MAX(p.premiumAmount) FROM Policy p), "
	            + "(SELECT cp.customerId FROM CustomerPolicy cp "
	            + "JOIN cp.policy p WHERE cp.policyRequestStatus = 'Approved' "
	            + "GROUP BY cp.customerId "
	            + "HAVING MAX(p.premiumAmount) = (SELECT MAX(premiumAmount) FROM Policy)) "
	            + ") AS highestPremiumCustomerId) "
	            + "FROM CustomerPolicy cp")
	    ReportDto generateCombinedReport();

*/



	


=======
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

	
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}
