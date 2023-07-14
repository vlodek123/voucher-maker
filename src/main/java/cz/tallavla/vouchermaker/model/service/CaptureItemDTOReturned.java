package cz.tallavla.vouchermaker.model.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Objects;

public class CaptureItemDTOReturned {
	private Long id;

	private String voucherCode;

	private BigDecimal captureAmount;

	private boolean processed;
	@JsonIgnore
	private CaptureDTOReturned capture;
	@JsonIgnore
	private VoucherDTOReturned voucher;

	public static CaptureItemDTOReturnedBuilder builder() {
		return new CaptureItemDTOReturnedBuilder();
	}

	@Override
	public String toString() {
		return "CaptureItemDTOReturned{" +
				"id=" + id +
				", voucherCode='" + voucherCode + '\'' +
				", captureAmount=" + captureAmount +
				", processed=" + processed +
				'}';
	}

	public CaptureItemDTOReturned() {
	}

	public CaptureItemDTOReturned(Long id, String voucherCode, BigDecimal captureAmount, boolean processed, CaptureDTOReturned capture, VoucherDTOReturned voucher) {
		this.id = id;
		this.voucherCode = voucherCode;
		this.captureAmount = captureAmount;
		this.processed = processed;
		this.capture = capture;
		this.voucher = voucher;
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

	public BigDecimal getCaptureAmount() {
		return captureAmount;
	}

	public void setCaptureAmount(BigDecimal captureAmount) {
		this.captureAmount = captureAmount;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public CaptureDTOReturned getCapture() {
		return capture;
	}

	public void setCapture(CaptureDTOReturned capture) {
		this.capture = capture;
	}

	public VoucherDTOReturned getVoucher() {
		return voucher;
	}

	public void setVoucher(VoucherDTOReturned voucher) {
		this.voucher = voucher;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CaptureItemDTOReturned that = (CaptureItemDTOReturned) o;

		if (processed != that.processed) return false;
		if (!Objects.equals(id, that.id)) return false;
		if (!Objects.equals(voucherCode, that.voucherCode)) return false;
		if (!Objects.equals(captureAmount, that.captureAmount))
			return false;
		if (!Objects.equals(capture, that.capture)) return false;
		return Objects.equals(voucher, that.voucher);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (voucherCode != null ? voucherCode.hashCode() : 0);
		result = 31 * result + (captureAmount != null ? captureAmount.hashCode() : 0);
		result = 31 * result + (processed ? 1 : 0);
		result = 31 * result + (capture != null ? capture.hashCode() : 0);
		result = 31 * result + (voucher != null ? voucher.hashCode() : 0);
		return result;
	}

	public static class CaptureItemDTOReturnedBuilder {

		private Long id;
		private String voucherCode;
		private BigDecimal captureAmount;
		private boolean processed;
		private CaptureDTOReturned capture;
		private VoucherDTOReturned voucher;

		public CaptureItemDTOReturnedBuilder() {
		}

		public CaptureItemDTOReturnedBuilder id(final Long id) {
			this.id = id;
			return this;
		}
		public CaptureItemDTOReturnedBuilder voucherCode(final String voucherCode) {
			this.voucherCode = voucherCode;
			return this;
		}
		public CaptureItemDTOReturnedBuilder captureAmount(final BigDecimal captureAmount) {
			this.captureAmount = captureAmount;
			return this;
		}
		public CaptureItemDTOReturnedBuilder processed(final boolean processed) {
			this.processed = processed;
			return this;
		}
		public CaptureItemDTOReturnedBuilder capture(final CaptureDTOReturned capture) {
			this.capture = capture;
			return this;
		}
		public CaptureItemDTOReturnedBuilder voucher(final VoucherDTOReturned voucher) {
			this.voucher = voucher;
			return this;
		}
		public CaptureItemDTOReturned build() {
			return new CaptureItemDTOReturned(this.id, this.voucherCode, this.captureAmount, this.processed, this.capture, this.voucher);
		}
	}
}
