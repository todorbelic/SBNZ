package com.sbnz.sbnz.repository;

import com.sbnz.sbnz.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
