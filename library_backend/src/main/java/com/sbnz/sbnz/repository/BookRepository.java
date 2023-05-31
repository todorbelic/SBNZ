package com.sbnz.sbnz.repository;

import com.sbnz.sbnz.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthorId(Long id);
}
