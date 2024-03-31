package com.mervorika.KutuphaneYonetimAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mervorika.KutuphaneYonetimAPI.entities.Book;

@Repository
public interface IBookRepo extends JpaRepository<Book,Integer> {
}
