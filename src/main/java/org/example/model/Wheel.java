package org.example.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Hibernate sam utworzy, tabelę na podstawie klas z adnotacją @Entity (jest to encja)
// Hibernate operuje jedynie na obiektach, ale nie można nim wykonać np.: DROP TABLE
@Entity
@Getter
@Setter
@NoArgsConstructor
// utworzy tabelę o nazwie KOLO zamiast nazwy klasy
@Table(name = "KOLO")
// Możemy nałożyć constrain na tabelę (np. wymagać unikalności danych w wybranych kolumnach)
// Możemy na tabele nałożyć indeksy, przyśpieszy to wyszukiwanie, ale spowalnia zapisywanie bazy danych
public class Wheel {
    @Id
    // poniższe pozwala na samodzielne automatyczne wpisywanie nr ID (są cztery sposoby generowania klucza)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    w adnotacjach kolumn możemy zmieniać nazwę kolumny, wymuszać unikalność danych itp.)
//    adnotacje @Column jest wymagana jedynie, gdy chcemy coś zmienić lub wymusić itp., kolumna zostanie utworzona nawet bez tej notacji
//    adnotacji uzywamy w cełym projekcie tak samo, tzn np tylko na polach, lub np. tylko na getterach jeżeli tak jest w już aktualnym projekcie
    @Column(name = "ROZMIAR")
    private Integer size;
    private String type;

    public Wheel(String name, Integer size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }
}
