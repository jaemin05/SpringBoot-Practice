package com.example.library.Repository;

import com.example.library.model.Book;
import com.example.library.model.Lend;
import com.example.library.model.LendStatus;

import java.util.Optional;

public interface LendRepository extends JpaRepository<Lend, Long>{
    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
