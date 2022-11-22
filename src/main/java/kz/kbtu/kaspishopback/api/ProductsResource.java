package kz.kbtu.kaspishopback.api;

import kz.kbtu.kaspishopback.domain.KsProduct;

import kz.kbtu.kaspishopback.dto.ProductDto;
import kz.kbtu.kaspishopback.service.ProductService;
import kz.kbtu.kaspishopback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import kz.kbtu.kaspishopback.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;



import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductsResource {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/product/list")
    public ResponseEntity<List<KsProduct>> productList() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/product/{id}/detail")
    public ResponseEntity<?> productDetail(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PostMapping("/product/save")
    public ResponseEntity<KsProduct> create(@RequestBody ProductDto product) {
        System.out.println(product.getDescription());
        KsProduct newProduct = new KsProduct(null,
                product.getManufacturer(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                userService.getUser((String)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        );

        productService.save(newProduct);
        return ResponseEntity.ok(newProduct);
    }
}
