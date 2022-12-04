package kz.kbtu.kaspishopback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentForProduct  {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private KsUser author;

    @ManyToOne
    private KsProduct productId;


}
