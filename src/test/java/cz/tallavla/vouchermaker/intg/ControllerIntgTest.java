package cz.tallavla.vouchermaker.intg;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.tallavla.vouchermaker.VoucherMakerApplication;
import cz.tallavla.vouchermaker.model.controller.NewCapture;
import cz.tallavla.vouchermaker.model.controller.NewCaptureItem;
import cz.tallavla.vouchermaker.model.controller.NewVoucher;
import cz.tallavla.vouchermaker.model.controller.VoucherAction;
import cz.tallavla.vouchermaker.model.repository.Capture;
import cz.tallavla.vouchermaker.model.repository.CaptureItem;
import cz.tallavla.vouchermaker.model.repository.Voucher;
import cz.tallavla.vouchermaker.repository.CaptureItemRepository;
import cz.tallavla.vouchermaker.repository.CaptureRepository;
import cz.tallavla.vouchermaker.repository.VoucherRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = VoucherMakerApplication.class)
@AutoConfigureMockMvc
@Transactional
public class ControllerIntgTest {

	private static final String VOUCHER_URL = "/voucher";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private VoucherRepository voucherRepository;

	@Autowired
	private CaptureRepository captureRepository;

	@Autowired
	private CaptureItemRepository captureItemRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		var voucherCode = "TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO1";
		var amount = new BigDecimal("1000");
		LocalDateTime expDate = LocalDateTime.now().plusMonths(5).truncatedTo(ChronoUnit.MILLIS);
		Voucher voucher = Voucher.builder()
				.amount(amount)
				.balance(amount)
				.active(true)
				.voucherCode(voucherCode)
				.expirationDate(expDate)
				.captureItemList(null)
				.build();

		Voucher returnedVoucher = voucherRepository.save(voucher);

		CaptureItem captureItem = CaptureItem.builder()
				.processed(false)
				.captureAmount(new BigDecimal("100"))
				.voucherCode("TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO1")
				.voucher(returnedVoucher)
				.build();

		CaptureItem returnedCaptureItem = captureItemRepository.save(captureItem);

		Capture capture = Capture.builder()
				.reason("Not Processed.")
				.numberOfItems(1)
				.processed(false)
				.captureItemList(new ArrayList<>(List.of(returnedCaptureItem)))
				.build();

		captureRepository.save(capture);
		returnedVoucher.setCaptureItemList(new ArrayList<>(List.of(returnedCaptureItem)));
		voucherRepository.save(returnedVoucher);
	}

	@Test
	@DisplayName("Create Voucher Intg Test")
	@DirtiesContext
	public void createVoucher() throws Exception {

		NewVoucher newVoucher = NewVoucher.builder()
				.amount("1000")
				.build();

		mvc.perform(post(VOUCHER_URL + "/new")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(newVoucher)))
				.andExpect(status().isCreated())
				.andReturn();

		Voucher voucher = voucherRepository.getReferenceById(10002L);

		var bigDecNumber = new BigDecimal("1000");

		assertAll("Create VOucher Integration TEST",
				() -> assertEquals(10002L, voucher.getId()),
				() -> assertEquals(bigDecNumber, voucher.getAmount()),
				() -> assertEquals(bigDecNumber, voucher.getBalance())
		);
	}

	@Test
	@DisplayName("Get Voucher Intg Test")
	@DirtiesContext
	public void getVoucher() throws Exception {
		var voucherCode = "TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO1";

		VoucherAction voucherAction = VoucherAction.builder()
				.action("DEACTIVATE")
				.build();

		MvcResult result = mvc.perform(get(VOUCHER_URL + "/{code}", voucherCode))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(10001)))
				.andExpect(jsonPath("$.balance", is(1000)))
				.andExpect(jsonPath("$.active", is(true)))
				.andReturn();
	}

	@Test
	@DisplayName("Voucher Action Intg Test")
	@DirtiesContext
	public void voucherAction() throws Exception {

		var voucherCode = "TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO1";

		VoucherAction voucherAction = VoucherAction.builder()
				.action("DEACTIVATE")
				.build();

		MvcResult result = mvc.perform(patch(VOUCHER_URL + "/{code}/action", voucherCode)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(voucherAction)))
				.andExpect(status().isAccepted())
				.andExpect(jsonPath("$.info", is("Voucher was DEACTIVATED.")))
				.andExpect(jsonPath("$.id", is("TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO1")))
				.andReturn();

	}

	@Test
	@DisplayName("Capture Action Intg Test")
	@DirtiesContext
	public void captureAction() throws Exception {

		var voucherCode = "TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO1";

		NewCapture newCapture = NewCapture.builder()
				.captureItemList(new ArrayList<>(List.of(NewCaptureItem.builder()
						.voucherCode(voucherCode)
						.captureAmount("150")
						.build()
				)))
				.build();

		MvcResult result = mvc.perform(post(VOUCHER_URL + "/capture")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(newCapture)))
				.andExpect(status().isAccepted())
				.andExpect(jsonPath("$.info", is("Capture processed, capture ID:")))
				.andExpect(jsonPath("$.id", is("20002")))
				.andReturn();

	}

	@Test
	@DisplayName("Get Capture Intg Test")
	@DirtiesContext
	public void getCapture() throws Exception {

		var voucherCode = "TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO1";


		MvcResult result = mvc.perform(get(VOUCHER_URL + "/capture/{id}", 20001L))
				.andExpect(status().isAccepted())
				.andExpect(jsonPath("$.id", is(20001)))
				.andExpect(jsonPath("$.numberOfItems", is(1)))
				.andExpect(jsonPath("$.processed", is(false)))
				.andReturn();

	}

}
