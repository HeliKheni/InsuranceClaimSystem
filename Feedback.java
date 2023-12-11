package mvc.model;

import java.sql.Date;

public class Feedback {
    private String username;
    private String product_serial_no;
    private String productName;
    private String comment;
    private int rating;
    private Date submissionDate;  // New field for submission date

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

  
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public java.sql.Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Feedback(String username, String product_serial_no, String comment, int rating, Date submissionDate) {
        super();
        this.username = username;
        this.setProduct_serial_no(product_serial_no);
        this.comment = comment;
        this.rating = rating;
        this.submissionDate = submissionDate;
    }

    public Feedback(String username2, String productSerialNo, String productSerialNo2, int rating2) {
        // TODO Auto-generated constructor stub
    }

	public Feedback() {
		// TODO Auto-generated constructor stub
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProduct_serial_no() {
		return product_serial_no;
	}

	public void setProduct_serial_no(String product_serial_no) {
		this.product_serial_no = product_serial_no;
	}
}
