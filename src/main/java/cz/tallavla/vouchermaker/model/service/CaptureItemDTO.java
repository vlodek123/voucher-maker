package cz.tallavla.vouchermaker.model.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptureItemDTO {

	private String voucherCode;

	private BigDecimal captureAmount;
}
