package cz.tallavla.vouchermaker.model.service;

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
public class CaptureItemDTOReturned {
	private Long id;

	private String voucherCode;

	private BigDecimal captureAmount;

	private boolean processed;
	@JsonIgnore
	private CaptureDTOReturned capture;
	@JsonIgnore
	private VoucherDTOReturned voucher;

	@Override
	public String toString() {
		return "CaptureItemDTOReturned{" +
				"id=" + id +
				", voucherCode='" + voucherCode + '\'' +
				", captureAmount=" + captureAmount +
				", processed=" + processed +
				'}';
	}
}
