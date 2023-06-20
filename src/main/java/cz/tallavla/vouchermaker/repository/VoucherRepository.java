package cz.tallavla.vouchermaker.repository;

import cz.tallavla.vouchermaker.model.repository.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

	Optional<Voucher> findByVoucherCode(String code);
}
