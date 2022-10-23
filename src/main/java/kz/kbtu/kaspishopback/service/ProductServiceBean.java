package kz.kbtu.kaspishopback.service;

import kz.kbtu.kaspishopback.domain.KsProduct;
import kz.kbtu.kaspishopback.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceBean implements ProductService{
private final ProductRepo productRepo;
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
}
