package com.ozguryazilim.libraryproject.repository;

import com.ozguryazilim.libraryproject.model.Author;
import com.ozguryazilim.libraryproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

   List<Book> findByName(String name);
   List<Book> findBySeriesName(String seriesName);
   List<Book> findByAuthor(Author author);
   List<Book> findByIsbn(Long isbn);

}
