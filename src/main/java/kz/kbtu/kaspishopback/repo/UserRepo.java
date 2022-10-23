package kz.kbtu.kaspishopback.repo;

import kz.kbtu.kaspishopback.domain.KsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<KsUser,Long> {
    KsUser findByUsername(String username);
}
