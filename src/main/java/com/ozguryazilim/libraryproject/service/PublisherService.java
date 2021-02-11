package com.ozguryazilim.libraryproject.service;

import com.ozguryazilim.libraryproject.model.Book;
import com.ozguryazilim.libraryproject.model.Publisher;
import com.ozguryazilim.libraryproject.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private PublisherRepository repository;

    @Autowired
    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public Publisher save(Publisher publisher) {
        return repository.save(publisher);
    }

    public void addBook(Long publisherId, Book book) {
        Publisher publisher = findById(publisherId);
        publisher.addBook(book);
    }

    public void addBook(Publisher publisher, Book book) {
        publisher.addBook(book);
    }

    public List<Publisher> findAll() {
        return repository.findAll();
    }

    public Publisher findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Optional<Publisher> findByName(String name) {
        return repository
                .findByName(name);
    }

    public Publisher update(Publisher publisher) {
        return repository.save(publisher);
    }

    public void delete(Publisher publisher) {
        repository.delete(publisher);
    }

}
