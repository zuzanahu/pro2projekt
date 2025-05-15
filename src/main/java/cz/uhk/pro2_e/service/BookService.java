package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Book;
import java.util.List;

import cz.uhk.pro2_e.model.User;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    List<Book> getAllBooks();
    List<Book> getBooksByUser(User user);
    void saveBook(Book book);
    Book getBook(long id);
    void deleteBook(long id);
}
