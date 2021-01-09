package bookweb.domain.repository;

import bookweb.domain.entity.BannedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BannedUserRepository extends JpaRepository<BannedUser, Long>, CrudRepository<BannedUser, Long> {
}
