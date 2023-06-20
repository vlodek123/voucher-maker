package cz.tallavla.vouchermaker.controller;

import cz.tallavla.vouchermaker.exception.WrongAmountFormatException;
import cz.tallavla.vouchermaker.exception.WrongVoucherActionException;
import cz.tallavla.vouchermaker.mappers.Mappers;
import cz.tallavla.vouchermaker.model.controller.InformationResponse;
import cz.tallavla.vouchermaker.model.controller.ReturnVoucher;
import cz.tallavla.vouchermaker.model.controller.NewVoucher;
import cz.tallavla.vouchermaker.model.controller.VoucherAction;
import cz.tallavla.vouchermaker.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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



}
