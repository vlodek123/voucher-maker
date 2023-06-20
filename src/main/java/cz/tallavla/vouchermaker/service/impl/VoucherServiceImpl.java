package cz.tallavla.vouchermaker.service.impl;

import cz.tallavla.vouchermaker.config.CodeConfig;
import cz.tallavla.vouchermaker.exception.VoucherNotFoundException;
import cz.tallavla.vouchermaker.exception.WrongVoucherActionException;
import cz.tallavla.vouchermaker.mappers.Mappers;
import cz.tallavla.vouchermaker.model.controller.InformationResponse;
import cz.tallavla.vouchermaker.model.controller.ReturnVoucher;
import cz.tallavla.vouchermaker.model.controller.VoucherAction;
import cz.tallavla.vouchermaker.model.repository.Voucher;
import cz.tallavla.vouchermaker.model.service.VoucherDTO;
import cz.tallavla.vouchermaker.repository.VoucherRepository;
import cz.tallavla.vouchermaker.service.VoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

import static cz.tallavla.vouchermaker.utils.Constants.ACTIVATE;
import static cz.tallavla.vouchermaker.utils.Constants.DEACTIVATE;
import static cz.tallavla.vouchermaker.utils.VoucherCodeGenerator.generateCode;



@Service
@RequiredArgsConstructor
@Slf4j
public class VoucherServiceImpl implements VoucherService {

	@Autowired
	VoucherRepository voucherRepository;

	@Autowired
	Mappers mappers;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public ReturnVoucher createVoucher(BigDecimal amount) {

		String voucherCode = generateCode(new CodeConfig("TE", "NO", createPattern()));

		long addedMonths = 12;
		LocalDateTime expirationDate = LocalDateTime.now().plusMonths(addedMonths).truncatedTo(ChronoUnit.MILLIS);

		VoucherDTO newVoucherDTO = VoucherDTO.builder()
				.voucherCode(voucherCode)
				.amount(amount)
				.balance(amount)
				.expirationDate(expirationDate)
				.active(true)
				.build();

		Voucher createdVoucher = modelMapper.map(newVoucherDTO, Voucher.class);

		Voucher savedVoucher = voucherRepository.save(createdVoucher);

		return modelMapper.map(savedVoucher, ReturnVoucher.class);
	}

	@Override
	public ReturnVoucher getVoucher(String code) {

		Optional<Voucher> voucherFound = voucherRepository.findByVoucherCode(code);

		System.out.println(voucherFound);

		if (voucherFound.isEmpty()) {
			throw new VoucherNotFoundException("Voucher not found.");
		} else {
			return modelMapper.map(voucherFound.get(), ReturnVoucher.class);
		}

	}

	@Override
	public InformationResponse actVoucher(String code, String action) {

		log.info("voucher {} action: {}", code, action);

		Optional<Voucher> voucherForAction = voucherRepository.findByVoucherCode(code);

		System.out.println(voucherForAction);

		if (voucherForAction.isEmpty()) {
			throw new VoucherNotFoundException("Voucher not found.");
		}

		Voucher voucher = voucherForAction.get();

		if (!isActionPossible(action, voucher.isActive())) {
			throw new WrongVoucherActionException(String.format("Not possible to %s voucher %s", action, voucher.getVoucherCode()));
		}

		switch (action) {
			case ACTIVATE -> voucher.setActive(true);
			case DEACTIVATE -> voucher.setActive(false);
		}

		try {
			Voucher updatedVoucher = voucherRepository.save(voucher);
			return InformationResponse.builder().info(String.format("Voucher was %sD.", action)).voucherCode(updatedVoucher.getVoucherCode()).build();
		} catch (Exception e) {
			throw new WrongVoucherActionException(String.format("Not possible to %s voucher %s, persistence error", action, voucher.getVoucherCode()));
		}

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

		for (int i = 0; i <numbers.length; i++
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
