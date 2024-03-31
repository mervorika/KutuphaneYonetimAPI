package com.mervorika.KutuphaneYonetimAPI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "serial")
    private int id;
    @Column(name = "name",nullable = false)
    @NotNull
    private String name;
    @Column(name = "publicationYear")
    private int publicationYear;
    @Column(name = "stock")
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "publisher_id",referencedColumnName = "id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<BookBorrowing> bookBorrowing;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinTable(name = "cat2book",
            joinColumns = {@JoinColumn(name = "cat2book_book_id")},
            inverseJoinColumns = {@JoinColumn(name = "cat2book_category_id")}
    )
    private List<Category> categoryList;
}

