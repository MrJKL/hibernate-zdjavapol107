package org.example.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "movie")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer value;
//    powiązanie encji, adnotacja do wiązania 1 do 1 (1 film do 1 odznaki)
//    trzeba wskazać kto jest "właścicielem" relacji, aby hibernate nie utworzył dwóch relacji (obie tabele posiadają kolumny z id tej drugiej, a powinna tylko jedna)
    @OneToOne(mappedBy = "badge")
    private Movie movie;

    public Badge(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
