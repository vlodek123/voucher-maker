package cz.tallavla.vouchermaker.unit.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.tallavla.vouchermaker.mappers.Mappers;
import cz.tallavla.vouchermaker.model.controller.NewVoucher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Mappers Unit Test")
public class MappersUnitTest {

	@Autowired
	private Mappers mappers;

	ObjectMapper om = new ObjectMapper();

	@Test
	@DisplayName("Get Json String")
	public void getJsonString() {

		String expectedJson = "{\"amount\":\"1000\"}";
		NewVoucher source = new NewVoucher(new BigDecimal("1000"));

		var actual =  mappers.getJsonString(source);

		assertEquals(expectedJson, actual);
	}
}
