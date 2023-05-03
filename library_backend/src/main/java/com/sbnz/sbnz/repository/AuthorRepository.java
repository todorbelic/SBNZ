package com.sbnz.sbnz.repository;

import com.sbnz.sbnz.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Author findByFirstNameAndLastName(String firstName, String lastName);
}
