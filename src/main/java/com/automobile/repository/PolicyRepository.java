package com.automobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD

import com.automobile.model.Policy;

=======
import org.springframework.stereotype.Repository;

import com.automobile.model.Policy;
import com.automobile.model.Vehicle;

@Repository
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	@Query("select p from CustomerPolicy cp JOIN cp.customer c JOIN cp.policy p where c.id=?1 AND p.id=?2")
	Policy getPolicyByCustomerAndPolicyId(int customerId, int policyId);

<<<<<<< HEAD
=======
	@Query("select COUNT(cp) from CustomerPolicy cp JOIN cp.policy p where cp.customer.id=?1 AND p.policyStatus='Active'")
	long getNumberOfActivePolicies(int customerId);

	@Query("select COUNT(cp) from CustomerPolicy cp JOIN cp.policy p where cp.customer.id=?1 AND p.policyStatus='Expired'")
	long getNumberOfExpiredPolicies(int customerId);

	@Query("select p.vehicle from Policy p where p.id=?1")
	Vehicle getVehicleByPolicyId(int policyId);

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}
