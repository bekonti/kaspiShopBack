package kz.kbtu.kaspishopback.dto;

import kz.kbtu.kaspishopback.domain.Basket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BasketDto {
    List<Basket> baskets= new ArrayList<>();
    double total;

}
