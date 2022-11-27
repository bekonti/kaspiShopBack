package kz.kbtu.kaspishopback.repo;

import kz.kbtu.kaspishopback.domain.KsProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<KsProduct, Long> {

}
