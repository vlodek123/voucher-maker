package cz.tallavla.vouchermaker.model.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ReturnVoucher {

	private Long id;
	private String voucherCode;
	private BigDecimal amount;
	private BigDecimal balance;
	private LocalDateTime expirationDate;
	private boolean active;
	private List<ReturnCaptureItem> captureItemList;

	public static ReturnVoucherBuilder builder() {
		return new ReturnVoucherBuilder();
	}

	public ReturnVoucher(Long id, String voucherCode, BigDecimal amount, BigDecimal balance, LocalDateTime expirationDate, boolean active, List<ReturnCaptureItem> captureItemList) {
		this.id = id;
		this.voucherCode = voucherCode;
		this.amount = amount;
		this.balance = balance;
		this.expirationDate = expirationDate;
		this.active = active;
		this.captureItemList = captureItemList;
	}

	public ReturnVoucher() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<ReturnCaptureItem> getCaptureItemList() {
		return captureItemList;
	}

	public void setCaptureItemList(List<ReturnCaptureItem> captureItemList) {
		this.captureItemList = captureItemList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ReturnVoucher that = (ReturnVoucher) o;

		if (active != that.active) return false;
		if (!Objects.equals(id, that.id)) return false;
		if (!Objects.equals(voucherCode, that.voucherCode)) return false;
		if (!Objects.equals(amount, that.amount)) return false;
		if (!Objects.equals(balance, that.balance)) return false;
		if (!Objects.equals(expirationDate, that.expirationDate))
			return false;
		return Objects.equals(captureItemList, that.captureItemList);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (voucherCode != null ? voucherCode.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (balance != null ? balance.hashCode() : 0);
		result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (captureItemList != null ? captureItemList.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ReturnVoucher{" +
				"id=" + id +
				", voucherCode='" + voucherCode + '\'' +
				", amount=" + amount +
				", balance=" + balance +
				", expirationDate=" + expirationDate +
				", active=" + active +
				", captureItemList=" + captureItemList +
				'}';
	}

	public static class ReturnVoucherBuilder {
		private Long id;
		private String voucherCode;
		private BigDecimal amount;
		private BigDecimal balance;
		private LocalDateTime expirationDate;
		private boolean active;
		private List<ReturnCaptureItem> captureItemList;

		ReturnVoucherBuilder() {
		}

		public ReturnVoucherBuilder id(final Long id) {
			this.id = id;
			return this;
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

		public ReturnVoucherBuilder captureItemList(final List<ReturnCaptureItem> captureItemList) {
			this.captureItemList = captureItemList;
			return this;
		}

		public ReturnVoucher build() {
			return new ReturnVoucher(this.id, this.voucherCode, this.amount, this.balance, this.expirationDate, this.active, this.captureItemList);
		}

		public String toString() {
			return "ReturnVoucher.ReturnVoucherBuilder(id=" + this.id + ", voucherCode=" + this.voucherCode + ", amount=" + this.amount + ", balance=" + this.balance + ", expirationDate=" + this.expirationDate + ", active=" + this.active + ", captureItemList=" + this.captureItemList + ")";
		}
	}
}
