package com.automobile.model;

import java.time.LocalDate;

import com.automobile.enums.ClaimStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ExecutiveClaim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Executive executive;
	@ManyToOne
	private ClaimPolicy claimPolicy;
	
	private LocalDate updateDate;
    
	@Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Executive getExecutive() {
		return executive;
	}

	public void setExecutive(Executive executive) {
		this.executive = executive;
	}

	public ClaimPolicy getClaimPolicy() {
		return claimPolicy;
	}

	public void setClaimPolicy(ClaimPolicy claimPolicy) {
		this.claimPolicy = claimPolicy;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	

	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
	}

	@Override
	public String toString() {
		return "ExecutiveClaim [id=" + id + ", executive=" + executive + ", claimPolicy=" + claimPolicy
				+ ", updateDate=" + updateDate + ", claimStatus=" + claimStatus + "]";
	}
}
