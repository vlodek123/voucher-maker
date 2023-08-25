package cz.tallavla.vouchermaker.unit.daoservice;

import cz.tallavla.vouchermaker.controller.VoucherController;
import cz.tallavla.vouchermaker.daoservice.VoucherDOAService;
import cz.tallavla.vouchermaker.model.repository.Voucher;
import cz.tallavla.vouchermaker.model.service.VoucherDTO;
import cz.tallavla.vouchermaker.model.service.VoucherDTOReturned;
import cz.tallavla.vouchermaker.repository.VoucherRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cz.tallavla.vouchermaker.testutils.TestUtils.readJsonFileToClassObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Voucher DAOService Unit Test")
@DirtiesContext
public class VoucherDOAServiceUnitTest {

	private final static Logger log = LoggerFactory.getLogger(VoucherController.class);
	@Autowired
	private VoucherDOAService voucherDOAService;

	@MockBean
	private VoucherRepository voucherRepositoryMock;


	@Test
	@DisplayName("Save Voucher")
	public void saveVoucher() {

		var amount = new BigDecimal("1000.00");
		VoucherDTO voucherDTO = VoucherDTO.builder()
				.voucherCode("abc")
				.amount(amount)
				.balance(amount)
				.build();

		Voucher voucher = Voucher.builder()
				.id(10001L)
				.voucherCode("abc")
				.amount(amount)
				.balance(amount)
				.build();

		when(voucherRepositoryMock.save(any())).thenReturn(voucher);

		var actual =  voucherDOAService.saveVoucher(voucherDTO);

		assertEquals(10001L, actual.getId());

		verify(voucherRepositoryMock, times(1)).save(any());
		verifyNoMoreInteractions(voucherRepositoryMock);
	}

	@Test
	@DisplayName("Get Voucher By VoucherCode")
	public void findVoucherByVoucherCode() {
		Optional<Voucher> voucherFound = Optional.ofNullable(readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", Voucher.class));

		when(voucherRepositoryMock.findByVoucherCode(anyString())).thenReturn(voucherFound);

		var actual = voucherDOAService.findVoucherByVoucherCode("TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO");

		assert actual.isPresent();
		assertEquals(10001L, actual.get().getId());
	}

	@Test
	@DisplayName("Save All Vouchers")
	public void saveAllVouchers() {
		VoucherDTOReturned voucherFound = readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class);

		assert voucherFound != null;
		ArrayList<VoucherDTOReturned> listOfSavedVoucherToProcess = new ArrayList<>(List.of(voucherFound,voucherFound));

		when(voucherRepositoryMock.saveAll(any())).thenReturn(new ArrayList<>(List.of(new Voucher())));

		voucherDOAService.saveAllVouchers(listOfSavedVoucherToProcess);

		verify(voucherRepositoryMock, times(1)).saveAll(any());
		verifyNoMoreInteractions(voucherRepositoryMock);

	}

}
