package cz.uhk.pro2_e.repository;

import cz.uhk.pro2_e.model.Book;
import cz.uhk.pro2_e.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUser(User user);
}
