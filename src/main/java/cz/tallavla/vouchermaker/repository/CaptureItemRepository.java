package cz.tallavla.vouchermaker.repository;

import cz.tallavla.vouchermaker.model.repository.CaptureItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptureItemRepository extends JpaRepository<CaptureItem, Long> {


}
