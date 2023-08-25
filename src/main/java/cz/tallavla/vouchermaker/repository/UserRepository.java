package cz.tallavla.vouchermaker.repository;

import cz.tallavla.vouchermaker.model.repository.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUserNameOrEmail(String userName, String email);

	Optional<User> findByUserName(String userName);

	Boolean existsByUserName(String userName);

	Boolean existsByEmail(String email);
}
