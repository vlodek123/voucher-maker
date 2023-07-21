package cz.tallavla.vouchermaker.model.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class NewVoucher {

	//TODO: anotace pro Object Mapper
	private BigDecimal amount;

	public NewVoucher() {
	}

	public NewVoucher(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
