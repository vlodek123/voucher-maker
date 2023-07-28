package cz.tallavla.vouchermaker.model.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "NewVoucher model Information")
public class NewVoucher {

	@ApiModelProperty(value = "NewVoucher amount")
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
