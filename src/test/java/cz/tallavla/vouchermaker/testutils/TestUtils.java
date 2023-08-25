package cz.tallavla.vouchermaker.testutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.tallavla.vouchermaker.controller.VoucherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class TestUtils {

	private final static Logger log = LoggerFactory.getLogger(VoucherController.class);
	public static <T> T readJsonFileToClassObject(String url, Class<T> parameterClass)  {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()){
			Files.copy(new ClassPathResource(url).getFile().toPath(), bos);
			String json = bos.toString();
			return objectMapper.readValue(json, parameterClass);
		} catch (IOException e) {
			log.info("FILE not read properly");
			e.printStackTrace();
			return null;
		}
	}

}
