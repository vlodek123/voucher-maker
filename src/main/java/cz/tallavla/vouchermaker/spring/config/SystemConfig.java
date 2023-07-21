package cz.tallavla.vouchermaker.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConfig {

	private static String getBrandId() {
		String brand = null, tmp;
		if ((tmp = System.getProperty("configServiceBrand")) != null) {
			brand = tmp;
		} else if ((tmp = System.getProperty("brand")) != null) {
			brand = tmp;
		}
		return brand == null ? "netcom" : brand;
	}

	@Bean("brandId")
	public String brandId() {
		return getBrandId();
	}

}
