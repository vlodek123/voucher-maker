package cz.tallavla.vouchermaker.daoservice;

import cz.tallavla.vouchermaker.model.service.CaptureDTOReturned;

import java.util.Optional;

public interface CaptureDAOService {

	CaptureDTOReturned saveCapture(CaptureDTOReturned captureDTOReturned);

	public Optional<CaptureDTOReturned> getCapture(Long id);
}
