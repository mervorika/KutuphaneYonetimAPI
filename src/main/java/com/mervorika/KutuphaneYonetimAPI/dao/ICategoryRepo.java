package com.mervorika.KutuphaneYonetimAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mervorika.KutuphaneYonetimAPI.entities.Category;
@Repository
public interface ICategoryRepo extends JpaRepository<Category,Integer> {
}
