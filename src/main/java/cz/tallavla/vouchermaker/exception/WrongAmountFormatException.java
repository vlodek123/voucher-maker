package cz.tallavla.vouchermaker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongAmountFormatException extends RuntimeException {

	public WrongAmountFormatException(String message) {
		super(message);
	}
}
