package com.automobile.service;

import org.springframework.stereotype.Service;

import com.automobile.model.Claim;



@Service
public class ClaimService {

    // Deductible calculation based on engine CC
    private int getDeductible(int engineCC) {
        if (engineCC < 1500) {
            return 1500;
        } else {
            return 2000;
        }
    }

    // Depreciation calculation based on vehicle age and zero depreciation add-on
    
    private double getDepreciation(int vehicleAge, YesOrNo zeroDepreciation) {
        if (zeroDepreciation == YesOrNo.NO) {
           
            if (vehicleAge < 0.5) {  // Less than 6 months
                return 0.05;
            } else if (vehicleAge < 1) {  // 6 months to 1 year
                return 0.15;
            } else if (vehicleAge < 2) {  // 1 to 2 years
                return 0.20;
            } else if (vehicleAge < 3) {  // 2 to 3 years
                return 0.30;
            } else if (vehicleAge < 4) {  // 3 to 4 years
                return 0.40;
            } else if (vehicleAge < 5) {  // 4 to 5 years
                return 0.50;
            } else {  // More than 5 years
                return 0.60;
            }
        } else {
            return 0;  
        }
    }

    // Final claim amount calculation
    public double calculateClaimAmount(Claim claim) {
    	
        int currentYear = 2024; 
        int vehicleAge = currentYear - claim.getManufacturedYear();
        
        double deductible = getDeductible(claim.getEngineCC());
        double depreciation = getDepreciation(vehicleAge, claim.getZeroDepreciation());

        double claimAmount = (claim.getDamageCostEstimate() - deductible) * (1 - depreciation);

        
        return claimAmount;
    }
}
