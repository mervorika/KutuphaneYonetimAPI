package com.mervorika.KutuphaneYonetimAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mervorika.KutuphaneYonetimAPI.entities.Author;

public interface IAuthorRepo extends JpaRepository<Author,Integer> {
}
