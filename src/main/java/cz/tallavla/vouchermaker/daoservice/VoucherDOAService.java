package cz.tallavla.vouchermaker.daoservice;

import cz.tallavla.vouchermaker.model.repository.Voucher;
import cz.tallavla.vouchermaker.model.service.VoucherDTO;
import cz.tallavla.vouchermaker.model.service.VoucherDTOReturned;

import java.util.ArrayList;
import java.util.Optional;

public interface VoucherDOAService {

	<T> VoucherDTOReturned saveVoucher(T voucherForSave);

	Optional<VoucherDTOReturned> findVoucherByVoucherCode(String code);


	void saveAllVouchers(ArrayList<VoucherDTOReturned> listOfSavedVoucherToProcess);
}
