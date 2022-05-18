package org.example.model;


import lombok.*;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
//poniżej tworzymy prywatny constructor ze wszystkimi parametrami dla builder (takiego potrzebuje), ograniczamy dostęp ze względu na pole id
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    @OneToMany(mappedBy = "author")
    private Set<Movie> movie;

    public Author(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }



}
