package cz.tallavla.vouchermaker.model.repository;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vouchers")
public class Voucher {

	@SequenceGenerator(name = "voucherIdSeq", sequenceName = "vouchers_id_seq", initialValue = 10001, allocationSize = 1)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voucherIdSeq")
	private Long id;

	@Column(nullable = false, unique = true)
	private String voucherCode;
	@Column(nullable = false)
	private BigDecimal amount;
	@Column(nullable = false)
	private BigDecimal balance;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@Column(nullable = false)
	private LocalDateTime expirationDate;

	@Column(nullable = false)
	private boolean active;

	@OneToMany(mappedBy = "voucher")
	private List<CaptureItem> captureItems;

	public static VoucherBuilder builder() {
		return new VoucherBuilder();
	}

	public VoucherBuilder toBuilder() {
		return (new VoucherBuilder()).id(this.id).voucherCode(this.voucherCode).amount(this.amount).balance(this.balance).createdDate(this.createdDate).lastUpdatedDate(this.lastUpdatedDate).expirationDate(this.expirationDate).active(this.active).captureItems(this.captureItems);
	}

	@Override
	public String toString() {
		return "Voucher{" +
				"id=" + id +
				", voucherCode='" + voucherCode + '\'' +
				", amount=" + amount +
				", balance=" + balance +
				", createdDate=" + createdDate +
				", lastUpdatedDate=" + lastUpdatedDate +
				", expirationDate=" + expirationDate +
				", active=" + active +
				", captureItems=" + captureItems +
				'}';
	}

	public Voucher(Long id, String voucherCode, BigDecimal amount, BigDecimal balance, LocalDateTime createdDate, LocalDateTime lastUpdatedDate, LocalDateTime expirationDate, boolean active, List<CaptureItem> captureItems) {
		this.id = id;
		this.voucherCode = voucherCode;
		this.amount = amount;
		this.balance = balance;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.expirationDate = expirationDate;
		this.active = active;
		this.captureItems = captureItems;
	}

	public Voucher() {
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
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

	public List<CaptureItem> getCaptureItems() {
		return captureItems;
	}

	public void setCaptureItems(List<CaptureItem> captureItems) {
		this.captureItems = captureItems;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Voucher voucher = (Voucher) o;

		if (active != voucher.active) return false;
		if (!Objects.equals(id, voucher.id)) return false;
		if (!Objects.equals(voucherCode, voucher.voucherCode)) return false;
		if (!Objects.equals(amount, voucher.amount)) return false;
		if (!Objects.equals(balance, voucher.balance)) return false;
		if (!Objects.equals(createdDate, voucher.createdDate)) return false;
		if (!Objects.equals(lastUpdatedDate, voucher.lastUpdatedDate))
			return false;
		if (!Objects.equals(expirationDate, voucher.expirationDate))
			return false;
		return Objects.equals(captureItems, voucher.captureItems);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (voucherCode != null ? voucherCode.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		result = 31 * result + (balance != null ? balance.hashCode() : 0);
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + (lastUpdatedDate != null ? lastUpdatedDate.hashCode() : 0);
		result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (captureItems != null ? captureItems.hashCode() : 0);
		return result;
	}
	public static class VoucherBuilder {
		private Long id;
		private String voucherCode;
		private BigDecimal amount;
		private BigDecimal balance;
		private LocalDateTime createdDate;
		private LocalDateTime lastUpdatedDate;
		private LocalDateTime expirationDate;
		private boolean active;
		private List<CaptureItem> captureItems;
		VoucherBuilder() {
		}

		public VoucherBuilder id(final Long id) {
			this.id = id;
			return this;
		}

		public VoucherBuilder voucherCode(final String voucherCode) {
			this.voucherCode = voucherCode;
			return this;
		}

		public VoucherBuilder amount(final BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public VoucherBuilder balance(final BigDecimal balance) {
			this.balance = balance;
			return this;
		}

		public VoucherBuilder createdDate(final LocalDateTime createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public VoucherBuilder lastUpdatedDate(final LocalDateTime lastUpdatedDate) {
			this.lastUpdatedDate = lastUpdatedDate;
			return this;
		}

		public VoucherBuilder expirationDate(final LocalDateTime expirationDate) {
			this.expirationDate = expirationDate;
			return this;
		}

		public VoucherBuilder active(final boolean active) {
			this.active = active;
			return this;
		}

		public VoucherBuilder captureItems(final List<CaptureItem> captureItems) {
			this.captureItems = captureItems;
			return this;
		}

		public Voucher build() {
			return new Voucher(this.id, this.voucherCode, this.amount, this.balance, this.createdDate, this.lastUpdatedDate, this.expirationDate, this.active, this.captureItems);
		}

		public String toString() {
			return "Voucher.VoucherBuilder(id=" + this.id + ", voucherCode=" + this.voucherCode + ", amount=" + this.amount + ", balance=" + this.balance + ", createdDate=" + this.createdDate + ", lastUpdatedDate=" + this.lastUpdatedDate + ", expirationDate=" + this.expirationDate + ", active=" + this.active + ", captureItems=" + this.captureItems +  ")";
		}
	}
}
