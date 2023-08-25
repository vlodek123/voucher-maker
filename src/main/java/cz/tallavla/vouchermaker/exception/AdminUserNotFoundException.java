package cz.tallavla.vouchermaker.exception;

public class AdminUserNotFoundException extends RuntimeException {

		public AdminUserNotFoundException() {
		super("Admin user not found in the database.");
	}
}
