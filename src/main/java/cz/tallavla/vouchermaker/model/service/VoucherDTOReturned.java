package cz.tallavla.vouchermaker.model.service;

import cz.tallavla.vouchermaker.model.repository.CaptureItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTOReturned {

	private Long id;
	private String voucherCode;
	private BigDecimal amount;
	private BigDecimal balance;
	private LocalDateTime expirationDate;
	private LocalDateTime createdDate;
	private boolean active;
	private List<CaptureItemDTOReturned> captureItemList;
}
