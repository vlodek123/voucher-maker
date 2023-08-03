package cz.tallavla.vouchermaker.exception;

import org.springframework.http.HttpStatus;

public class VoucherMakerAPIException extends RuntimeException {


	private HttpStatus status;
	private String message;

	public VoucherMakerAPIException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public VoucherMakerAPIException(String message, HttpStatus status, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
