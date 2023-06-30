package cz.tallavla.vouchermaker.service;

import cz.tallavla.vouchermaker.model.controller.InformationResponse;
import cz.tallavla.vouchermaker.model.service.CaptureDTO;
import cz.tallavla.vouchermaker.model.service.CaptureDTOReturned;
import cz.tallavla.vouchermaker.model.service.VoucherDTOReturned;

import java.math.BigDecimal;

public interface VoucherService {

	VoucherDTOReturned createVoucher(BigDecimal amount);
	VoucherDTOReturned getVoucher(String code);

	InformationResponse actVoucher(String code, String action);

	InformationResponse actCapture(CaptureDTO captureDTO);

	CaptureDTOReturned getCapture(Long id);
}
