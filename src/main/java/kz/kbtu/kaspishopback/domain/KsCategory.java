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
// это типо как хранится в таблице
//id name parent_id
//1  comp 0
//2  PK   1
//
//
//
//
//Комп 1
//    Ноут 2-> 1
//    ПК 3 -> 1
//    Комплпектующие 4 ->1
//            Мат плата 5 -> 4
//            Проц 6 -> 4
