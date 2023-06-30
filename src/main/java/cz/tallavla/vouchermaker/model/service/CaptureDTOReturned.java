package cz.tallavla.vouchermaker.model.service;

import cz.tallavla.vouchermaker.model.repository.CaptureItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptureDTOReturned {

	private Long id;

	private LocalDateTime createdDate;

	private int numberOfItems;

	private boolean processed;

	private String reason;

	private List<CaptureItemDTOReturned> captureItemList;


}
