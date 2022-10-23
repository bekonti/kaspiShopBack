package kz.kbtu.kaspishopback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KsProduct {
    @Id
    @GeneratedValue
    private Long id;

    private String manufacturer;
    private String name;
    private String description;
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    private KsUser author;

}
