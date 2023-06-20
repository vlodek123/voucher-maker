package cz.tallavla.vouchermaker.model.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationResponse {
	private String info;
	private String voucherCode;
}
