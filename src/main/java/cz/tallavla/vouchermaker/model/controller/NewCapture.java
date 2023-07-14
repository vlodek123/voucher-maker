package cz.tallavla.vouchermaker.model.controller;

import java.util.ArrayList;
import java.util.Objects;

public class NewCapture {

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
