package cz.tallavla.vouchermaker.controller;

import cz.tallavla.vouchermaker.exception.VoucherCodeIsNullException;
import cz.tallavla.vouchermaker.exception.WrongAmountFormatException;
import cz.tallavla.vouchermaker.exception.WrongVoucherActionException;
import cz.tallavla.vouchermaker.mappers.Mappers;
import cz.tallavla.vouchermaker.model.controller.*;
import cz.tallavla.vouchermaker.model.service.CaptureDTO;
import cz.tallavla.vouchermaker.model.service.CaptureItemDTO;
import cz.tallavla.vouchermaker.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private Mappers mappers;

	@Autowired
	private VoucherService voucherService;

	@PostMapping("/new")
	public ResponseEntity<ReturnVoucher> createVoucher(@RequestBody NewVoucher newVoucher) {

		BigDecimal amount;

		try {
			amount = new BigDecimal(String.valueOf(newVoucher.getAmount()));
			log.info("New voucher amount: {}", amount);
			return new ResponseEntity<>(voucherService.createVoucher(amount), HttpStatus.CREATED);
		} catch (NumberFormatException ex) {
			throw new WrongAmountFormatException("Wrong format of amount.");
		}
	}

	@GetMapping("/{code}")
	public ResponseEntity<ReturnVoucher> getVoucher(@PathVariable("code") String code) {
		log.info("Getting voucher with code {}.", code);
		return new ResponseEntity<>(voucherService.getVoucher(code), HttpStatus.OK);
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
		System.out.println(mappers.getJsonString(newCapture));

		return new ResponseEntity<>(voucherService.actCapture(mapCapture(newCapture)), HttpStatus.ACCEPTED);
	}

	private CaptureDTO mapCapture(NewCapture newCapture) {

		CaptureDTO captureDTO = new CaptureDTO(new ArrayList<>());

		for (NewCaptureItem captureItem : newCapture.getCaptureItemList()
		) {
			CaptureItemDTO captureItemDTO = new CaptureItemDTO();
			BigDecimal captureAmount;
			try {
				captureAmount = new BigDecimal(String.valueOf(captureItem.getCaptureAmount()));
				log.info("Capture Item amount: {}", captureAmount);
				captureItemDTO.setCaptureAmount(captureAmount);
			} catch (NumberFormatException ex) {
				throw new WrongAmountFormatException("Wrong format of amount.");  //TODO: decline all capture, possible only decline wrong captureItem, not all capture
			}

			if (testVoucherCodeForNull(captureItem.getVoucherCode())) {
				captureItemDTO.setVoucherCode(captureItem.getVoucherCode());
			} else {
				throw new VoucherCodeIsNullException("Voucher Code is empty."); //TODO: as above
			}
			captureDTO.getCaptureItemList().add(captureItemDTO);
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
