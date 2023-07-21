package cz.tallavla.vouchermaker.model.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReturnVoucher {
	private String voucherCode;
	private BigDecimal amount;
	private BigDecimal balance;
	private LocalDateTime expirationDate;
	private boolean active;
	private List<ReturnCaptureItem> captureItems;

	public static ReturnVoucherBuilder builder() {
		return new ReturnVoucherBuilder();
	}

	public ReturnVoucher(String voucherCode, BigDecimal amount, BigDecimal balance, LocalDateTime expirationDate, boolean active, List<ReturnCaptureItem> captureItems) {
		this.voucherCode = voucherCode;
		this.amount = amount;
		this.balance = balance;
		this.expirationDate = expirationDate;
		this.active = active;
		this.captureItems = captureItems;
	}

	public ReturnVoucher() {
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<ReturnCaptureItem> getCaptureItems() {
		return captureItems;
	}

	public void setCaptureItems(List<ReturnCaptureItem> captureItems) {
		this.captureItems = captureItems;
	}


	@Override
	public String toString() {
		return "ReturnVoucher{" +
				"voucherCode='" + voucherCode + '\'' +
				", amount=" + amount +
				", balance=" + balance +
				", expirationDate=" + expirationDate +
				", active=" + active +
				", captureItems=" + captureItems +
				'}';
	}

	public static class ReturnVoucherBuilder {
		private String voucherCode;
		private BigDecimal amount;
		private BigDecimal balance;
		private LocalDateTime expirationDate;
		private boolean active;
		private List<ReturnCaptureItem> captureItems;

		ReturnVoucherBuilder() {
		}

		public ReturnVoucherBuilder voucherCode(final String voucherCode) {
			this.voucherCode = voucherCode;
			return this;
		}

		public ReturnVoucherBuilder amount(final BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public ReturnVoucherBuilder balance(final BigDecimal balance) {
			this.balance = balance;
			return this;
		}

		public ReturnVoucherBuilder expirationDate(final LocalDateTime expirationDate) {
			this.expirationDate = expirationDate;
			return this;
		}

		public ReturnVoucherBuilder active(final boolean active) {
			this.active = active;
			return this;
		}

		public ReturnVoucherBuilder captureItems(final List<ReturnCaptureItem> captureItems) {
			this.captureItems = captureItems;
			return this;
		}

		public ReturnVoucher build() {
			return new ReturnVoucher(this.voucherCode, this.amount, this.balance, this.expirationDate, this.active, this.captureItems);
		}

		public String toString() {
			return "ReturnVoucher.ReturnVoucherBuilder(voucherCode=" + this.voucherCode + ", amount=" + this.amount + ", balance=" + this.balance + ", expirationDate=" + this.expirationDate + ", active=" + this.active + ", captureItems=" + this.captureItems + ")";
		}
	}
}
