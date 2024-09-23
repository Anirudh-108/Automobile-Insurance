package com.jwt.securirty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.automobile.service.ClaimPolicyService;

@SpringBootTest
public class ClaimPolicyServiceTest {

	@Autowired
	private ClaimPolicyService claimPolicyService;

	@Test
	public void getAllActivePolicyTest() {
//		List<Policy> mockList = Arrays.asList("A","B","C","D","E","F","I");

//		when(mainService.getProductTitle()).thenReturn(mockList);

		int exceptedNum = 2;
		int actualNum = claimPolicyService.getAllActivePolicy("harry@gmail.com").size();
		assertEquals(exceptedNum, actualNum);
	}
}
