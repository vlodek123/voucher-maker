package cz.tallavla.vouchermaker.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.tallavla.vouchermaker.exception.VoucherNotFoundException;
import cz.tallavla.vouchermaker.model.controller.InformationResponse;
import cz.tallavla.vouchermaker.model.controller.NewCapture;
import cz.tallavla.vouchermaker.model.controller.NewVoucher;
import cz.tallavla.vouchermaker.model.controller.VoucherAction;
import cz.tallavla.vouchermaker.model.service.CaptureDTOReturned;
import cz.tallavla.vouchermaker.model.service.VoucherDTOReturned;
import cz.tallavla.vouchermaker.service.VoucherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.ArrayList;

import static cz.tallavla.vouchermaker.testutils.TestUtils.readJsonFileToClassObject;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Controller Unit Test")
@DirtiesContext
public class ControllerUnitTest {

	private static final String VOUCHER_URL = "/vouchermaker";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private VoucherService voucherServiceMOck;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	@DisplayName("GET Voucher Not Found")
	public void getVoucherNotFound() throws Exception {
		//given
		var voucherCode = "TE1R-6K8D-7I9X-5O4L-4N0Y-2R0H-7Q1Q-NO";
		VoucherDTOReturned response = readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class);

		when(voucherServiceMOck.getVoucher(any())).thenThrow(new VoucherNotFoundException("Voucher not found."));

		MvcResult result = mvc.perform(get(VOUCHER_URL + "/vouchers/{code}", voucherCode))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Voucher not found.")))
				.andReturn();
	}

	@Test
	@DisplayName("GET Voucher")
	public void getVoucher() throws Exception {

		var voucherCode = "TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO";
		VoucherDTOReturned response = readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class);

		when(voucherServiceMOck.getVoucher(any())).thenReturn(response);

		MvcResult result = mvc.perform(get(VOUCHER_URL + "/vouchers/{code}", voucherCode))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.voucherCode", is("TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO")))
				.andExpect(jsonPath("$.balance", is(1750.0)))
				.andExpect(jsonPath("$.active", is(true)))
				.andReturn();
	}

	@Test
	@DisplayName("POST Voucher")
	public void createVoucher() throws Exception {
		var amount = new BigDecimal("1000");
		NewVoucher newVoucher = new NewVoucher(new BigDecimal(1000));

		VoucherDTOReturned response = readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class);
		assert response != null;
		response.setCaptureItems(new ArrayList<>());

		when(voucherServiceMOck.createVoucher(any())).thenReturn(response);

		MvcResult result = mvc.perform(post(VOUCHER_URL + "/vouchers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newVoucher)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.voucherCode", is("TE1B-6P8R-7P8B-6L1Z-1G5B-6G1U-5G3R-NO")))
				.andExpect(jsonPath("$.balance", is(1750.0)))
				.andExpect(jsonPath("$.active", is(true)))
				.andReturn();
	}

	@Test
	@DisplayName("POST Voucher Wrong Amount")
	public void createVoucherWrongAmount() throws Exception {
		var amount = new BigDecimal("1000");

		MvcResult result = mvc.perform(post(VOUCHER_URL + "/vouchers")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"amount\": \"XXXX\" }"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.type", is("WRONG FORMAT")))
				.andReturn();
	}

	@Test
	@DisplayName("PATCH Voucher Action Missing Action")
	public void voucherActionMissingAction() throws Exception {
		var voucherCode = "TE1R-6K8D-7I9X-5O4L-4N0Y-2R0H-7Q1Q-NO";

		MvcResult result = mvc.perform(patch(VOUCHER_URL + "/vouchers/{code}/action", voucherCode)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"amount\": null }"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("Invalid request for voucher action")))
				.andReturn();
	}

	@Test
	@DisplayName("PATCH Voucher Action Wrong Action")
	public void voucherActionWrongAction() throws Exception {
		var voucherCode = "TE1R-6K8D-7I9X-5O4L-4N0Y-2R0H-7Q1Q-NO";

		MvcResult result = mvc.perform(patch(VOUCHER_URL + "/vouchers/{code}/action", voucherCode)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"action\": \"WRONG\" }"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("Unsupported action from client: Only 'ACTIVATE' or 'DEACTIVATE' actions are supported.")))
				.andReturn();
	}

	@Test
	@DisplayName("PATCH Voucher Action")
	public void voucherAction() throws Exception {
		var action = "DEACTIVATE";
		var voucherCode = "TE1R-6K8D-7I9X-5O4L-4N0Y-2R0H-7Q1Q-NO";
		VoucherAction voucherAction = new VoucherAction(action);

		var response = InformationResponse.builder().info(String.format("Voucher was %sD.", action)).id(voucherCode).build();

		when(voucherServiceMOck.actVoucher(anyString(), anyString())).thenReturn(response);

		MvcResult result = mvc.perform(patch(VOUCHER_URL + "/vouchers/{code}/action", voucherCode)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(voucherAction)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.info", is("Voucher was DEACTIVATED.")))
				.andReturn();
	}

	@Test
	@DisplayName("POST Capture")
	public void captureAction() throws Exception {

		NewCapture capture = readJsonFileToClassObject("/payloads/NewCapture.json", NewCapture.class);

		var response = InformationResponse.builder().info("Capture processed, capture ID:").id("20001").build();

		when(voucherServiceMOck.actCapture(any())).thenReturn(response);

		MvcResult result = mvc.perform(post(VOUCHER_URL + "/captures")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(capture)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.info", is("Capture processed, capture ID:")))
				.andReturn();
	}

	@Test
	@DisplayName("GET Capture")
	public void getCapture() throws Exception {
		var id = "20001";
		CaptureDTOReturned capture = readJsonFileToClassObject("/payloads/CaptureDTOReturned.json", CaptureDTOReturned.class);

		when(voucherServiceMOck.getCapture(any())).thenReturn(capture);

		assert capture != null;
		MvcResult result = mvc.perform(get(VOUCHER_URL + "/captures/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(20001)))
				.andExpect(jsonPath("$.captureItems.size()", is(capture.getCaptureItems().size())))
				.andReturn();
	}

}
