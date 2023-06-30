package cz.tallavla.vouchermaker.daoservice.impl;

import cz.tallavla.vouchermaker.daoservice.VoucherDOAService;
import cz.tallavla.vouchermaker.model.repository.Voucher;
import cz.tallavla.vouchermaker.model.service.VoucherDTO;
import cz.tallavla.vouchermaker.model.service.VoucherDTOReturned;
import cz.tallavla.vouchermaker.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoucherDOAServiceImpl implements VoucherDOAService {

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
