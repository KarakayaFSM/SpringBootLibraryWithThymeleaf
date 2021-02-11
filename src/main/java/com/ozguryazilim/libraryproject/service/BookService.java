package com.ozguryazilim.libraryproject.service;

import com.ozguryazilim.libraryproject.DAO.BookDAO;
import com.ozguryazilim.libraryproject.model.Author;
import com.ozguryazilim.libraryproject.model.Book;
import com.ozguryazilim.libraryproject.model.Publisher;
import com.ozguryazilim.libraryproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;

    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public Book update(Book newBook) {
        return repository.save(newBook);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> findByAuthor(String name) {
        return repository.findByName(name);
    }

    public List<Book> findByIsbn(Long isbn) {
        return repository.findByIsbn(isbn);
    }

    public List<Book> findBySeriesName(String seriesName) {
        return repository.findBySeriesName(seriesName);
    }

    public Optional<Book> findByName(String name) {
        List<Book> books = repository.findByName(name);
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }

    public Book getBook(BookDAO DAO, Author author, Publisher publisher) {
        return Book.builder()
                .name(DAO.getName())
                .subName(DAO.getSubName())
                .seriesName(DAO.getSeriesName())
                .author(author)
                .publisher(publisher)
                .isbn(DAO.getIsbn())
                .explanation(DAO.getExplanation())
                .build();
    }

}
