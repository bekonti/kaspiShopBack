package kz.kbtu.kaspishopback.service;

import kz.kbtu.kaspishopback.domain.KsProduct;
import kz.kbtu.kaspishopback.domain.KsUser;
import kz.kbtu.kaspishopback.domain.WishList;
import kz.kbtu.kaspishopback.repo.ProductRepo;
import kz.kbtu.kaspishopback.repo.WishListRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceBean implements ProductService {
    private final ProductRepo productRepo;
    private final UserService userService;
    private final WishListRepo wishListRepo;

    @Override
    public List<KsProduct> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public KsProduct getProduct(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public void save(KsProduct product) {
        productRepo.save(product);
    }

    @Override
    public void addWishList(long ProductId) {
        KsUser user = userService.getCurrentUser();
        KsProduct product= getProduct(ProductId);
        wishListRepo.save(new WishList(null, user, product));
    }

}
