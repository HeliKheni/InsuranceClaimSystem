package mvc.model;

public class User {
	private String username;
    private String password;
    private String cellphoneNo;
    private String email;
    private String address;
    
    public User() {}
    
	public User(String username, String password, String cellphoneNo, String email, String address) {
		super();
		this.username = username;
		this.password = password;
		this.cellphoneNo = cellphoneNo;
		this.email = email;
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellphoneNo() {
		return cellphoneNo;
	}

	public void setCellphoneNo(String cellphoneNo) {
		this.cellphoneNo = cellphoneNo;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
    

}
