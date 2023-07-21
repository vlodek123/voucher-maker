package cz.tallavla.vouchermaker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(WrongFormatException.class)
	public ResponseEntity<Object> handleWrongAmountFormatException(WrongFormatException ex) {
		ErrorResponse error = new ErrorResponse("1001004", "WRONG FORMAT",ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(WrongVoucherActionException.class)
	public ResponseEntity<Object> handleWrongVoucherActionException(WrongVoucherActionException ex) {
		ErrorResponse error = new ErrorResponse("100103","WRONG FORMAT",ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(VoucherNotFoundException.class)
	public ResponseEntity<Object> handleVoucherNotFoundException(VoucherNotFoundException ex) {
		ErrorResponse error = new ErrorResponse("100105","NOT FOUND", ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(VoucherCodeIsNullException.class)
	public ResponseEntity<Object> handleVoucherCodeIsNullException(VoucherCodeIsNullException ex) {
		ErrorResponse error = new ErrorResponse("100106", "NULL VALUE", ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CaptureException.class)
	public ResponseEntity<Object> handleCaptureException(CaptureException ex) {
		ErrorResponse error = new ErrorResponse("100107", "PROCESSING ERROR", ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CaptureNotFoundException.class)
	public ResponseEntity<Object> handleCaptureNotFoundException(CaptureNotFoundException ex) {
		ErrorResponse error = new ErrorResponse("100102", "NOT FOUND", ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		ErrorResponse error = new ErrorResponse("100100","WRONG FORMAT",ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		ErrorResponse error = new ErrorResponse("100108","WRONG FORMAT",ex.getLocalizedMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
