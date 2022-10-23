package kz.kbtu.kaspishopback.repo;

import kz.kbtu.kaspishopback.domain.KsPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepo extends JpaRepository<KsPhoto,Long> {

    Optional<KsPhoto> findByName(String fileName);
}
