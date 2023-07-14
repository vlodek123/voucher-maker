package cz.tallavla.vouchermaker.model.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class VoucherDTO {

	private String voucherCode;
	private BigDecimal amount;
	private BigDecimal balance;
	private LocalDateTime expirationDate;
	private boolean active;
	private List<CaptureItemDTO> captureItemList;

	public static VoucherDTOBuilder builder() {return new VoucherDTOBuilder();}

	public VoucherDTO(String voucherCode, BigDecimal amount, BigDecimal balance, LocalDateTime expirationDate, boolean active, List<CaptureItemDTO> captureItemList) {
		this.voucherCode = voucherCode;
		this.amount = amount;
		this.balance = balance;
		this.expirationDate = expirationDate;
		this.active = active;
		this.captureItemList = captureItemList;
	}

	public VoucherDTO() {
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

	public List<CaptureItemDTO> getCaptureItemList() {
		return captureItemList;
	}

	public void setCaptureItemList(List<CaptureItemDTO> captureItemList) {
		this.captureItemList = captureItemList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		VoucherDTO that = (VoucherDTO) o;

		if (active != that.active) return false;
		if (!Objects.equals(voucherCode, that.voucherCode)) return false;
		if (!Objects.equals(amount, that.amount)) return false;
		if (!Objects.equals(balance, that.balance)) return false;
		if (!Objects.equals(expirationDate, that.expirationDate))
			return false;
		return Objects.equals(captureItemList, that.captureItemList);
	}

	@Override
	public int hashCode() {
		int result = voucherCode != null ? voucherCode.hashCode() : 0;
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (balance != null ? balance.hashCode() : 0);
		result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (captureItemList != null ? captureItemList.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "VoucherDTO{" +
				"voucherCode='" + voucherCode + '\'' +
				", amount=" + amount +
				", balance=" + balance +
				", expirationDate=" + expirationDate +
				", active=" + active +
				", captureItemList=" + captureItemList +
				'}';
	}

	public static class VoucherDTOBuilder {
		private String voucherCode;
		private BigDecimal amount;
		private BigDecimal balance;
		private LocalDateTime expirationDate;
		private boolean active;
		private List<CaptureItemDTO> captureItemList;

		public VoucherDTOBuilder() {
		}
		public VoucherDTOBuilder voucherCode(final String voucherCode) {
			this.voucherCode = voucherCode;
			return this;
		}
		public VoucherDTOBuilder amount(final BigDecimal amount) {
			this.amount = amount;
			return this;
		}
		public VoucherDTOBuilder balance(final BigDecimal balance) {
			this.balance = balance;
			return this;
		}
		public VoucherDTOBuilder expirationDate(final LocalDateTime expirationDate) {
			this.expirationDate = expirationDate;
			return this;
		}
		public VoucherDTOBuilder active(final boolean active) {
			this.active = active;
			return this;
		}
		public VoucherDTOBuilder captureItemList(final List<CaptureItemDTO> captureItemList) {
			this.captureItemList = captureItemList;
			return this;
		}

		public VoucherDTO build() {return new VoucherDTO(this.voucherCode, this.amount, this.balance, this.expirationDate, this.active, this.captureItemList);}
	}
}
