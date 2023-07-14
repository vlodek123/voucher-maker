package cz.tallavla.vouchermaker.service.impl;

import cz.tallavla.vouchermaker.config.CodeConfig;
import cz.tallavla.vouchermaker.daoservice.CaptureDAOService;
import cz.tallavla.vouchermaker.daoservice.CaptureItemDAOService;
import cz.tallavla.vouchermaker.daoservice.VoucherDOAService;
import cz.tallavla.vouchermaker.exception.CaptureException;
import cz.tallavla.vouchermaker.exception.CaptureNotFoundException;
import cz.tallavla.vouchermaker.exception.VoucherNotFoundException;
import cz.tallavla.vouchermaker.exception.WrongVoucherActionException;
import cz.tallavla.vouchermaker.mappers.Mappers;
import cz.tallavla.vouchermaker.model.controller.InformationResponse;
import cz.tallavla.vouchermaker.model.service.*;
import cz.tallavla.vouchermaker.service.VoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;

import static cz.tallavla.vouchermaker.utils.Constants.ACTIVATE;
import static cz.tallavla.vouchermaker.utils.Constants.DEACTIVATE;
import static cz.tallavla.vouchermaker.utils.VoucherCodeGenerator.generateCode;


@Service
@RequiredArgsConstructor
@Slf4j
public class VoucherServiceImpl implements VoucherService {

	@Autowired
	VoucherDOAService voucherDOAService;
	@Autowired
	CaptureDAOService captureDAOService;

	@Autowired
	CaptureItemDAOService captureItemDAOService;

	@Autowired
	Mappers mappers;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public VoucherDTOReturned createVoucher(BigDecimal amount) {

		String voucherCode = generateCode(new CodeConfig("TE", "NO", createPattern()));

		long addedMonths = 12;
		LocalDateTime expirationDate = LocalDateTime.now().plusMonths(addedMonths).truncatedTo(ChronoUnit.MILLIS);

		VoucherDTO newVoucherDTO = VoucherDTO.builder()
				.voucherCode(voucherCode)
				.amount(amount)
				.balance(amount)
				.expirationDate(expirationDate)
				.active(true)
				.captureItemList(null)
				.build();



		return voucherDOAService.saveVoucher(newVoucherDTO);
	}

	@Override
	public VoucherDTOReturned getVoucher(String code) {

		Optional<VoucherDTOReturned> voucherFound = voucherDOAService.findVoucherByVoucherCode(code);

		if (voucherFound.isEmpty()) {
			throw new VoucherNotFoundException("Voucher not found.");
		} else {
			return voucherFound.get();
		}

	}

	@Override
	public InformationResponse actVoucher(String code, String action) {

		log.info("voucher {} action: {}", code, action);

		Optional<VoucherDTOReturned> voucherForAction = voucherDOAService.findVoucherByVoucherCode(code);

		if (voucherForAction.isEmpty()) {
			throw new VoucherNotFoundException("Voucher not found.");
		}

		VoucherDTOReturned voucher = voucherForAction.get();

		if (!isActionPossible(action, voucher.isActive())) {
			throw new WrongVoucherActionException(String.format("Not possible to %s voucher %s", action, voucher.getVoucherCode()));
		}

		switch (action) {
			case ACTIVATE:
				voucher.setActive(true);
				break;
			case DEACTIVATE:      //TODO: can be deactivated voucher processed ?
				voucher.setActive(false);
				break;
		}

		try {
			VoucherDTOReturned updatedVoucher = voucherDOAService.saveVoucher(voucher);
			return InformationResponse.builder().info(String.format("Voucher was %sD.", action)).id(updatedVoucher.getVoucherCode()).build();
		} catch (Exception e) {
			throw new WrongVoucherActionException(String.format("Not possible to %s voucher %s, persistence error", action, voucher.getVoucherCode()));
		}
	}

	@Override
	public InformationResponse actCapture(CaptureDTO captureDTO) {

 	// mapping CaptureDTO to Capture for save
		CaptureDTOReturned captureForSave = CaptureDTOReturned.builder()
				.processed(false)
				.numberOfItems(captureDTO.getCaptureItemDTOS().size())
				.build();

		CaptureDTOReturned savedCapture = captureDAOService.saveCapture(captureForSave);
		long captureId = savedCapture.getId();

		log.info("captureId: {}", captureId);

		// mapping and saving captureItems
		ArrayList<CaptureItemDTOReturned> captureItemListForSave = mapCaptureItemList(captureDTO, savedCapture);

		ArrayList<CaptureItemDTOReturned> captureItemListSaved = new ArrayList<>(captureItemDAOService.saveAllCaptureItems(captureItemListForSave));

		// in cycle get all vouchers in capture
		ArrayList<VoucherDTOReturned> listOfSavedVoucherToProcess = new ArrayList<>();

		for (CaptureItemDTOReturned item : captureItemListSaved
		) {
			// test if voucher was already processed and change it again
			var voucherMaybeNull = compareVouchers(listOfSavedVoucherToProcess, item.getVoucherCode());
			Optional<VoucherDTOReturned> voucherFound;
			boolean voucherFoundInList;
			if (voucherMaybeNull != null) {
				voucherFoundInList = true;
				voucherFound = Optional.of(voucherMaybeNull);
			} else {
				voucherFoundInList = false;
				voucherFound = voucherDOAService.findVoucherByVoucherCode(item.getVoucherCode());
			}

			// if voucher not found save capture and captureItem with processed flags as false
			if (voucherFound.isEmpty()) {
				savedCapture.setReason("Voucher not found.");
				captureDAOService.saveCapture(savedCapture);
				captureItemDAOService.saveAllCaptureItems(setProcessedFlag(captureItemListSaved, false));
				log.info("Voucher not found. Capture not processed.");
				throw new CaptureException("Voucher not found. Capture " + captureId + " not processed.");
			} else {

				VoucherDTOReturned voucherToProcess = modelMapper.map(voucherFound.get(), VoucherDTOReturned.class);

//				System.out.println("voucherToProcess: " + voucherToProcess);

				BigDecimal balance = voucherToProcess.getBalance();
				BigDecimal captureItemAmount = item.getCaptureAmount();
				BigDecimal newBalance = balance.subtract(captureItemAmount);
				// if final voucher balance < 0 save capture and captureItem with processed flags as false
				if (newBalance.compareTo(new BigDecimal(0)) < 0) {
					savedCapture.setReason("Not enough funds.");
					captureDAOService.saveCapture(savedCapture);
					captureItemDAOService.saveAllCaptureItems(setProcessedFlag(captureItemListSaved, false));
					log.info("Not enough funds. Capture not processed.");
					listOfSavedVoucherToProcess.clear();
					throw new CaptureException("Not enough funds. Capture " + captureId + " not processed.");
				} else {

					if (voucherFoundInList) {  //if voucher was already processed, change balance of voucher in list

						for (VoucherDTOReturned obj : listOfSavedVoucherToProcess) {
							if (obj.getVoucherCode().equals(item.getVoucherCode())) {
								obj.setBalance(newBalance);
								break;
							}
						}

					} else { // else add whole voucher to the list for save
						voucherToProcess.setBalance(newBalance);
						listOfSavedVoucherToProcess.add(voucherToProcess);
					}

				}
			}
		}
		// if everything OK save capture and captureItems with process flag true, save voucher with new balance

		voucherDOAService.saveAllVouchers(listOfSavedVoucherToProcess); //TODO: deactivate voucher when balance is 0 ?
		savedCapture.setReason("Capture id " + captureId + " processed.");
		savedCapture.setProcessed(true);

		captureDAOService.saveCapture(savedCapture);

		joinCaptureItemsWithVouchers(listOfSavedVoucherToProcess, captureItemListSaved);

		captureItemDAOService.saveAllCaptureItems(setProcessedFlag(captureItemListSaved, true));

		return InformationResponse.builder().info("Capture processed, capture ID:").id(savedCapture.getId().toString()).build();
	}

	private void joinCaptureItemsWithVouchers(ArrayList<VoucherDTOReturned> listOfSavedVoucherToProcess, ArrayList<CaptureItemDTOReturned> captureItemListSaved) {

		for (CaptureItemDTOReturned item : captureItemListSaved
		) {
			item.setVoucher(listOfSavedVoucherToProcess.stream()
					.filter(obj -> obj.getVoucherCode().equals(item.getVoucherCode()))
					.findFirst()
					.get()
			);
		}

	}

	@Override
	public CaptureDTOReturned getCapture(Long id) {

		Optional<CaptureDTOReturned> captureFound = captureDAOService.getCapture(id);

		if (captureFound.isEmpty()) {
			throw new CaptureNotFoundException("Capture not found.");
		} else {

			return captureFound.get();
		}
	}

	private VoucherDTOReturned compareVouchers(ArrayList<VoucherDTOReturned> listOfSavedVoucherToProcess, String voucherCode) {

		for (VoucherDTOReturned obj : listOfSavedVoucherToProcess) {
			if (obj.getVoucherCode().equals(voucherCode)) {
				return obj;
			}
		}
		return null;
	}

	private ArrayList<CaptureItemDTOReturned> setProcessedFlag(ArrayList<CaptureItemDTOReturned> captureItemListSaved, boolean flag) {

		for (CaptureItemDTOReturned item : captureItemListSaved
		) {
			item.setProcessed(flag);
		}
		return captureItemListSaved;
	}

	private ArrayList<CaptureItemDTOReturned> mapCaptureItemList(CaptureDTO captureDTO, CaptureDTOReturned savedCapture) {
		ArrayList<CaptureItemDTOReturned> captureItemList = new ArrayList<>();
		for (CaptureItemDTO item : captureDTO.getCaptureItemDTOS()
		) {
			CaptureItemDTOReturned captureItem = CaptureItemDTOReturned.builder()
					.capture(savedCapture)
					.captureAmount(item.getCaptureAmount())
					.voucherCode(item.getVoucherCode())
					.processed(false)
					.voucher(null)
					.build();
			captureItemList.add(captureItem);
		}

		return captureItemList;
	}

	private boolean isActionPossible(String action, boolean active) {

		if ((action.equals(ACTIVATE) && !active) || (action.equals(DEACTIVATE) && active)) {
			return true;
		} else {
			return false;
		}
	}

	private String createPattern() {
		char[] numbers = String.valueOf(System.currentTimeMillis()).toCharArray();

		StringBuilder pattern = new StringBuilder();

		for (int i = 0; i < numbers.length; i++
		) {
			pattern.append(numbers[i]);
			pattern.append("#");
			if (i % 2 == 0) {
				pattern.append("-");
			}
		}
		return pattern.toString();
	}

}
