package cz.tallavla.vouchermaker.model.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnCapture {

	private Long id;

	private LocalDateTime createdDate;


	private int numberOfItems;


	private boolean processed;

	private String reason;

	private List<ReturnCaptureItem> captureItemList;
}
