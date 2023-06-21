package cz.tallavla.vouchermaker.model.repository;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vouchers")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@Getter
public class Voucher {

	@SequenceGenerator(name = "voucherIdSeq", sequenceName = "vouchers_id_seq", initialValue = 10001, allocationSize = 1)

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voucherIdSeq")
	private Long id;

	@Column(nullable = false, unique = true)
	private String voucherCode;
	@Column(nullable = false)
	private BigDecimal amount;
	@Column(nullable = false)
	private BigDecimal balance;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@Column(nullable = false)
	private LocalDateTime expirationDate;

	@Column(nullable = false)
	private boolean active;
}