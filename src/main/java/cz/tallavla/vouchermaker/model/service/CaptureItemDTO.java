package cz.tallavla.vouchermaker.model.service;

import java.math.BigDecimal;
import java.util.Objects;

public class CaptureItemDTO {

	private String voucherCode;

	private BigDecimal captureAmount;

//	public static CaptureItemDTOBuilder builder() {
//		return new CaptureItemDTOBuilder();
//	}

	public CaptureItemDTO(String voucherCode, BigDecimal captureAmount) {
		this.voucherCode = voucherCode;
		this.captureAmount = captureAmount;
	}

	public CaptureItemDTO() {
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

		CaptureItemDTO that = (CaptureItemDTO) o;

		if (!Objects.equals(voucherCode, that.voucherCode)) return false;
		return Objects.equals(captureAmount, that.captureAmount);
	}

	@Override
	public int hashCode() {
		int result = voucherCode != null ? voucherCode.hashCode() : 0;
		result = 31 * result + (captureAmount != null ? captureAmount.hashCode() : 0);
		return result;
	}
//	public static class CaptureItemDTOBuilder {
//		private String voucherCode;
//		private BigDecimal captureAmount;
//
//		CaptureItemDTOBuilder() {
//		}
//
//		public CaptureItemDTOBuilder voucherCode(final String voucherCode) {
//			this.voucherCode = voucherCode;
//			return this;
//		}
//
//		public CaptureItemDTOBuilder captureAmount(final BigDecimal captureAmount) {
//			this.captureAmount = captureAmount;
//			return this;
//		}
//
//		public CaptureItemDTO build() {
//			return new CaptureItemDTO(this.voucherCode, this.captureAmount);
//		}
//
//		public String toString() {
//			return "CaptureItemDTO.CaptureItemDTOBuilder(voucherCode=" + this.voucherCode + ", captureAmount=" + this.captureAmount + ")";
//		}
//	}

}
