package org.example.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer yearsOfExperience;
    @ManyToMany(mappedBy = "actors", fetch = FetchType.EAGER)
    private Set<Movie> movies;

}
