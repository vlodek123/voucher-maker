package cz.tallavla.vouchermaker.model.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewVoucher {

	//TODO: anotace pro Object Mapper
	private String amount;
}
