//package cz.tallavla.vouchermaker.unit.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import cz.tallavla.vouchermaker.exception.VoucherNotFoundException;
//import cz.tallavla.vouchermaker.model.controller.InformationResponse;
//import cz.tallavla.vouchermaker.model.controller.NewCapture;
//import cz.tallavla.vouchermaker.model.controller.NewVoucher;
//import cz.tallavla.vouchermaker.model.controller.VoucherAction;
//import cz.tallavla.vouchermaker.model.service.CaptureDTO;
//import cz.tallavla.vouchermaker.model.service.CaptureDTOReturned;
//import cz.tallavla.vouchermaker.model.service.VoucherDTOReturned;
//import cz.tallavla.vouchermaker.service.VoucherService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//
//import static cz.tallavla.vouchermaker.testutils.TestUtils.readJsonFileToClassObject;
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@DisplayName("Controller Unit Test")
//public class ControllerUnitTest {
//
//	private static final String VOUCHER_URL = "/voucher";
//
//	@Autowired
//	private MockMvc mvc;
//
//	@MockBean
//	private VoucherService voucherServiceMOck;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//
//	@Test
//	@DisplayName("GET Voucher Not Found")
//	public void getVoucherNotFound() throws Exception {
//		//given
//		var voucherCode = "TE1R-6K8D-7I9X-5O4L-4N0Y-2R0H-7Q1Q-NO";
//		VoucherDTOReturned response = readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class);
//
//		when(voucherServiceMOck.getVoucher(any())).thenThrow(new VoucherNotFoundException("Voucher not found."));
//
//		MvcResult result = mvc.perform(get(VOUCHER_URL + "/{code}", voucherCode))
//				.andExpect(status().isNotFound())
//				.andExpect(jsonPath("$.message", is("Voucher not found.")))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("GET Voucher")
//	public void getVoucher() throws Exception {
//
//		var voucherCode = "TE1R-6K8D-7I9X-5O4L-4N0Y-2R0H-7Q1Q-NO";
//		VoucherDTOReturned response = readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class);
//
//		when(voucherServiceMOck.getVoucher(any())).thenReturn(response);
//
//		MvcResult result = mvc.perform(get(VOUCHER_URL + "/{code}", voucherCode))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.id", is(10001)))
//				.andExpect(jsonPath("$.balance", is(1750.0)))
//				.andExpect(jsonPath("$.active", is(true)))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("POST Voucher")
//	public void createVoucher() throws Exception {
//		var amount = new BigDecimal("1000");
//		NewVoucher newVoucher = NewVoucher.builder()
//				.amount("1000")
//				.build();
//
//		VoucherDTOReturned response = readJsonFileToClassObject("/payloads/VoucherDTOReturned.json", VoucherDTOReturned.class);
//		assert response != null;
//		response.setCaptureItemList(new ArrayList<>());
//
//		when(voucherServiceMOck.createVoucher(any())).thenReturn(response);
//
//		MvcResult result = mvc.perform(post(VOUCHER_URL + "/new")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(newVoucher)))
//				.andExpect(status().isCreated())
//				.andExpect(jsonPath("$.id", is(10001)))
//				.andExpect(jsonPath("$.balance", is(1750.0)))
//				.andExpect(jsonPath("$.active", is(true)))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("POST Voucher Wrong Amount")
//	public void createVoucherWrongAmount() throws Exception {
//		var amount = new BigDecimal("1000");
//		NewVoucher newVoucher = NewVoucher.builder()
//				.amount("XXXX")
//				.build();
//
//		MvcResult result = mvc.perform(post(VOUCHER_URL + "/new")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(newVoucher)))
//				.andExpect(status().isBadRequest())
//				.andExpect(jsonPath("$.message", is("Wrong format of amount.")))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("PATCH Voucher Action Missing Action")
//	public void voucherActionMissingAction() throws Exception {
//		var voucherCode = "TE1R-6K8D-7I9X-5O4L-4N0Y-2R0H-7Q1Q-NO";
//		VoucherAction voucherAction = VoucherAction.builder()
//				.action(null)
//				.build();
//
//		MvcResult result = mvc.perform(patch(VOUCHER_URL + "/{code}/action", voucherCode)
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(voucherAction)))
//				.andExpect(status().isBadRequest())
//				.andExpect(jsonPath("$.message", is("Invalid request for voucher action")))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("PATCH Voucher Action Wrong Action")
//	public void voucherActionWrongAction() throws Exception {
//		var voucherCode = "TE1R-6K8D-7I9X-5O4L-4N0Y-2R0H-7Q1Q-NO";
//		VoucherAction voucherAction = VoucherAction.builder()
//				.action("WRONG")
//				.build();
//
//		MvcResult result = mvc.perform(patch(VOUCHER_URL + "/{code}/action", voucherCode)
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(voucherAction)))
//				.andExpect(status().isBadRequest())
//				.andExpect(jsonPath("$.message", is("Unsupported action from client: Only 'ACTIVATE' or 'DEACTIVATE' actions are supported.")))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("PATCH Voucher Action")
//	public void voucherAction() throws Exception {
//		var action = "DEACTIVATE";
//		var voucherCode = "TE1R-6K8D-7I9X-5O4L-4N0Y-2R0H-7Q1Q-NO";
//		VoucherAction voucherAction = VoucherAction.builder()
//				.action(action)
//				.build();
//
//		var response = InformationResponse.builder().info(String.format("Voucher was %sD.", action)).id(voucherCode).build();
//
//		when(voucherServiceMOck.actVoucher(anyString(), anyString())).thenReturn(response);
//
//		MvcResult result = mvc.perform(patch(VOUCHER_URL + "/{code}/action", voucherCode)
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(voucherAction)))
//				.andExpect(status().isAccepted())
//				.andExpect(jsonPath("$.info", is("Voucher was DEACTIVATED.")))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("POST Capture")
//	public void captureAction() throws Exception {
//
//		NewCapture capture = readJsonFileToClassObject("/payloads/CaptureDTO.json", NewCapture.class);
//
//		var response = InformationResponse.builder().info("Capture processed, capture ID:").id("20001").build();
//
//		when(voucherServiceMOck.actCapture(any())).thenReturn(response);
//
//		MvcResult result = mvc.perform(post(VOUCHER_URL + "/capture")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(capture)))
//				.andExpect(status().isAccepted())
//				.andExpect(jsonPath("$.info", is("Capture processed, capture ID:")))
//				.andReturn();
//	}
//
//	@Test
//	@DisplayName("GET Capture")
//	public void getCapture() throws Exception {
//		var id = "20001";
//		CaptureDTOReturned capture = readJsonFileToClassObject("/payloads/CaptureDTOReturned.json", CaptureDTOReturned.class);
//
//		when(voucherServiceMOck.getCapture(any())).thenReturn(capture);
//
//		assert capture != null;
//		MvcResult result = mvc.perform(get(VOUCHER_URL + "/capture/{id}", id))
//				.andExpect(status().isAccepted())
//				.andExpect(jsonPath("$.id", is(20001)))
//				.andExpect(jsonPath("$.captureItemList.size()", is(capture.getCaptureItemList().size())))
//				.andReturn();
//	}
//
//}
