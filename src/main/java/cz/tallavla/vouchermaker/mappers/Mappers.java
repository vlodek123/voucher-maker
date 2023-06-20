package cz.tallavla.vouchermaker.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class Mappers {

	ObjectMapper om = new ObjectMapper();

	public <T> String getJsonString(T source) {
		try {
			return om.writeValueAsString(source);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
