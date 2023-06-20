package cz.tallavla.vouchermaker.service;

import cz.tallavla.vouchermaker.model.controller.InformationResponse;
import cz.tallavla.vouchermaker.model.controller.ReturnVoucher;
import cz.tallavla.vouchermaker.model.controller.VoucherAction;

import java.math.BigDecimal;

public interface VoucherService {

	ReturnVoucher createVoucher(BigDecimal amount);
	ReturnVoucher getVoucher(String code);

	InformationResponse actVoucher(String code, String action);
}
