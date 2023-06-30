package cz.tallavla.vouchermaker.model.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import java.math.BigDecimal;

@Entity
@Table(name = "captureItems")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
public class CaptureItem {

	@SequenceGenerator(name = "captureItemIdSeq", sequenceName = "captureItems_id_seq", initialValue = 30001, allocationSize = 1)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "captureItemIdSeq")
	private Long id;

	@Column(nullable = false)
	private String voucherCode;

	@Column(nullable = false)
	private BigDecimal captureAmount;

	@Column(nullable = false)
	private boolean processed;

	@CreatedBy
	@ManyToOne
	@JsonIgnore
	private Capture capture;

	@CreatedBy
	@ManyToOne
	@JsonIgnore
	private Voucher voucher;
	@Override
	public String toString() {
		return "CaptureItem{" +
				"id=" + id +
				", voucherCode='" + voucherCode + '\'' +
				", captureAmount=" + captureAmount +
				", processed=" + processed +
//				", capture=" + capture +
				'}';
	}
}
