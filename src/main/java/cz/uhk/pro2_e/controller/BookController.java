package cz.uhk.pro2_e.controller;

import cz.uhk.pro2_e.model.Book;
import cz.uhk.pro2_e.model.Quote;
import cz.uhk.pro2_e.model.Review;
import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.service.BookService;
import cz.uhk.pro2_e.service.QuoteService;
import cz.uhk.pro2_e.service.ReviewService;
import cz.uhk.pro2_e.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final ReviewService reviewService;
    private final QuoteService quoteService;
    private final UserService userService;

    @Autowired
    public BookController(BookService bookService, ReviewService reviewService,
                          QuoteService quoteService, UserService userService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
        this.quoteService = quoteService;
        this.userService = userService;
    }

    // Show edit form for a book, including reviews and quotes
    @GetMapping("/{id}/edit")
    public String editBookForm(Model model, @PathVariable long id) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("newReview", new Review());
        model.addAttribute("newQuote", new Quote());
        return "books_edit";
    }

    @PostMapping("/{id}/add-review")
    public String addReview(@ModelAttribute("newReview") Review review,@PathVariable long id) {
        // Create a new review with the data from the form and param from path
        Review newReview = new Review();
        newReview.setBook(bookService.getBook(id));
        newReview.setDate(LocalDate.now());
        newReview.setComment(review.getComment());
        reviewService.saveReview(newReview);

        return  "redirect:/books/" + id + "/edit";
    }

    // Add a quote to a book
    @PostMapping("/{id}/add-quote")
    public String addQuote(@ModelAttribute("newQuote") Quote quote,@PathVariable long id) {
        // Create a new quote with the data from the form and param from path
        Quote newQuote = new Quote();
        newQuote.setBook(bookService.getBook(id));
        newQuote.setDateAdded(LocalDate.now());
        newQuote.setContent(quote.getContent());
        newQuote.setPageNumber(quote.getPageNumber());
        quoteService.saveQuote(newQuote);

        return  "redirect:/books/" + id + "/edit";
    }

    // Delete a review from a book
    @GetMapping("/{bookId}/delete-review/{reviewId}")
    public String deleteReview(@PathVariable long bookId, @PathVariable long reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/books/" + bookId + "/edit";
    }

    // Delete a quote from a book
    @GetMapping("/{bookId}/delete-quote/{quoteId}")
    public String deleteQuote(@PathVariable long bookId, @PathVariable long quoteId) {
        quoteService.deleteQuote(quoteId);
        return "redirect:/books/" + bookId + "/edit";
    }

    // List all books for the current user
    @GetMapping("/")
    public String list(Model model, Principal principal) {
        // Only show books for the logged-in user
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("books", bookService.getBooksByUser(user));
        return "books_list";
    }

    // Show add book form
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "books_add";
    }

    // Save a new or edited book
    @PostMapping("/save")
    public String save(@ModelAttribute Book book, Principal principal) {
        if (book.getId() == 0L) {
            // New book
            User user = userService.getUserByUsername(principal.getName());
            book.setUser(user);
        } else {
            // Update existing book
            Book existingBook = bookService.getBook(book.getId());
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            // Don't change the user!
            book = existingBook;
        }
        bookService.saveBook(book);
        return "redirect:/books/";
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable long id) {
        model.addAttribute("book", bookService.getBook(id));
        return "books_delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable long id) {
        bookService.deleteBook(id);
        return "redirect:/books/";
    }

    // Book detail page
    @GetMapping("/{id}")
    public String getBookDetail(@PathVariable long id, Model model) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "books_detail";
    }
}
