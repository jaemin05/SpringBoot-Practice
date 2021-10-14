package com.example.library.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @JsonBackReference
    @OneToMany(mappedBy = "author", // mappedBy : 관계의 주체
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books;
}

