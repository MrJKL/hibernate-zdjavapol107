package org.example.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Actor {
    @Id
    private Long id;
    private String name;
    private String yearsOfExperience;
    @ManyToMany
    private Set<Movie> movies;
}
