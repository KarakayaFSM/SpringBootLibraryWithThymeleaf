package com.ozguryazilim.libraryproject.controller;

import com.ozguryazilim.libraryproject.DAO.AuthorBookInfo;
import com.ozguryazilim.libraryproject.DAO.AuthorDAO;
import com.ozguryazilim.libraryproject.model.Author;
import com.ozguryazilim.libraryproject.model.Book;
import com.ozguryazilim.libraryproject.service.AuthorService;
import com.ozguryazilim.libraryproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;
    private BookService bookService;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String authors(Model model) {
        model.addAttribute("author", new AuthorDAO("", ""));

        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);

        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "authors";
    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute("author") AuthorDAO DAO, Model model) {

        model.addAttribute("author", DAO);

        Author author = Author.builder()
                .name(DAO.getName())
                .explanation(DAO.getExplanation())
                .build();

        authorService.save(author);

        return "authors";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("abinfo") AuthorBookInfo info, Model model) {

        model.addAttribute("author", new AuthorDAO("",""));
        model.addAttribute("abinfo", info);

        Optional<Book> book = bookService.findByName(info.getBookName());
        Optional<Author> author = authorService.findByName(info.getAuthorName());

        if (book.isPresent() && author.isPresent()) {
            authorService.addBook(author.get(), book.get());
        }

        return "authors";
    }

}
