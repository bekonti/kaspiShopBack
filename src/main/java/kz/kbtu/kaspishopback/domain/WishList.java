package kz.kbtu.kaspishopback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private KsUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    private KsProduct wishList;
}
