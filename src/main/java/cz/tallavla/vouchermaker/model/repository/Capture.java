package cz.tallavla.vouchermaker.model.repository;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "captures")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
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
}

