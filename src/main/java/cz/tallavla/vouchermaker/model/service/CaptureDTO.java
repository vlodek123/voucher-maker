package cz.tallavla.vouchermaker.model.service;

import java.util.ArrayList;
import java.util.Objects;

public class CaptureDTO {

	private ArrayList<CaptureItemDTO> captureItemDTOS;

	public CaptureDTO(ArrayList<CaptureItemDTO> captureItemDTOS) {
		this.captureItemDTOS = captureItemDTOS;
	}

	public CaptureDTO() {
	}

	public ArrayList<CaptureItemDTO> getCaptureItemDTOS() {
		return captureItemDTOS;
	}

	public void setCaptureItemDTOS(ArrayList<CaptureItemDTO> captureItemDTOS) {
		this.captureItemDTOS = captureItemDTOS;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CaptureDTO that = (CaptureDTO) o;

		return Objects.equals(captureItemDTOS, that.captureItemDTOS);
	}

	@Override
	public int hashCode() {
		return captureItemDTOS != null ? captureItemDTOS.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "CaptureDTO{" +
				"captureItemDTOS=" + captureItemDTOS +
				'}';
	}
}
