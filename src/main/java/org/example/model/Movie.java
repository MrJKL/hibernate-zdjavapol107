package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "actors")
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private LocalDate releaseDate;
    @OneToOne
    private Badge badge;
    @ManyToOne
    private Author author;
    // jeżeli ustawimy @ManyToMany(cascade = cascade.PERSIST) to przy zapisywaniu zapisze także te powiązane
    @ManyToMany
    private Set<Actor> actors;
    public Movie(String title, LocalDate releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }
}
