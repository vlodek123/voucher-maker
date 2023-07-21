package cz.tallavla.vouchermaker.exception;

public class ErrorResponse {

	private String error;
	private String type;
	private String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

	public ErrorResponse(String error, String type, String message) {
		this.error = error;
		this.type = type;
		this.message = message;
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
}
