package cz.tallavla.vouchermaker.model.controller;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCaptureItem {

	private String voucherCode;

	private String captureAmount;

}
