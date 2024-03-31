package com.mervorika.KutuphaneYonetimAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mervorika.KutuphaneYonetimAPI.entities.Publisher;

public interface IPublisherRepo extends JpaRepository<Publisher,Integer> {
}
