package cz.tallavla.vouchermaker.model.service;

import cz.tallavla.vouchermaker.model.controller.NewCaptureItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptureDTO {

	private ArrayList<CaptureItemDTO> captureItemList;
}
