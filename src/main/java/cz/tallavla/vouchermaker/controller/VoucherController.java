package cz.tallavla.vouchermaker.controller;

import cz.tallavla.vouchermaker.exception.VoucherCodeIsNullException;
import cz.tallavla.vouchermaker.exception.WrongVoucherActionException;
import cz.tallavla.vouchermaker.model.controller.*;
import cz.tallavla.vouchermaker.model.service.CaptureDTO;
import cz.tallavla.vouchermaker.model.service.CaptureItemDTO;
import cz.tallavla.vouchermaker.service.VoucherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static cz.tallavla.vouchermaker.utils.Constants.ACTIVATE;
import static cz.tallavla.vouchermaker.utils.Constants.DEACTIVATE;

@RestController
@RequestMapping("/vouchermaker")
@Slf4j
@Api(value = "CRUD Rest API for Voucher maker", tags = "Voucher Maker API", protocols = "http")
public class VoucherController {

	@Autowired
	private VoucherService voucherService;

	private final ModelMapper modelMapper = new ModelMapper();

	@ApiOperation(value = "REST API to create Voucher", consumes = "application/json", produces =  "application/json", notes = "Create Voucher")
//	@ApiResponses({
//			@ApiResponse(code = 200, message = "Valid response ", response = ReturnVoucher.class),
//			@ApiResponse(code = 401, message = "Validation failed", response = ErrorResponse.class),
//			@ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
//			@ApiResponse(code = 500, message = "Operation failed, internal server error", response = ErrorResponse.class)
//	})
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/vouchers")
	public ResponseEntity<ReturnVoucher> createVoucher(@RequestBody @Validated NewVoucher newVoucher) {

			return new ResponseEntity<>(modelMapper.map(voucherService.createVoucher(newVoucher.getAmount()), ReturnVoucher.class), HttpStatus.CREATED);
	}

	@ApiOperation(value = "REST API to get Voucher", consumes = "application/json", produces =  "application/json", notes = "Get Voucher")
	@GetMapping("/vouchers/{code}")
//	@ApiResponses({
//			@ApiResponse(code = 200, message = "Valid response ", response = ReturnVoucher.class),
//			@ApiResponse(code = 401, message = "Validation failed", response = ErrorResponse.class),
//			@ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
//			@ApiResponse(code = 500, message = "Operation failed, internal server error", response = ErrorResponse.class)
//	})
	public ResponseEntity<ReturnVoucher> getVoucher(@PathVariable("code") String code) {
		log.info("Getting voucher with code {}.", code);

		return new ResponseEntity<>(modelMapper.map(voucherService.getVoucher(code), ReturnVoucher.class), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "REST API to act Voucher", consumes = "application/json", produces =  "application/json", notes = "Act Voucher")
	@PatchMapping(value = "/vouchers/{code}/action")
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

		return new ResponseEntity<>(voucherService.actVoucher(code, action), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "REST API to create Capture", consumes = "application/json", produces =  "application/json", notes = "Create Voucher")
	@PostMapping("/captures")
	public ResponseEntity<InformationResponse> captureAction(@RequestBody NewCapture newCapture) {

		return new ResponseEntity<>(voucherService.actCapture(mapCapture(newCapture)), HttpStatus.OK);
	}

	@ApiOperation(value = "REST API to get Capture", consumes = "application/json", produces =  "application/json", notes = "Get Capture")
	@GetMapping("/captures/{id}")
	public ResponseEntity<ReturnCapture> getCapture(@PathVariable(value = "id") Long id) {

		return new ResponseEntity<>(modelMapper.map(voucherService.getCapture(id), ReturnCapture.class), HttpStatus.OK);
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
				throw new VoucherCodeIsNullException("Voucher Code is empty.");
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
