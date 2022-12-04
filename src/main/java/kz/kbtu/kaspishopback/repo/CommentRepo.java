package kz.kbtu.kaspishopback.repo;

import kz.kbtu.kaspishopback.domain.CommentForProduct;
import kz.kbtu.kaspishopback.domain.KsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentRepo extends JpaRepository<CommentForProduct,Long> {
    List<CommentForProduct> findAllByProductIdEquals (Long productId);
}
