package cz.tallavla.vouchermaker.model.controller;

import java.math.BigDecimal;
import java.util.Objects;

public class NewCaptureItem {

	private String voucherCode;

	private BigDecimal captureAmount;

	public static NewCaptureItemBuilder builder() {return new NewCaptureItemBuilder();}

	public NewCaptureItem(String voucherCode, BigDecimal captureAmount) {
		this.voucherCode = voucherCode;
		this.captureAmount = captureAmount;
	}

	public NewCaptureItem() {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NewCaptureItem that = (NewCaptureItem) o;

		if (!Objects.equals(voucherCode, that.voucherCode)) return false;
		return Objects.equals(captureAmount, that.captureAmount);
	}

	@Override
	public int hashCode() {
		int result = voucherCode != null ? voucherCode.hashCode() : 0;
		result = 31 * result + (captureAmount != null ? captureAmount.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "NewCaptureItem{" +
				"voucherCode='" + voucherCode + '\'' +
				", captureAmount='" + captureAmount + '\'' +
				'}';
	}

	public static class NewCaptureItemBuilder {
		private String voucherCode;
		private BigDecimal captureAmount;

		NewCaptureItemBuilder() {
		}

		public NewCaptureItemBuilder voucherCode(final String voucherCode) {
			this.voucherCode = voucherCode;
			return this;
		}

		public NewCaptureItemBuilder captureAmount(final BigDecimal captureAmount) {
			this.captureAmount = captureAmount;
			return this;
		}

		public NewCaptureItem build() {
			return new NewCaptureItem(this.voucherCode, this.captureAmount);
		}

		public String toString() {
			return "NewCaptureItem.NewCaptureItemBuilder(voucherCode=" + this.voucherCode + ", captureAmount=" + this.captureAmount + ")";
		}
	}
}
