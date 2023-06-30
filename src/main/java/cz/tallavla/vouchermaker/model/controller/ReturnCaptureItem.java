package cz.tallavla.vouchermaker.model.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.tallavla.vouchermaker.model.repository.Capture;
import cz.tallavla.vouchermaker.model.repository.Voucher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnCaptureItem {

	private Long id;

	private String voucherCode;

	private BigDecimal captureAmount;

	private boolean processed;
	@JsonIgnore
	private ReturnCapture capture;
	@JsonIgnore
	private ReturnVoucher voucher;

	@Override
	public String toString() {
		return "ReturnCaptureItem{" +
				"id=" + id +
				", voucherCode='" + voucherCode + '\'' +
				", captureAmount=" + captureAmount +
				", processed=" + processed +
				'}';
	}
}
