package beans;

import java.time.LocalDateTime;

public class Reimbursement {

	
	public static enum StatusType {
		PENDING, DENIED, APPOVED
	}

	public static enum ReimbursementType {
		LODGING, TRAVEL, FOOD, OTHER
	}

	private int reimbId;
	private double amount;
	private LocalDateTime timestamp;
	private LocalDateTime resolved;
	private String description;
	private String author;
	private int authorId;
	private int resolver;
	private StatusType statusType;
	private int statusTypeId = 1;
	private ReimbursementType reimbType;
	private int reimbTypeId;

	public Reimbursement(double amount, LocalDateTime timestamp, LocalDateTime resolved, String description,
			 String author, StatusType statusType, ReimbursementType reimbType) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.statusType = statusType;
		this.reimbType = reimbType;
	}


	public int getStatusTypeId() {
		return statusTypeId;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}


	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp.now();
	}

	public LocalDateTime getResolved() {
		return resolved;
	}

	public void setResolved(LocalDateTime resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public StatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
		if(statusType.toString() == "PENDING") {
			this.statusTypeId = 1;
		} else if(statusType.toString() == "DENIED") {
			this.statusTypeId = 2;
		} else if(statusType.toString() == "APPROVED") {
			this.statusTypeId = 3;
		}
	}

	public ReimbursementType getReimbType() {
		return reimbType;
	}

	public void setReimbType(ReimbursementType reimbType) {
		this.reimbType = reimbType;
		if(reimbType.toString() == "LODGING") {
			this.reimbTypeId = 1;
		} else if(reimbType.toString() == "TRAVEL") {
			this.reimbTypeId = 2;
		} else if(reimbType.toString() == "FOOD") {
			this.reimbTypeId = 3;
		} else {
			this.reimbTypeId = 4;
		}
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", amount=" + amount + ", timestamp=" + timestamp + ", resolved="
				+ resolved + ", description=" + description + ", author=" + author
				+ ", resolver=" + resolver + ", statusType=" + statusType + ", reimbType=" + reimbType + "]";
	}

}
