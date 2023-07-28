package cz.tallavla.vouchermaker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CaptureException extends RuntimeException {

	private Long id;

	public CaptureException(String message, Long id) {
		super(message);
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
