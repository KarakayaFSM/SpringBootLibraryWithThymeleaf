package com.ozguryazilim.libraryproject.repository;

import com.ozguryazilim.libraryproject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorByName(String name);
}
