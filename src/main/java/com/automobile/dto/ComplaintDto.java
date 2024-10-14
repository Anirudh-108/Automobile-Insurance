package com.automobile.dto;

public class ComplaintDto {
	
	private int complaintId;
	private String complaintType;
	private String complaintStatus;
	private String description;
	private String policyType;
	private String vehcleName;
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public String getComplaintStatus() {
		return complaintStatus;
	}
	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getVehcleName() {
		return vehcleName;
	}
	public void setVehcleName(String vehcleName) {
		this.vehcleName = vehcleName;
	}
	@Override
	public String toString() {
		return "ComplaintDto [complaintId=" + complaintId + ", complaintType=" + complaintType + ", complaintStatus="
				+ complaintStatus + ", description=" + description + ", policyType=" + policyType + ", vehcleName="
				+ vehcleName + "]";
	}
	
	

}
