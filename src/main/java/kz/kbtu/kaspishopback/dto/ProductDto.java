package kz.kbtu.kaspishopback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String manufacturer;
    private String name;
    private String description;
    private double price;


}
