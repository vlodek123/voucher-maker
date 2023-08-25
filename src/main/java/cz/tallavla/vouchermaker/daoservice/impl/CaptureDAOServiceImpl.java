package cz.tallavla.vouchermaker.daoservice.impl;

import cz.tallavla.vouchermaker.controller.VoucherController;
import cz.tallavla.vouchermaker.daoservice.CaptureDAOService;
import cz.tallavla.vouchermaker.model.repository.Capture;
import cz.tallavla.vouchermaker.model.service.CaptureDTOReturned;
import cz.tallavla.vouchermaker.repository.CaptureRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CaptureDAOServiceImpl implements CaptureDAOService {

	private final static Logger log = LoggerFactory.getLogger(VoucherController.class);
	ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CaptureRepository captureRepository;

	@Override
	public CaptureDTOReturned saveCapture(CaptureDTOReturned captureForSave) {

		return modelMapper.map(captureRepository.save(modelMapper.map(captureForSave, Capture.class)), CaptureDTOReturned.class);
	}

	@Override
	public Optional<CaptureDTOReturned> getCapture(Long id) {

		Optional<Capture> captureFound = captureRepository.findById(id);

		return captureFound.map(capture -> modelMapper.map(capture, CaptureDTOReturned.class));
	}
}
