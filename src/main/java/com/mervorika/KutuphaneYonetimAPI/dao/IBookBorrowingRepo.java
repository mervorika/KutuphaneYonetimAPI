package com.mervorika.KutuphaneYonetimAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mervorika.KutuphaneYonetimAPI.entities.BookBorrowing;
@Repository
public interface IBookBorrowingRepo extends JpaRepository<BookBorrowing,Integer> {
}
