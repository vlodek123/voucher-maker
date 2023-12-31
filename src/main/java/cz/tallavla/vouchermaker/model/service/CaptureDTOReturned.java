package cz.tallavla.vouchermaker.model.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CaptureDTOReturned {

	private Long id;

	private LocalDateTime createdDate;

	private int numberOfItems;

	private boolean processed;

	private String reason;

	private List<CaptureItemDTOReturned> captureItems;

	public static CaptureDTOReturnedBuilder builder() {
		return new CaptureDTOReturnedBuilder();
	}

	public CaptureDTOReturned() {
	}

	public CaptureDTOReturned(Long id, LocalDateTime createdDate, int numberOfItems, boolean processed, String reason, List<CaptureItemDTOReturned> captureItems) {
		this.id = id;
		this.createdDate = createdDate;
		this.numberOfItems = numberOfItems;
		this.processed = processed;
		this.reason = reason;
		this.captureItems = captureItems;
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

	public List<CaptureItemDTOReturned> getCaptureItems() {
		return captureItems;
	}

	public void setCaptureItems(List<CaptureItemDTOReturned> captureItems) {
		this.captureItems = captureItems;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CaptureDTOReturned that = (CaptureDTOReturned) o;

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
	public static class CaptureDTOReturnedBuilder {
		private Long id;
		private LocalDateTime createdDate;
		private int numberOfItems;
		private boolean processed;
		private String reason;
		private List<CaptureItemDTOReturned> captureItems;

		CaptureDTOReturnedBuilder() {
		}

		public CaptureDTOReturnedBuilder id(final Long id) {
			this.id = id;
			return this;
		}

		public CaptureDTOReturnedBuilder createdDate(final LocalDateTime createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public CaptureDTOReturnedBuilder numberOfItems(final int numberOfItems) {
			this.numberOfItems = numberOfItems;
			return this;
		}

		public CaptureDTOReturnedBuilder processed(final boolean processed) {
			this.processed = processed;
			return this;
		}

		public CaptureDTOReturnedBuilder reason(final String reason) {
			this.reason = reason;
			return this;
		}

		public CaptureDTOReturnedBuilder captureItems(final List<CaptureItemDTOReturned> captureItems) {
			this.captureItems = captureItems;
			return this;
		}

		public CaptureDTOReturned build() {
			return new CaptureDTOReturned(this.id, this.createdDate, this.numberOfItems, this.processed, this.reason, this.captureItems);
		}

		public String toString() {
			return "CaptureDTOReturned.CaptureDTOReturnedBuilder(id=" + this.id + ", createdDate=" + this.createdDate + ", numberOfItems=" + this.numberOfItems + ", processed=" + this.processed + ", reason=" + this.reason + ", captureItems=" + this.captureItems + ")";
		}
	}

}
