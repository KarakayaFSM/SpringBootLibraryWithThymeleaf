package com.ozguryazilim.libraryproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "publishers")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue
    @Column(name = "publisher_id")
    private Long id;

    @Column(unique = true)
    private String name;

    private String explanation;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private Set<Book> books;

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
