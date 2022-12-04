package kz.kbtu.kaspishopback.api;

import kz.kbtu.kaspishopback.domain.Basket;
import kz.kbtu.kaspishopback.domain.CommentForProduct;
import kz.kbtu.kaspishopback.domain.KsProduct;

import kz.kbtu.kaspishopback.dto.BasketDto;
import kz.kbtu.kaspishopback.dto.ProductDto;
import kz.kbtu.kaspishopback.service.ProductService;
import kz.kbtu.kaspishopback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/product/addTowishList")
    public ResponseEntity<?> addWishList(@RequestBody ProductDto dto) {
        productService.addWishList(dto.getProductId());
        return ResponseEntity.ok(null);
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

    @PostMapping("/product/basket/createOrUpdate")
    public ResponseEntity<Basket> createOrUpdate(@RequestBody Basket busket) {
        return ResponseEntity.ok(productService.createOrUpdateBasket(busket));
    }

    @PostMapping("/product/basket/remove")
    public ResponseEntity<Basket> removeBasket(@RequestBody Long busketId) {
        productService.removeFromBasket(busketId);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/product/basket/list")
    public ResponseEntity<BasketDto> getBasket() {
        return ResponseEntity.ok(BasketDto.builder()
                .baskets(productService.getBaskets())
                .total(99)
                .build());
    }

    @GetMapping("/product/{id}/comments")
    public ResponseEntity<List<CommentForProduct>> getCommentsByProductId(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.getCommentsByProductId(id));
    }

    @PostMapping("/product/comment/add")
    public ResponseEntity<CommentForProduct> add(@RequestBody CommentForProduct newComment){
        return ResponseEntity.ok().body(productService.addComment(newComment));
    }

    @PostMapping("product/comment/remove")
    public ResponseEntity<?> removeComment(@RequestBody Long id){
        productService.deleteComment(id);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

}
