package mvc.model;

import java.util.List;

public class userProduct {
	private String username;
    private String productName; 
    private String productSerialNo;
    private String purchasedDate;
    private int UserProductId;

    public userProduct(String username, String productName, String productSerialNo, String purchasedDate) {
        this.username = username;
        this.productName = productName;
        this.productSerialNo = productSerialNo;
        this.purchasedDate = purchasedDate;
    }

    public int getUserProductId() {
		return UserProductId;
	}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getproductName() {
			return productName;
		}

		public void setproductName(String productName) {
			this.productName = productName;
		}

		public String getProductSerialNo() {
			return productSerialNo;
		}

		public void setProductSerialNo(String productSerialNo) {
			this.productSerialNo = productSerialNo;
		}

		public String getPurchasedDate() {
			return purchasedDate;
		}

		public void setPurchasedDate(String purchasedDate) {
			this.purchasedDate = purchasedDate;
		}
	    
		// New property to hold a list of claims
	    private List<Claim> claims;

	    public List<Claim> getClaims() {
	        return claims;
	    }

	    public void setClaims(List<Claim> claims) {
	        this.claims = claims;
	    }
	
}
