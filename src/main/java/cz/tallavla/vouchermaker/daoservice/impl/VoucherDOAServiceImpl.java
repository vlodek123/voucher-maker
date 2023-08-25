package cz.tallavla.vouchermaker.daoservice.impl;

import cz.tallavla.vouchermaker.controller.VoucherController;
import cz.tallavla.vouchermaker.daoservice.VoucherDOAService;
import cz.tallavla.vouchermaker.model.repository.Voucher;
import cz.tallavla.vouchermaker.model.service.VoucherDTOReturned;
import cz.tallavla.vouchermaker.repository.VoucherRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service

public class VoucherDOAServiceImpl implements VoucherDOAService {

	private final static Logger log = LoggerFactory.getLogger(VoucherController.class);
	@Autowired
	VoucherRepository voucherRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public <T> VoucherDTOReturned saveVoucher(T voucherForSave) {
		return modelMapper.map(voucherRepository.save(modelMapper.map(voucherForSave, Voucher.class)),VoucherDTOReturned.class);
	}


	@Override
	public Optional<VoucherDTOReturned> findVoucherByVoucherCode(String code) {

		Optional<Voucher> voucherFound = voucherRepository.findByVoucherCode(code);

		return voucherFound.map(voucher -> modelMapper.map(voucher, VoucherDTOReturned.class));


	}

	@Override
	public void saveAllVouchers(ArrayList<VoucherDTOReturned> listOfSavedVoucherToProcess) {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.createTypeMap(VoucherDTOReturned.class, Voucher.class);

		voucherRepository.saveAll(modelMapper.map(listOfSavedVoucherToProcess, new TypeToken<ArrayList<Voucher>>() {}.getType()));
	}
}
