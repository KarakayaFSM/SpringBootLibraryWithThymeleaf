package com.ozguryazilim.libraryproject.controller;

import com.ozguryazilim.libraryproject.DAO.BookDAO;
import com.ozguryazilim.libraryproject.model.Book;
import com.ozguryazilim.libraryproject.service.AuthorService;
import com.ozguryazilim.libraryproject.service.BookService;
import com.ozguryazilim.libraryproject.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;

    @Autowired
    public BookController(BookService bookService,
                          AuthorService authorService,
                          PublisherService publisherService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.publisherService = publisherService;
    }

    @GetMapping
    public String books(Model model) {
        BookDAO bookDAO = new BookDAO("", "", "", "", "", 12345L
                , "");
        model.addAttribute("book", bookDAO);
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") BookDAO DAO, Model model) {

        model.addAttribute("book", DAO);

        Book book = bookService.getBook(DAO,
                authorService.findByName(DAO.getAuthorName()).orElseThrow(),
                publisherService.findByName(DAO.getPublisherName()).orElseThrow()
        );

        bookService.save(book);

        return "books";
    }


}
