package cz.tallavla.vouchermaker.model.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

	private String voucherCode;
	private BigDecimal amount;
	private BigDecimal balance;
	private LocalDateTime expirationDate;
	private boolean active;
}
