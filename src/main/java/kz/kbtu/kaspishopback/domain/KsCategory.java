package kz.kbtu.kaspishopback.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KsCategory {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Long parent_id = -1L;
}
