package com.ozguryazilim.libraryproject.service;

import com.ozguryazilim.libraryproject.model.Author;
import com.ozguryazilim.libraryproject.model.Book;
import com.ozguryazilim.libraryproject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;

    }

    public Author save(Author author) {
        return repository.save(author);
    }

    public List<Author> findAll() {
        return repository.findAll();
    }

    public void addBook(Long authorId, Book book) {
        Author author = findById(authorId);
        author.addBook(book);
    }

    public void addBook(Author author, Book book) {
        author.addBook(book);
    }

    public Author findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Optional<Author> findByName(String name) {
        List<Author> authors = repository
                .findAuthorByName(name);
        return authors.isEmpty() ? Optional.empty() : Optional.of(authors.get(0));
    }

    public Author update(Author author) {
        return repository.save(author);
    }

    public void delete(Author author) {
        repository.delete(author);
    }


}
