package kz.kbtu.kaspishopback.repo;

import kz.kbtu.kaspishopback.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BasketRepo extends JpaRepository<Basket, Long> {



}
