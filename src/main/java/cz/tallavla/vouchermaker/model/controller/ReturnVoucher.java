package cz.tallavla.vouchermaker.model.controller;

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
public class ReturnVoucher {

	private String voucherCode;
	private BigDecimal amount;
	private BigDecimal balance;
	private LocalDateTime expirationDate;
	private boolean active;
}
