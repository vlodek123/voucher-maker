package cz.tallavla.vouchermaker.unit.service;

import cz.tallavla.vouchermaker.daoservice.CaptureDAOService;
import cz.tallavla.vouchermaker.daoservice.CaptureItemDAOService;
import cz.tallavla.vouchermaker.daoservice.VoucherDOAService;
import cz.tallavla.vouchermaker.exception.VoucherNotFoundException;
import cz.tallavla.vouchermaker.exception.WrongVoucherActionException;
import cz.tallavla.vouchermaker.model.service.CaptureDTO;
import cz.tallavla.vouchermaker.model.service.CaptureDTOReturned;
import cz.tallavla.vouchermaker.model.service.CaptureItemDTOReturned;
import cz.tallavla.vouchermaker.model.service.VoucherDTOReturned;
import cz.tallavla.vouchermaker.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cz.tallavla.vouchermaker.testutils.TestUtils.readJsonFileToClassObject;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Voucher Service Unit Test")
@Slf4j
public class VoucherServiceUnitTest {

	@Autowired
	private VoucherService voucherService;

	@MockBean
	private VoucherDOAService voucherDAOServiceMock;

	@MockBean
	private CaptureItemDAOService captureItemDAOServiceMock;

	@MockBean
	private CaptureDAOService captureDAOServiceMock;

	@Test
	@DisplayName("Create Voucher")
	public void createVoucher() {

		VoucherDTOReturned response = readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class);

		when(voucherDAOServiceMock.saveVoucher(any())).thenReturn(response);

		var actual = voucherService.createVoucher(new BigDecimal("1000"));

		assertAll("Create Voucher TEST",
				() -> assertEquals(new BigDecimal("2000.00"), actual.getAmount()),
				() -> assertEquals(10001L, actual.getId()),
				() -> assertEquals(new BigDecimal("1750.00"), actual.getBalance())
				);
	}

	@Test
	@DisplayName("Get Voucher Not Found")
	public void getVoucher_404() {
		var voucherCode = "abc";
		Optional<VoucherDTOReturned> response = Optional.empty();

		when(voucherDAOServiceMock.findVoucherByVoucherCode(anyString())).thenReturn(response);

		try {
			var actual = voucherService.getVoucher(voucherCode);
		} catch (VoucherNotFoundException ex) {
			assertEquals("Voucher not found.", ex.getMessage());
		}

	}

	@Test
	@DisplayName("Get Voucher")
	public void getVoucher() {
		var voucherCode = "abc";
		Optional<VoucherDTOReturned> response = Optional.ofNullable(readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class));

		when(voucherDAOServiceMock.findVoucherByVoucherCode(anyString())).thenReturn(response);

		var actual = voucherService.getVoucher(voucherCode);

		assertAll("Create Voucher TEST",
				() -> assertEquals(new BigDecimal("2000.00"), actual.getAmount()),
				() -> assertEquals(10001L, actual.getId()),
				() -> assertEquals(new BigDecimal("1750.00"), actual.getBalance())
		);
	}

	@Test
	@DisplayName("Act Voucher Not Found")
	public void actVoucher_404() {
		var voucherCode = "abc";
		Optional<VoucherDTOReturned> response = Optional.empty();

		when(voucherDAOServiceMock.findVoucherByVoucherCode(anyString())).thenReturn(response);

		try {
			var actual = voucherService.getVoucher(voucherCode);
		} catch (VoucherNotFoundException ex) {
			assertEquals("Voucher not found.", ex.getMessage());
		}
	}

	@Test
	@DisplayName("Act Voucher Action Set")
	public void actVoucher_Action_Set() {
		var voucherCode = "abc";
		var action = "ACTIVATE";
		Optional<VoucherDTOReturned> response = Optional.ofNullable(readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class));

		when(voucherDAOServiceMock.findVoucherByVoucherCode(anyString())).thenReturn(response);

		try {
			var actual = voucherService.actVoucher(voucherCode, action);
		} catch (WrongVoucherActionException ex) {
			assertEquals("Not possible to ACTIVATE voucher TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO", ex.getMessage());
		}
	}

	@Test
	@DisplayName("Act Voucher Wrong Action")
	public void actVoucher_Wrong_Action() {
		var voucherCode = "abc";
		var action = "WRONG";
		Optional<VoucherDTOReturned> response = Optional.ofNullable(readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class));

		when(voucherDAOServiceMock.findVoucherByVoucherCode(anyString())).thenReturn(response);

		try {
			var actual = voucherService.actVoucher(voucherCode, action);
		} catch (WrongVoucherActionException ex) {
			assertEquals("Not possible to WRONG voucher TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO", ex.getMessage());
		}
	}

	@Test
	@DisplayName("Act Voucher Persistence Error")
	public void actVoucher_Persistence_Error() {
		var voucherCode = "abc";
		var action = "DEACTIVATE";
		Optional<VoucherDTOReturned> response = Optional.ofNullable(readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class));

		when(voucherDAOServiceMock.findVoucherByVoucherCode(anyString())).thenReturn(response);

		when(voucherDAOServiceMock.saveVoucher(any())).thenThrow(WrongVoucherActionException.class);

		assertThrows(WrongVoucherActionException.class, () -> voucherService.actVoucher(voucherCode, action));
	}

	@Test
	@DisplayName("Act Voucher")
	public void actVoucher() {
		var voucherCode = "abc";
		var action = "DEACTIVATE";
		Optional<VoucherDTOReturned> response = Optional.ofNullable(readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class));

		when(voucherDAOServiceMock.findVoucherByVoucherCode(anyString())).thenReturn(response);

		when(voucherDAOServiceMock.saveVoucher(any())).thenReturn(response.get());

		var actual = voucherService.actVoucher(voucherCode, action);

		assertAll("Act Voucher TEST",
				() -> assertEquals("Voucher was DEACTIVATED.", actual.getInfo()),
				() -> assertEquals("TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO", actual.getId())
		);
	}

	@Test
	@DisplayName("Act Capture")
	public void actCapture() {
		CaptureDTO captureDTO = readJsonFileToClassObject("/payloads/CaptureDTO.json", CaptureDTO.class);

		CaptureDTOReturned savedCapture = readJsonFileToClassObject("/payloads/CaptureDTOReturned.json", CaptureDTOReturned.class);

		ArrayList<CaptureItemDTOReturned> captureItemsSaved = new ArrayList<>(List.of(
				CaptureItemDTOReturned.builder()
					.id(30001L)
					.voucherCode("TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO")
					.captureAmount(new BigDecimal("100.00"))
					.processed(true)
					.capture(savedCapture)
					.voucher(null)
					.build(),
				CaptureItemDTOReturned.builder()
						.id(30002L)
						.voucherCode("TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO")
						.captureAmount(new BigDecimal("150.00"))
						.processed(true)
						.capture(savedCapture)
						.voucher(null)
						.build()
		));

		Optional<VoucherDTOReturned> response = Optional.ofNullable(readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class));

		when(captureDAOServiceMock.saveCapture(any())).thenReturn(savedCapture);
		when(voucherDAOServiceMock.findVoucherByVoucherCode(anyString())).thenReturn(response);
		when(captureItemDAOServiceMock.saveAllCaptureItems(any())).thenReturn(captureItemsSaved);

		doNothing().when(voucherDAOServiceMock).saveAllVouchers(any());

		var actual = voucherService.actCapture(captureDTO);

		assertAll("Act Voucher TEST",
				() -> assertEquals("Capture processed, capture ID:", actual.getInfo()),
				() -> assertEquals("20001", actual.getId())
		);
	}

	@Test
	@DisplayName("Get Capture")
	public void getCapture() {
		CaptureDTO captureDTO = readJsonFileToClassObject("/payloads/CaptureDTO.json", CaptureDTO.class);

		Optional<CaptureDTOReturned> savedCapture = Optional.ofNullable(readJsonFileToClassObject("/payloads/CaptureDTOReturned.json", CaptureDTOReturned.class));

		when(captureDAOServiceMock.getCapture(any())).thenReturn(savedCapture);

		var actual = voucherService.getCapture(20001L);

		assertAll("Get Voucher TEST",
				() -> assertTrue(actual.isProcessed()),
				() -> assertEquals(20001L, actual.getId())
		);
	}
}
