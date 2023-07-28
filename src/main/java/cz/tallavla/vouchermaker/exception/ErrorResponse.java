package cz.tallavla.vouchermaker.exception;

public class ErrorResponse {

	private String error;
	private String type;
	private String message;

	private Long id;

	public ErrorResponse(String message) {
		this.message = message;
	}

	public ErrorResponse(String error, String type, String message) {
		this.error = error;
		this.type = type;
		this.message = message;

	}

	public ErrorResponse(String error, String type, String message, Long id) {
		this.error = error;
		this.type = type;
		this.message = message;
		this.id = id;
	}

	public ErrorResponse() {
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
