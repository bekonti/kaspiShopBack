package kz.kbtu.kaspishopback.service;

import kz.kbtu.kaspishopback.domain.Basket;
import kz.kbtu.kaspishopback.domain.CommentForProduct;
import kz.kbtu.kaspishopback.domain.KsProduct;
import kz.kbtu.kaspishopback.dto.BasketDto;

import java.util.List;

public interface ProductService {
    List<KsProduct> getProducts();
    KsProduct getProduct(Long id);
    void save(KsProduct product);
    void addWishList(long productId);
    void removeFromWishList(long productId);
    Basket createOrUpdateBasket(Basket busket);
    void removeFromBasket(Long busketId);
    List<Basket> getBaskets();
    BasketDto getBaskets1();
    CommentForProduct addComment(CommentForProduct newComment);
    List<CommentForProduct> getCommentsByProductId(Long id);
    void deleteComment(Long id);
}
