package cz.tallavla.vouchermaker.model.repository;

import jakarta.persistence.*;
import lombok.*;

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

	@Column(nullable = false, unique = true)
	private String voucherCode;

	@Column(nullable = false)
	private BigDecimal captureAmount;

	@Column(nullable = false)
	private Long captureId;
}
