package cz.tallavla.vouchermaker.model.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Objects;

public class ReturnCaptureItem {

	private Long id;

	private String voucherCode;

	private BigDecimal captureAmount;

	private boolean processed;
	@JsonIgnore
	private ReturnCapture capture;
	@JsonIgnore
	private ReturnVoucher voucher;

	public static ReturnCaptureItemBuilder builder() {
		return new ReturnCaptureItemBuilder();
	}

	@Override
	public String toString() {
		return "ReturnCaptureItem{" +
				"id=" + id +
				", voucherCode='" + voucherCode + '\'' +
				", captureAmount=" + captureAmount +
				", processed=" + processed +
				'}';
	}

	public ReturnCaptureItem(Long id, String voucherCode, BigDecimal captureAmount, boolean processed, ReturnCapture capture, ReturnVoucher voucher) {
		this.id = id;
		this.voucherCode = voucherCode;
		this.captureAmount = captureAmount;
		this.processed = processed;
		this.capture = capture;
		this.voucher = voucher;
	}

	public ReturnCaptureItem() {
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

	public ReturnCapture getCapture() {
		return capture;
	}

	public void setCapture(ReturnCapture capture) {
		this.capture = capture;
	}

	public ReturnVoucher getVoucher() {
		return voucher;
	}

	public void setVoucher(ReturnVoucher voucher) {
		this.voucher = voucher;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ReturnCaptureItem that = (ReturnCaptureItem) o;

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
	public static class ReturnCaptureItemBuilder {
		private Long id;
		private String voucherCode;
		private BigDecimal captureAmount;
		private boolean processed;
		private ReturnCapture capture;
		private ReturnVoucher voucher;

		ReturnCaptureItemBuilder() {
		}

		public ReturnCaptureItemBuilder id(final Long id) {
			this.id = id;
			return this;
		}

		public ReturnCaptureItemBuilder voucherCode(final String voucherCode) {
			this.voucherCode = voucherCode;
			return this;
		}

		public ReturnCaptureItemBuilder captureAmount(final BigDecimal captureAmount) {
			this.captureAmount = captureAmount;
			return this;
		}

		public ReturnCaptureItemBuilder processed(final boolean processed) {
			this.processed = processed;
			return this;
		}

		@JsonIgnore
		public ReturnCaptureItemBuilder capture(final ReturnCapture capture) {
			this.capture = capture;
			return this;
		}

		@JsonIgnore
		public ReturnCaptureItemBuilder voucher(final ReturnVoucher voucher) {
			this.voucher = voucher;
			return this;
		}

		public ReturnCaptureItem build() {
			return new ReturnCaptureItem(this.id, this.voucherCode, this.captureAmount, this.processed, this.capture, this.voucher);
		}

		public String toString() {
			return "ReturnCaptureItem.ReturnCaptureItemBuilder(id=" + this.id + ", voucherCode=" + this.voucherCode + ", captureAmount=" + this.captureAmount + ", processed=" + this.processed + ", capture=" + this.capture + ", voucher=" + this.voucher + ")";
		}
	}

}
