package kz.kbtu.kaspishopback.repo;

import kz.kbtu.kaspishopback.domain.KsUser;
import kz.kbtu.kaspishopback.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepo extends JpaRepository<WishList,Long> {

}
