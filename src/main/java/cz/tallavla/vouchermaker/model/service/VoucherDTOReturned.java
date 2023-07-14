package cz.tallavla.vouchermaker.model.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class VoucherDTOReturned {

	private Long id;
	private String voucherCode;
	private BigDecimal amount;
	private BigDecimal balance;
	private LocalDateTime expirationDate;
	private LocalDateTime createdDate;
	private boolean active;
	private List<CaptureItemDTOReturned> captureItemList;

	public static VoucherDTOReturnedBuilder builder() {return new VoucherDTOReturnedBuilder();}

	public VoucherDTOReturned() {
	}

	public VoucherDTOReturned(Long id, String voucherCode, BigDecimal amount, BigDecimal balance, LocalDateTime expirationDate, LocalDateTime createdDate, boolean active, List<CaptureItemDTOReturned> captureItemList) {
		this.id = id;
		this.voucherCode = voucherCode;
		this.amount = amount;
		this.balance = balance;
		this.expirationDate = expirationDate;
		this.createdDate = createdDate;
		this.active = active;
		this.captureItemList = captureItemList;
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<CaptureItemDTOReturned> getCaptureItemList() {
		return captureItemList;
	}

	public void setCaptureItemList(List<CaptureItemDTOReturned> captureItemList) {
		this.captureItemList = captureItemList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		VoucherDTOReturned that = (VoucherDTOReturned) o;

		if (active != that.active) return false;
		if (!Objects.equals(id, that.id)) return false;
		if (!Objects.equals(voucherCode, that.voucherCode)) return false;
		if (!Objects.equals(amount, that.amount)) return false;
		if (!Objects.equals(balance, that.balance)) return false;
		if (!Objects.equals(expirationDate, that.expirationDate))
			return false;
		if (!Objects.equals(createdDate, that.createdDate)) return false;
		return Objects.equals(captureItemList, that.captureItemList);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (voucherCode != null ? voucherCode.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (balance != null ? balance.hashCode() : 0);
		result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (captureItemList != null ? captureItemList.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "VoucherDTOReturned{" +
				"id=" + id +
				", voucherCode='" + voucherCode + '\'' +
				", amount=" + amount +
				", balance=" + balance +
				", expirationDate=" + expirationDate +
				", createdDate=" + createdDate +
				", active=" + active +
				", captureItemList=" + captureItemList +
				'}';
	}

	public static class VoucherDTOReturnedBuilder {

		private Long id;
		private String voucherCode;
		private BigDecimal amount;
		private BigDecimal balance;
		private LocalDateTime expirationDate;
		private LocalDateTime createdDate;
		private boolean active;
		private List<CaptureItemDTOReturned> captureItemList;

		public VoucherDTOReturnedBuilder() {
		}
		public VoucherDTOReturnedBuilder voucherCode(final Long id) {
			this.id = id;
			return this;
		}

		public VoucherDTOReturnedBuilder voucherCode(final String voucherCode) {
			this.voucherCode = voucherCode;
			return this;
		}
		public VoucherDTOReturnedBuilder amount(final BigDecimal amount) {
			this.amount = amount;
			return this;
		}
		public VoucherDTOReturnedBuilder balance(final BigDecimal balance) {
			this.balance = balance;
			return this;
		}
		public VoucherDTOReturnedBuilder expirationDate(final LocalDateTime expirationDate) {
			this.expirationDate = expirationDate;
			return this;
		}
		public VoucherDTOReturnedBuilder createdDate(final LocalDateTime createdDate) {
			this.createdDate = createdDate;
			return this;
		}
		public VoucherDTOReturnedBuilder active(final boolean active) {
			this.active = active;
			return this;
		}
		public VoucherDTOReturnedBuilder captureItemList(final List<CaptureItemDTOReturned> captureItemList) {
			this.captureItemList = captureItemList;
			return this;
		}

		public VoucherDTOReturned build() {return new VoucherDTOReturned(this.id, this.voucherCode, this.amount, this.balance, this.expirationDate, this.createdDate, this.active, this.captureItemList);}

	}
}
