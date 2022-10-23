package kz.kbtu.kaspishopback.service;

import kz.kbtu.kaspishopback.domain.KsProduct;

import java.util.List;

public interface ProductService {
    List<KsProduct> getProducts();
    KsProduct getProduct(Long id);
    void save(KsProduct product);
}
