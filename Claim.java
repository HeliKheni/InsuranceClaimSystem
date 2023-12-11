package mvc.model;

import java.sql.Date;

public class Claim {
	private int claimId;
    private int userProductId;
    private Date dateOfClaim;
    private String descriptionOfIncident;
    private String approvalStatus;  
    private String username;
    private String productName;
    private Date purchasedDate;
    
	public Claim(int claimId, int userProductId, Date dateOfClaim, String descriptionOfIncident,
			String approvalStatus) {
		super();
		this.claimId = claimId;
		this.userProductId = userProductId;
		this.dateOfClaim = dateOfClaim;
		this.descriptionOfIncident = descriptionOfIncident;
		this.approvalStatus = approvalStatus;
	}
	
	 public Claim(String productName, String username, Date purchasedDate,
             Date dateOfClaim, String descriptionOfIncident, String approvalStatus, int claimId) {
			this.productName = productName;
			this.username = username;
			this.purchasedDate = purchasedDate;
			this.dateOfClaim = dateOfClaim;
			this.descriptionOfIncident = descriptionOfIncident;
			this.approvalStatus = approvalStatus;
			this.claimId = claimId;
	 }
	
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public int getUserProductId() {
		return userProductId;
	}
	public void setUserProductId(int userProductId) {
		this.userProductId = userProductId;
	}
	public Date getDateOfClaim() {
		return dateOfClaim;
	}
	public void setDateOfClaim(Date dateOfClaim) {
		this.dateOfClaim = dateOfClaim;
	}
	public String getDescriptionOfIncident() {
		return descriptionOfIncident;
	}
	public void setDescriptionOfIncident(String descriptionOfIncident) {
		this.descriptionOfIncident = descriptionOfIncident;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getPurchasedDate() {
		return purchasedDate;
	}
	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}  
}
