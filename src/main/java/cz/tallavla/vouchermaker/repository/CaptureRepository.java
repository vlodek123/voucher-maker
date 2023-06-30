package cz.tallavla.vouchermaker.repository;

import cz.tallavla.vouchermaker.model.repository.Capture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptureRepository extends JpaRepository<Capture, Long> {


}
