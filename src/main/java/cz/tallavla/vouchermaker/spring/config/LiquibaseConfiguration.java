package cz.tallavla.vouchermaker.spring.config;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnProperty(name = "liquibase.update.enabled", havingValue = "true")
public class LiquibaseConfiguration {

	@Autowired
	private SpringLiquibase liquibase;

	@Value("${spring.liquibase.enabled}")
	private boolean liquibaseEnabled;

	@PostConstruct
	public void applyLiquibaseUpdate() throws LiquibaseException {
		if (liquibaseEnabled) {
			// Set the changelog file to the master changelog
			liquibase.setChangeLog("classpath:changelog-master.xml");

			// Perform the Liquibase update
			liquibase.afterPropertiesSet();
		} else {
			// Liquibase is not enabled, handle this scenario if required
		}
	}
}