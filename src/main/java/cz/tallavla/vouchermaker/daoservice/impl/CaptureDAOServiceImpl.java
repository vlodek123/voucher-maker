package cz.tallavla.vouchermaker.daoservice.impl;

import cz.tallavla.vouchermaker.daoservice.CaptureDAOService;
import cz.tallavla.vouchermaker.model.repository.Capture;
import cz.tallavla.vouchermaker.model.service.CaptureDTOReturned;
import cz.tallavla.vouchermaker.repository.CaptureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaptureDAOServiceImpl implements CaptureDAOService {

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
