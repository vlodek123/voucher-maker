package cz.tallavla.vouchermaker.model.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Objects;

@ApiModel(description = "Capture model Information")
public class NewCapture {

	@ApiModelProperty(value = "Capture captureItems")
	private ArrayList<NewCaptureItem> captureItems;

	public NewCapture(ArrayList<NewCaptureItem> captureItems) {
		this.captureItems = captureItems;
	}

	public NewCapture() {
	}

	public ArrayList<NewCaptureItem> getCaptureItems() {
		return captureItems;
	}

	public void setCaptureItems(ArrayList<NewCaptureItem> captureItems) {
		this.captureItems = captureItems;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NewCapture that = (NewCapture) o;

		return Objects.equals(captureItems, that.captureItems);
	}

	@Override
	public int hashCode() {
		return captureItems != null ? captureItems.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "NewCapture{" +
				"captureItems=" + captureItems +
				'}';
	}
}
