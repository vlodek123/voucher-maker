package cz.tallavla.vouchermaker.model.repository;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "captures")
public class Capture {

	@SequenceGenerator(name = "captureIdSeq", sequenceName = "capture_id_seq", initialValue = 20001, allocationSize = 1)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "captureIdSeq")
	private Long id;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(nullable = false)
	private int numberOfItems;

	@Column(nullable = false)
	private boolean processed;
	@Column
	private String reason;

	@OneToMany(mappedBy = "capture")
	private List<CaptureItem> captureItemList;

	@Override
	public String toString() {
		return "Capture{" +
				"id=" + id +
				", createdDate=" + createdDate +
				", numberOfItems=" + numberOfItems +
				", processed=" + processed +
				", reason='" + reason + '\'' +
				", captureItemList=" + captureItemList +
				'}';
	}

	public Capture(Long id, LocalDateTime createdDate, int numberOfItems, boolean processed, String reason, List<CaptureItem> captureItemList) {
		this.id = id;
		this.createdDate = createdDate;
		this.numberOfItems = numberOfItems;
		this.processed = processed;
		this.reason = reason;
		this.captureItemList = captureItemList;
	}

	public Capture() {
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

	public List<CaptureItem> getCaptureItemList() {
		return captureItemList;
	}

	public void setCaptureItemList(List<CaptureItem> captureItemList) {
		this.captureItemList = captureItemList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Capture capture = (Capture) o;

		if (numberOfItems != capture.numberOfItems) return false;
		if (processed != capture.processed) return false;
		if (!Objects.equals(id, capture.id)) return false;
		if (!Objects.equals(createdDate, capture.createdDate)) return false;
		if (!Objects.equals(reason, capture.reason)) return false;
		return Objects.equals(captureItemList, capture.captureItemList);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + numberOfItems;
		result = 31 * result + (processed ? 1 : 0);
		result = 31 * result + (reason != null ? reason.hashCode() : 0);
		result = 31 * result + (captureItemList != null ? captureItemList.hashCode() : 0);
		return result;
	}
}

