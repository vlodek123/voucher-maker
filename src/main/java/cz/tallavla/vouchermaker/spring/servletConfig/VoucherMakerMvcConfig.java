package cz.tallavla.vouchermaker.spring.servletConfig;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {
		"cz.talla.vouchermaker.controller"})
public class VoucherMakerMvcConfig implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		configurer.ignoreAcceptHeader(false);
	}
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for(HttpMessageConverter converter: converters) {
			if(converter instanceof MappingJackson2HttpMessageConverter) {
				ObjectMapper mapper = ((MappingJackson2HttpMessageConverter)converter).getObjectMapper();
				mapper.registerModule(new JavaTimeModule());
				mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
				mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
//				mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			}
		}
	}
}
