package cz.tallavla.vouchermaker.model.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
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

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getVoucherCode() {
//		return voucherCode;
//	}
//
//	public void setVoucherCode(String voucherCode) {
//		this.voucherCode = voucherCode;
//	}
//
//	public BigDecimal getCaptureAmount() {
//		return captureAmount;
//	}
//
//	public void setCaptureAmount(BigDecimal captureAmount) {
//		this.captureAmount = captureAmount;
//	}
//
//	public boolean isProcessed() {
//		return processed;
//	}
//
//	public void setProcessed(boolean processed) {
//		this.processed = processed;
//	}
//
//	public Capture getCapture() {
//		return capture;
//	}
//
//	public void setCapture(Capture capture) {
//		this.capture = capture;
//	}
//
//	public Voucher getVoucher() {
//		return voucher;
//	}
//
//	public void setVoucher(Voucher voucher) {
//		this.voucher = voucher;
//	}
//
//	public CaptureItem(Long id, String voucherCode, BigDecimal captureAmount, boolean processed, Capture capture, Voucher voucher) {
//		this.id = id;
//		this.voucherCode = voucherCode;
//		this.captureAmount = captureAmount;
//		this.processed = processed;
//		this.capture = capture;
//		this.voucher = voucher;
//	}
}
