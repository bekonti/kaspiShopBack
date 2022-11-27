package kz.kbtu.kaspishopback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="busket")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Basket {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name = "count")
    private int count;

    @Transient
    private double total;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private KsUser user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private KsProduct product;

    @Column(name = "finished", nullable = false)
    private boolean finished= false;

}
