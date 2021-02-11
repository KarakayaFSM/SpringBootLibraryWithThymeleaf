package com.ozguryazilim.libraryproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String subName;
    private String seriesName;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    //Bir Kitabı birden çok yayın evi basabilir
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    private Long isbn;
    private String explanation;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
