package com.ozguryazilim.libraryproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authors")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "author_id")
    private Long id;

    @Column(unique = true)
    private String name;

    private String explanation;

    public void addBook(Book book) {
        books.add(book);
    }

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Book> books;

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
