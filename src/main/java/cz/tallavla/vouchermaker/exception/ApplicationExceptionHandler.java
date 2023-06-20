package cz.tallavla.vouchermaker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(WrongAmountFormatException.class)
	public ResponseEntity<Object> handleWrongAmountFormatException(WrongAmountFormatException ex) {
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(WrongVoucherActionException.class)
	public ResponseEntity<Object> handleWrongVoucherActionException(WrongVoucherActionException ex) {
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(VoucherNotFoundException.class)
	public ResponseEntity<Object> handleVoucherNotFoundException(VoucherNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
