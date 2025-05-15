package cz.uhk.pro2_e.service;

import cz.uhk.pro2_e.model.Book;
import cz.uhk.pro2_e.model.User;
import cz.uhk.pro2_e.repository.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByUser(User user) {
        return bookRepository.findByUser(user);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getBook(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
