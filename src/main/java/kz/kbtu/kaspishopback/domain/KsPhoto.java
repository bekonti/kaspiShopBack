package kz.kbtu.kaspishopback.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KsPhoto {
    @Id
    @GeneratedValue
    private Long id;


    private String name;
    private String type;
    private String filePath;

//    @Lob
//    @Column(name = "imagedata", length = 1000)
//    private byte[] imageData;
}
