package com.ozguryazilim.libraryproject.controller;

import com.ozguryazilim.libraryproject.DAO.PublisherBookInfo;
import com.ozguryazilim.libraryproject.DAO.PublisherDAO;
import com.ozguryazilim.libraryproject.model.Book;
import com.ozguryazilim.libraryproject.model.Publisher;
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
import java.util.Optional;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    private PublisherService publisherService;
    private BookService bookService;

    @Autowired
    public PublisherController(PublisherService publisherService, BookService bookService) {
        this.publisherService = publisherService;
        this.bookService = bookService;
    }

    @GetMapping
    public String publishers(Model model) {
        model.addAttribute("publisher", new PublisherDAO("",""));

        List<Publisher> authors = publisherService.findAll();
        model.addAttribute("publishers", authors);

        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);

        return "publishers";
    }

    @PostMapping("/create")
    public String createPublisher(@ModelAttribute("publisher") PublisherDAO DAO, Model model) {

        model.addAttribute("publisher", DAO);

        Publisher publisher = Publisher.builder()
                .name(DAO.getName())
                .explanation(DAO.getExplanation())
                .build();

       publisherService.save(publisher);
       return "publishers";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("pbinfo") PublisherBookInfo info, Model model) {

        model.addAttribute("publisher", new PublisherDAO("",""));
        model.addAttribute("pbinfo", info);

        Optional<Book> book = bookService.findByName(info.getBookName());
        Optional<Publisher> author = publisherService.findByName(info.getPublisherName());

        if (book.isPresent() && author.isPresent()) {
            publisherService.addBook(author.get(), book.get());
        }

        return "publishers";
    }

}
