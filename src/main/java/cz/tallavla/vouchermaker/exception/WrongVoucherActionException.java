package cz.tallavla.vouchermaker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongVoucherActionException extends RuntimeException {

	public WrongVoucherActionException(String message) {
		super(message);
	}
}
