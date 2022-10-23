package kz.kbtu.kaspishopback.repo;

import kz.kbtu.kaspishopback.domain.KsRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<KsRole, Long> {
    KsRole findByName(String name);
}
