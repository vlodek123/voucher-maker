package cz.tallavla.vouchermaker.model.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@ApiModel(description = "ReturnCapture model Information")
public class ReturnCapture {

	@ApiModelProperty(value = "ReturnCapture id")
	private Long id;

	@ApiModelProperty(value = "ReturnCapture createdDate")
	private LocalDateTime createdDate;

	@ApiModelProperty(value = "ReturnCapture numberOfItems")
	private int numberOfItems;

	@ApiModelProperty(value = "ReturnCapture processed")
	private boolean processed;

	@ApiModelProperty(value = "ReturnCapture reason")
	private String reason;

	@ApiModelProperty(value = "ReturnCapture captureItems")
	private List<ReturnCaptureItem> captureItems;

	public static ReturnCaptureBuilder builder() {
		return new ReturnCaptureBuilder();
	}

	public ReturnCapture(Long id, LocalDateTime createdDate, int numberOfItems, boolean processed, String reason, List<ReturnCaptureItem> captureItems) {
		this.id = id;
		this.createdDate = createdDate;
		this.numberOfItems = numberOfItems;
		this.processed = processed;
		this.reason = reason;
		this.captureItems = captureItems;
	}

	public ReturnCapture() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<ReturnCaptureItem> getCaptureItems() {
		return captureItems;
	}

	public void setCaptureItems(List<ReturnCaptureItem> captureItems) {
		this.captureItems = captureItems;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ReturnCapture that = (ReturnCapture) o;

		if (numberOfItems != that.numberOfItems) return false;
		if (processed != that.processed) return false;
		if (!Objects.equals(id, that.id)) return false;
		if (!Objects.equals(createdDate, that.createdDate)) return false;
		if (!Objects.equals(reason, that.reason)) return false;
		return Objects.equals(captureItems, that.captureItems);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + numberOfItems;
		result = 31 * result + (processed ? 1 : 0);
		result = 31 * result + (reason != null ? reason.hashCode() : 0);
		result = 31 * result + (captureItems != null ? captureItems.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ReturnCapture{" +
				"id=" + id +
				", createdDate=" + createdDate +
				", numberOfItems=" + numberOfItems +
				", processed=" + processed +
				", reason='" + reason + '\'' +
				", captureItems=" + captureItems +
				'}';
	}

	public static class ReturnCaptureBuilder {
		private Long id;
		private LocalDateTime createdDate;
		private int numberOfItems;
		private boolean processed;
		private String reason;
		private List<ReturnCaptureItem> captureItems;

		ReturnCaptureBuilder() {
		}

		public ReturnCaptureBuilder id(final Long id) {
			this.id = id;
			return this;
		}

		public ReturnCaptureBuilder createdDate(final LocalDateTime createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public ReturnCaptureBuilder numberOfItems(final int numberOfItems) {
			this.numberOfItems = numberOfItems;
			return this;
		}

		public ReturnCaptureBuilder processed(final boolean processed) {
			this.processed = processed;
			return this;
		}

		public ReturnCaptureBuilder reason(final String reason) {
			this.reason = reason;
			return this;
		}

		public ReturnCaptureBuilder captureItems(final List<ReturnCaptureItem> captureItems) {
			this.captureItems = captureItems;
			return this;
		}

		public ReturnCapture build() {
			return new ReturnCapture(this.id, this.createdDate, this.numberOfItems, this.processed, this.reason, this.captureItems);
		}

		public String toString() {
			return "ReturnCapture.ReturnCaptureBuilder(id=" + this.id + ", createdDate=" + this.createdDate + ", numberOfItems=" + this.numberOfItems + ", processed=" + this.processed + ", reason=" + this.reason + ", captureItems=" + this.captureItems + ")";
		}
	}
}
