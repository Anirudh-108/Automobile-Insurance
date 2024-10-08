package com.jwt.securirty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.automobile.enums.PolicyStatus;
import com.automobile.enums.PolicyType;
import com.automobile.model.Policy;
import com.automobile.repository.ClaimPolicyRepository;
import com.automobile.service.ClaimPolicyService;

@SpringBootTest(classes = ClaimPolicyServiceTest.class)
public class ClaimPolicyServiceTest {

	@InjectMocks
	private ClaimPolicyService claimPolicyService;

	@Mock
	private ClaimPolicyRepository claimPolicyRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getAllActivePolicyTest() {
		Policy p1 = new Policy(1, PolicyType.Comprehensive, 960000, 70200, 12, PolicyStatus.Active);
		Policy p2 = new Policy(2, PolicyType.ThirdParty, 47500, 3562.5, 12, PolicyStatus.Active);
		List<Policy> mockList = Arrays.asList(p1, p2);

		when(claimPolicyService.getAllActivePolicy("harry@gmail.com")).thenReturn(mockList);

//		int exceptedNum = 2;
//		int actualNum = claimPolicyService.getAllActivePolicy("harry@gmail.com").size();
//		assertEquals(exceptedNum, actualNum);
	}
}
