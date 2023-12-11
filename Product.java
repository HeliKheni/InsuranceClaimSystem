package mvc.model;

import java.util.Date;

public class Product {
	 	private String productSerialNo;
	    private String productName;
	    private String model;
	    private double price;
	    private String manufacturer;
	    private String description;
	    private String color;
	    private String username; // UserProduct related field
	    private Date purchasedDate; // UserProduct related field
	    
	    public Product() {
	        // Default constructor
	    }
	    
		public Product(String productSerialNo, String productName, String model, double price, String manufacturer,
				String description, String color) {
			super();
			this.productSerialNo = productSerialNo;
			this.productName = productName;
			this.model = model;
			this.price = price;
			this.manufacturer = manufacturer;
			this.description = description;
			this.color = color;
		}
		
		public Product(String productSerialNo, String productName, String model, double price,
                String manufacturer, String description, String color,
                String username, Date purchasedDate) {
			     this.productSerialNo = productSerialNo;
			     this.productName = productName;
			     this.model = model;
			     this.price = price;
			     this.manufacturer = manufacturer;
			     this.description = description;
			     this.color = color;
			     this.setUsername(username);
			     this.setPurchasedDate(purchasedDate);
		}
		
		 public Product(String productName) {
		        this.productName = productName;
		    }
		 
	
		public String getProductSerialNo() {
			return productSerialNo;
		}

		public void setProductSerialNo(String productSerialNo) {
			this.productSerialNo = productSerialNo;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Date getPurchasedDate() {
			return purchasedDate;
		}

		public void setPurchasedDate(Date purchasedDate) {
			this.purchasedDate = purchasedDate;
		}
	    
	    

}
