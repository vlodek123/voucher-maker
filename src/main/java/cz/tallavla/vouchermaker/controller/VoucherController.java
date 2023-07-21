package cz.tallavla.vouchermaker.controller;

import cz.tallavla.vouchermaker.exception.VoucherCodeIsNullException;
import cz.tallavla.vouchermaker.exception.WrongFormatException;
import cz.tallavla.vouchermaker.exception.WrongVoucherActionException;
import cz.tallavla.vouchermaker.mappers.Mappers;
import cz.tallavla.vouchermaker.model.controller.*;
import cz.tallavla.vouchermaker.model.service.CaptureDTO;
import cz.tallavla.vouchermaker.model.service.CaptureItemDTO;
import cz.tallavla.vouchermaker.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import static cz.tallavla.vouchermaker.utils.Constants.ACTIVATE;
import static cz.tallavla.vouchermaker.utils.Constants.DEACTIVATE;

@RestController
@RequestMapping("/voucher")
@Slf4j
public class VoucherController {

	@Autowired
	private VoucherService voucherService;

	private final ModelMapper modelMapper = new ModelMapper();

	@PostMapping("/new")
	public ResponseEntity<ReturnVoucher> createVoucher(@RequestBody @Validated NewVoucher newVoucher) {

			return new ResponseEntity<>(modelMapper.map(voucherService.createVoucher(newVoucher.getAmount()), ReturnVoucher.class), HttpStatus.CREATED);
	}

	@GetMapping("/{code}")
	public ResponseEntity<ReturnVoucher> getVoucher(@PathVariable("code") String code) {
		log.info("Getting voucher with code {}.", code);

		return new ResponseEntity<>(modelMapper.map(voucherService.getVoucher(code), ReturnVoucher.class), HttpStatus.OK);
	}

	@PatchMapping(value = "/{code}/action")
	public ResponseEntity<InformationResponse> voucherAction(
			@PathVariable(value = "code") String code,
			@RequestBody VoucherAction voucherAction) {

		if (voucherAction == null || voucherAction.getAction() == null) {
			throw new WrongVoucherActionException("Invalid request for voucher action");
		}

		String action = voucherAction.getAction().toUpperCase();
		if (!ACTIVATE.equals(action) && !DEACTIVATE.equals(action)) {
			throw new WrongVoucherActionException("Unsupported action from client: Only 'ACTIVATE' or 'DEACTIVATE' actions are supported.");
		}

		return new ResponseEntity<>(voucherService.actVoucher(code, action), HttpStatus.ACCEPTED);
	}

	@PostMapping("/capture")
	public ResponseEntity<InformationResponse> captureAction(@RequestBody NewCapture newCapture) {

		return new ResponseEntity<>(voucherService.actCapture(mapCapture(newCapture)), HttpStatus.ACCEPTED);
	}

	@GetMapping("/capture/{id}")
	public ResponseEntity<ReturnCapture> getCapture(@PathVariable(value = "id") Long id) {

		return new ResponseEntity<>(modelMapper.map(voucherService.getCapture(id), ReturnCapture.class), HttpStatus.ACCEPTED);
	}

	private CaptureDTO mapCapture(NewCapture newCapture) {

		if (!CollectionUtils.isNotEmpty(newCapture.getCaptureItems())) {
			throw new VoucherCodeIsNullException("No capture items to process.");
		}

		CaptureDTO captureDTO = new CaptureDTO(new ArrayList<>());

		for (NewCaptureItem captureItem : newCapture.getCaptureItems()
		) {
			CaptureItemDTO captureItemDTO = new CaptureItemDTO();

			captureItemDTO.setCaptureAmount(captureItem.getCaptureAmount());


			if (testVoucherCodeForNull(captureItem.getVoucherCode())) {
				captureItemDTO.setVoucherCode(captureItem.getVoucherCode());
			} else {
				throw new VoucherCodeIsNullException("Voucher Code is empty."); //TODO: as above
			}
			captureDTO.getCaptureItemDTOS().add(captureItemDTO);
		}
		return captureDTO;
	}

	private boolean testVoucherCodeForNull(String voucherCode) {
		if (voucherCode != null && !voucherCode.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
