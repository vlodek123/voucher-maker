package cz.tallavla.vouchermaker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(WrongFormatException.class)
	public ResponseEntity<Object> handleWrongAmountFormatException(WrongFormatException ex) {
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

	@ExceptionHandler(VoucherCodeIsNullException.class)
	public ResponseEntity<Object> handleVoucherCodeIsNullException(VoucherCodeIsNullException ex) {
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CaptureException.class)
	public ResponseEntity<Object> handleCaptureException(CaptureException ex) {
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CaptureNotFoundException.class)
	public ResponseEntity<Object> handleCaptureNotFoundException(CaptureNotFoundException ex) {
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
